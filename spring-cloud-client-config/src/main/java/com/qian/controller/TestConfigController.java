package com.qian.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qian
 * @date 2018/9/21
 */
@Slf4j
@RestController
//@RefreshScope
@RequestMapping("/test")
public class TestConfigController {

    @Value("${juxu}")
    private String juxu;

    @GetMapping(value = "/config/feng", produces = "application/json;charset=UTF-8")
    public String testConfig() {
        log.info("read config file value...");
        return juxu;
    }
}
