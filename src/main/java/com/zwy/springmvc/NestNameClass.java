package com.zwy.springmvc;

import org.springframework.stereotype.Component;

@Component
public class NestNameClass {
    private String nestConfigName;
    private String nestPassword;

    public String getNestPassword() {
        return nestPassword;
    }

    public void setNestPassword(String nestPassword) {
        this.nestPassword = nestPassword;
    }

    public String getNestConfigName() {
        return nestConfigName;
    }

    public void setNestConfigName(String nestConfigName) {
        this.nestConfigName = nestConfigName;
    }
}
