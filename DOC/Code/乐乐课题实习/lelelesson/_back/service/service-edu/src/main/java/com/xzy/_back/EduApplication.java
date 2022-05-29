package com.xzy._back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@EnableDiscoveryClient  // nacos 注册
@SpringBootApplication
@ComponentScan(basePackages = {"com.xzy"})
public class EduApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(EduApplication.class, args);
    }
}