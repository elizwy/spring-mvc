package com.zwy.springmvc.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.IOException;
import java.util.*;

public class PropertiesDecrypt extends PropertySourcesPlaceholderConfigurer {

    private static final String KEY ="123456" ;
    private static final String START_FILED ="ENC<";
    private static final String END_FILED =">";

    @Override
    protected void loadProperties(Properties props) throws IOException {
        super.loadProperties(props);
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            if (StringUtils.isBlank(value)) {
                continue;
            }
            if (value.startsWith(START_FILED)) {
                value=value.replaceFirst(START_FILED,"");
                props.setProperty(keyStr, decryptString(value)); // 设置解密后的明文数据
            }
        }
    }

    private String decryptString(String value){
        if(StringUtils.isBlank(value)){
            return null;
        }
        return EncryptUtil.decrypt(value,KEY);
    }
}

