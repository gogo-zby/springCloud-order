package com.hmily.order.controller;

import com.hmily.order.client.ProductClient;
import com.hmily.order.dataobject.ProductInfo;
import com.hmily.order.dto.CartDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class ClientController {

    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        //刚才定义的方法
        String response = productClient.productMsg();
        log.info("response={}", response);
        return "feign: " + response;
    }

    @GetMapping("/getProductList")
    public String getProductList(){
        List<ProductInfo> productInfos = productClient.listForOrder(Arrays.asList("157875196366160022", "157875227953464068"));
        log.info("res={}", productInfos);
        return "ok";
    }

    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock(){
        productClient.decreaseStock(Arrays.asList(new CartDTO("164103465734242707", 3)));
        return "ok";
    }
}
