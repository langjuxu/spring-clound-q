package com.qian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.internal.EnableZipkinServer;

/**
 * @author qian
 * @date 2018/9/21
 */
// 启用Zipkin服务
@EnableZipkinServer
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudSleuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudSleuthApplication.class, args);
    }

}
