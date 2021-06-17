package com.kerwin.eduService.client;

import com.kerwin.commonutils.R;
import com.kerwin.eduService.client.impl.VodClientImp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 需要在调用服务中编写被调用服务的服务名
 * 并且在调用的方法上写上完全路径 如用到PathVariable中一定要指定名称@PathVariable("id")
 * */
@Component
@FeignClient(name = "service-vod",fallback = VodClientImp.class)
public interface VodClient {
    //定义需要调用的方法的路径
    @GetMapping("/eduvod/video/testNacos/{id}")
//    此处 PathVariable中一定要指定名称
    public R testNacos(@PathVariable("id") String id);
}
