package com.hmily.order.controller;

import com.hmily.order.dto.OrderDTO;
import com.hmily.order.message.StreamClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

    @GetMapping("/testSendMsg")
    public boolean process() {
        boolean send = streamClient.output().send(MessageBuilder.withPayload("now " + new Date()).build());
        log.info("result: {}" , send);
        return send;
    }

    @GetMapping("/testSendMsg2")
    public boolean processOrderDTO() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("1001");
        orderDTO.setBuyerName("Hello");
        boolean send = streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
        log.info("result: {}" , send);
        return send;
    }

}
