package com.qian.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qian
 * @date 2018/9/29
 */
@Slf4j
@RestController
@RequestMapping("/test1")
public class Test1Controller {

    @PostMapping("/zipkin/{name}")
    public String test1Zipkin(@PathVariable String name) {
        log.info("name:{}", name);
        return "test1 ok";
    }

    @PostMapping(value = "/moc", produces = "application/json;charset=UTF-8")
    public String moc(String username) {
        log.info("my username:{}", username);
        return username;
    }

    @PostMapping(value = "/mocTest1", produces = "application/json;charset=UTF-8")
    public String mocTest1(String key) {
        log.info("my key:{}", key);
        return key;
    }

}
