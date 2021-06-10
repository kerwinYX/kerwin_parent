package com.kerwin.servicebase.exceptionhandler;

import lombok.*;

/**
 * 自定义异常类
 * */
@Getter
@Setter
@AllArgsConstructor  //生成有参数构造方法
@NoArgsConstructor   //生成无参数构造
public class KerwinException extends RuntimeException {
    /**
     * 异常状态码
     * */
    private Integer code;
    /**
     * 异常信息
     * */
    private String msg;
}
