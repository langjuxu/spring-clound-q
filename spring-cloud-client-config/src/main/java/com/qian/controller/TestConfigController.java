package com.qian.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qian
 * @date 2018/9/21
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestConfigController {

    @Value("${feng}")
    private String feng;

    @GetMapping("/config/feng")
    public String testConfig() {
        log.info("read config file value...");
        return feng;
    }
}
