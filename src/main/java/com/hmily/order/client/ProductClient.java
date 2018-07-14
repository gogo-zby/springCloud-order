package com.hmily.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "product")  //要调用的应用名称
public interface ProductClient {

    @GetMapping("/msg") //要调用接口的具体URL
    String productMsg();    //这里的方法名可自由定义，上面的URL和对应服务的URL一致即可


}
