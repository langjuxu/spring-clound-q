package com.qian.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author qian
 * @date 2018/9/21
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${test1.test1ServiceUrl}")
    private String test1ServiceUrl;

    @GetMapping("/zipkin/{name}")
    public String testZipkin(@PathVariable String name) {
        log.info("name:{}", "77777777777777777777");
        System.err.println(name);
        ResponseEntity<String> exchange = restTemplate.exchange(test1ServiceUrl + name, HttpMethod.GET, null, String.class);
        System.err.println(exchange.getBody());
        return "test OK";
    }

}
