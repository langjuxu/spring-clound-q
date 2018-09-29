package com.qian.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/zipkin/{name}")
    public String test1Zipkin(@PathVariable String name) {
        log.info("name:{}", name);
        return "test1 ok";
    }

}
