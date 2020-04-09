package com.zwy.springmvc;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/config")
public class TestController {
    @Value("${mysql.name}")
    private String name;
    @Value("${mysql.password}")
    private String password;
    @Value("${mysql.url}")
    private String url;

    @Autowired
    NestNameClass nestNameClass;


    @RequestMapping(value = "/get")
    public String get(Model model) {
        System.out.println(System.getProperty("namespace"));
        System.out.println(url);
        System.out.println(name);
        System.out.println(password);
        model.addAttribute("url", url);
        model.addAttribute("name", name);
        model.addAttribute("password", password);
        model.addAttribute("nestName",nestNameClass.getNestConfigName());
        model.addAttribute("nestPassword",nestNameClass.getNestPassword());
        return "say";
    }


}
