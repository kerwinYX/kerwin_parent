package com.kerwin.educenter.service;

import com.kerwin.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kerwin.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author kerwin
 * @since 2021-06-18
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember user);

    void registerUser(RegisterVo user);

    UcenterMember getOpenIdMember(String openid);
}
