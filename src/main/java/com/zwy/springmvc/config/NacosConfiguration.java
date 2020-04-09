package com.zwy.springmvc.config;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableNacosConfig
@NacosPropertySource(groupId = "spring-demo",dataId = "mysql", autoRefreshed = true)
public class NacosConfiguration {

}
