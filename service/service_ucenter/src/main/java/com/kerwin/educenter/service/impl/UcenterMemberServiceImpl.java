package com.kerwin.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kerwin.commonutils.JwtUtils;
import com.kerwin.commonutils.MD5;
import com.kerwin.educenter.entity.UcenterMember;
import com.kerwin.educenter.entity.vo.RegisterVo;
import com.kerwin.educenter.mapper.UcenterMemberMapper;
import com.kerwin.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kerwin.servicebase.exceptionhandler.KerwinException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author kerwin
 * @since 2021-06-18
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String login(UcenterMember user) {
        //获取登录手机号和密码
        String mobile = user.getMobile();
        String password = user.getPassword();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new KerwinException(20001,"手机号或密码为空！");
        }
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember member = baseMapper.selectOne(wrapper);
        //判断是否注册
        if (member==null) {
            throw new KerwinException(20001,"当前手机号未注册！");
        }
        //判断密码是否正确
        if (!member.getPassword().equals(MD5.encrypt(password))) {
            throw new KerwinException(20001,"密码输入错误！");
        }
        // 判断用户是否处于被封禁状态
        if (member.getIsDisabled() || member.getIsDeleted()) {
            throw new KerwinException(20001,"当前用户存在异常，无法登录！");
        }

        //登录成功之后利用数据库查询出的用户信息生成token字符串
        return JwtUtils.getJwtToken(member.getId(), member.getNickname());
    }

    @Override
    public void registerUser(RegisterVo user) {
        String mobile = user.getMobile();
        String password = user.getPassword();
        String nickname = user.getNickname();
        String code = user.getCode();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)
                ||StringUtils.isEmpty(nickname) || StringUtils.isEmpty(code)) {
            //需具体提示信息  前后台都判断
            throw new KerwinException(20001,"存在输入项未填！");
        }

        // 判断验证码是否一致
        // 获取redis中存入的验证码
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if (!code.equals(redisCode)) {
            throw new KerwinException(20001, "验证码错误！");
        }
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);

        if (count>0) {
            throw new KerwinException(20001,"当前手机号已注册！");
        }
        UcenterMember member  = new UcenterMember();
        member.setMobile(mobile);
        member.setPassword(MD5.encrypt(password));
        member.setNickname(nickname);
        member.setIsDisabled(false);
        member.setAvatar("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=543903244,703141099&fm=26&gp=0.jpg");
        baseMapper.insert(member);
    }


    //根据openid判断
    @Override
    public UcenterMember getOpenIdMember(String openid) {
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        UcenterMember member = baseMapper.selectOne(wrapper);
        return member;
    }
}
