package com.hmily.order.controller;

import com.hmily.order.config.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GirlController {

    @Autowired
    private TestConfig testConfig;

    @GetMapping("/test/print")
    public String print() {
        return "name:" + testConfig.getName() + " age:" + testConfig.getAge();
    }
}
