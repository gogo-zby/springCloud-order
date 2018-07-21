package com.hmily.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),                //设置熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),    //请求数达到后才计算
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //熔断时间
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "40"),    //错误率
    })
    @GetMapping("/testHystrixCommand4")
    public String testHystrixCommand4(@RequestParam("number") Integer number) {
        if (number % 2 == 0) {
            return "success";
        }
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://127.0.0.1:8080/msg2", String.class);
    }

    @GetMapping("/testHystrixCommand3")
    //超时配置
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String testHystrixCommand3() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://127.0.0.1:8080/msg2", String.class);
    }

    @GetMapping("/testHystrixCommand2")
    @HystrixCommand
    public String testHystrixCommand2() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://127.0.0.1:8080/msg", String.class);
    }

    private String defaultFallback() {
        return "默认提示： 太拥挤了，请稍后再试~~";
    }

    @GetMapping("/testHystrixCommand1")
    @HystrixCommand(fallbackMethod = "fallback")
    public String testHystrixCommand1() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://127.0.0.1:8080/msg", String.class);
//        throw new RuntimeException("发送异常了");
    }

    private String fallback() {
        return "太拥挤了，请稍后再试~~";
    }


}
