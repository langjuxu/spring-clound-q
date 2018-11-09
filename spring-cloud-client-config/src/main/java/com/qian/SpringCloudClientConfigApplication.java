package com.qian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author qian
 * @date 2018/11/9
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudClientConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudClientConfigApplication.class, args);
    }

}
