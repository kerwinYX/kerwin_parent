package com.kerwin.educenter.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class ConstantWxUtils implements InitializingBean {

//    @Value("${wx.open.app_id}")
    private String appId = "wxed9954c01bb89b47";

//    @Value("${wx.open.app_secret}")
    private String appSecret ="a7482517235173ddb4083788de60b90e";

//    @Value("${wx.open.redirect_url}")
    private String redirectUrl = "http://guli.shop/api/ucenter/wx/callback";

    public static String WX_OPEN_APP_ID;
    public static String WX_OPEN_APP_SECRET;
    public static String WX_OPEN_REDIRECT_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        WX_OPEN_APP_ID = appId;
        WX_OPEN_APP_SECRET = appSecret;
        WX_OPEN_REDIRECT_URL = redirectUrl;
    }
}
