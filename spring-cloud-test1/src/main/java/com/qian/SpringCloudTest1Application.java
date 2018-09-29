package com.qian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * eureka注册服务提供者
 *
 * @author qian
 * @date 2018/9/21
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudTest1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudTest1Application.class, args);
    }
}
