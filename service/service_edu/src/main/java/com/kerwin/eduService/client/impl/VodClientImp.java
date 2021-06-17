package com.kerwin.eduService.client.impl;

import com.kerwin.commonutils.R;
import com.kerwin.eduService.client.VodClient;
import org.springframework.stereotype.Component;

@Component
public class VodClientImp implements VodClient {

    //此方法只会在VodClient中 testNacos执行出错之后才会执行
    @Override
    public R testNacos(String id) {
        return R.error().message("服务请求失败！"+id);
    }
}
