package com.hmily.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    @GetMapping("/testHystrixCommand3")
    //超时配置
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
	})
    public String testHystrixCommand3(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://127.0.0.1:8080/msg2", String.class);
    }

    @GetMapping("/testHystrixCommand2")
    @HystrixCommand
    public String testHystrixCommand2(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://127.0.0.1:8080/msg", String.class);
    }
    private String defaultFallback(){
        return "默认提示： 太拥挤了，请稍后再试~~";
    }

    @GetMapping("/testHystrixCommand1")
    @HystrixCommand(fallbackMethod = "fallback")
    public String testHystrixCommand1(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://127.0.0.1:8080/msg", String.class);
//        throw new RuntimeException("发送异常了");
    }
    private String fallback(){
        return "太拥挤了，请稍后再试~~";
    }





}
