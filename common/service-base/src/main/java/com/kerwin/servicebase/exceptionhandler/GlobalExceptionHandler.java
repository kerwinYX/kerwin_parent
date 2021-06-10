package com.kerwin.servicebase.exceptionhandler;

import com.kerwin.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局统一异常类
 * */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //指定出现什么异常执行这个方法 Exception.class异常类型
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据  所以需要添加此注解
    public R error(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error().message("执行了"+e.getMessage()+"的全局异常处理..");
    }

    /**
     * 自定义异常
    * */
    @ExceptionHandler(KerwinException.class)
    @ResponseBody //为了返回数据    会先执行小异常  特定异常
    public R error(KerwinException e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error().code(e.getCode()).message(e.getMsg());
    }

}
