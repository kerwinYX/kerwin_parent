package com.kerwin.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class ConstantVodUtils implements InitializingBean {


    private String keyid ="LTAI4FvvVEWiTJ3GNJJqJnk7";


    private String keysecret="9st82dv7EvFk9mTjYO1XXbM632fRbG";

    public static String ACCESS_KEY_SECRET;
    public static String ACCESS_KEY_ID;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = keyid;
        ACCESS_KEY_SECRET = keysecret;
    }
}
