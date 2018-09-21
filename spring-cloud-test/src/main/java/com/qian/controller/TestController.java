package com.qian.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qian
 * @date 2018/9/21
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/zipkin")
    public String testZipkin(String name) {
        logger.info("name:{}", name);
        System.err.println(name);
        return "OK";
    }
}
