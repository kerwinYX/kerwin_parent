package com.kerwin.eduService.controller;

import com.kerwin.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@Api(tags = "登录接口")
@RestController
@CrossOrigin  //解决跨域方法之一
@RequestMapping("/eduService/user")
public class EduLoginController {

    @PostMapping("login")
    public R toLogin(){
        return R.ok().data("token","admin-kerwin");
    }

    @GetMapping("info")
    public R getInfo(){
        return R.ok().data("roles","[admin]").data("name","kerwin").data("avatar","https://img1.baidu.com/it/u=3319494983,4268520294&fm=26&fmt=auto&gp=0.jpg");
    }
}
