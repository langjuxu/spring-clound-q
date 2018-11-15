package com.qian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author qian
 * @date 2018/11/9
 * 配置中心客户端
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.qian"}, lazyInit = true)
public class SpringCloudClientConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudClientConfigApplication.class, args);
    }

}
