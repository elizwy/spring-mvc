package com.zwy.springmvc.config;

import mockit.Deencapsulation;
import mockit.Injectable;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.Properties;

import static org.junit.Assert.*;

@RunWith(JMockit.class)
public class PropertiesDecryptTest {
    @Mocked
    private PropertiesDecrypt decrypt;

    @Test
    public void testDecryptStringWhenIsNull() {
        String result = (String) Deencapsulation.invoke(decrypt, "decryptString", new Class[]{String.class}, new Object[]{null});
        assertNull(result);
    }

    @Test
    public void testDecryptString() {
        String result = (String) Deencapsulation.invoke(decrypt, "decryptString", new Class[]{String.class}, new Object[]{"aaa.com"});
        System.out.println("aaa.comecrypt");
    }

    @Test
    public void testLoadPropertiesWhenIsNull(@Injectable Properties properties) {
        Deencapsulation.invoke(decrypt, "loadProperties", properties);
        assertEquals(properties.keySet().size(),0);
    }
    @Test
    public void testLoadPropertiesWhenValueIsNull() {
        Properties properties=new Properties();
        properties.setProperty("name","");
        Deencapsulation.invoke(decrypt, "loadProperties", properties);
        assertEquals("",properties.getProperty("name"));
    }
    @Test
    public void testLoadPropertiesWhenIsNotValid() {
        Properties properties=new Properties();
        properties.setProperty("name","ENC(ZWY");
        Deencapsulation.invoke(decrypt, "loadProperties", properties);
        assertEquals("ENC(ZWY",properties.getProperty("name"));
    }
    @Test
    public void testLoadProperties() {
        Properties properties=new Properties();
        properties.setProperty("name","ENC(ZWY");
        Deencapsulation.invoke(decrypt, "loadProperties", properties);
        assertEquals("ENC(ZWY",properties.getProperty("name"));
    }

    @Test
    public void getPorperties(){
        String namespace = System.getProperty("namespace");
        System.out.println(namespace);
    }

}