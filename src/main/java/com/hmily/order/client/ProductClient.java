package com.hmily.order.client;

import com.hmily.order.dataobject.ProductInfo;
import com.hmily.order.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient(name = "product")  //要调用的应用名称
public interface ProductClient {

    @GetMapping("/msg") //要调用接口的具体URL
    String productMsg();    //这里的方法名可自由定义，上面的URL和对应服务的URL一致即可

    @PostMapping("/product/listForOrder")
    List<ProductInfo> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<CartDTO> cartDTOList);

}
