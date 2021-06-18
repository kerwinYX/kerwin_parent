package com.kerwin.educenter.controller;


import com.kerwin.commonutils.JwtUtils;
import com.kerwin.commonutils.R;
import com.kerwin.educenter.entity.UcenterMember;
import com.kerwin.educenter.entity.vo.RegisterVo;
import com.kerwin.educenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author kerwin
 * @since 2021-06-18
 */
@RestController
@CrossOrigin
@RequestMapping("/educenter/member")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    //登录
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember user){
        //输入的信息只有手机号和密码
        String token =  memberService.login(user);
        return R.ok().data("token",token);
    }
    //注册
    @PostMapping("register")
    public R registerUser(@RequestBody RegisterVo user){
        memberService.registerUser(user);
        return R.ok();
    }

    //根据token获取用户信息
    @GetMapping("getUserInfo")
    public R getUserInfo(HttpServletRequest request){
        String id = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = memberService.getById(id);
        return R.ok().data("userInfo",member);
    }

}

