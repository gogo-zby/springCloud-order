package com.hmily.order.message;

import com.hmily.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableBinding(StreamClient.class)
public class StramReceiver {

//    @StreamListener(StreamClient.OUTPUT)
//    public void  process(String  message) {
//        log.info("StramReceiver: {}", message);
//    }

    @StreamListener(StreamClient.OUTPUT)
//    @SendTo(StreamClient.INPUT)
    public void  process(OrderDTO orderDTO) {
        log.info("StramReceiver: {}", orderDTO);
    }

//    @StreamListener(StreamClient.INPUT)
//    public void  process2(String  message) {
//        log.info("process2  StramReceiver: {}", message);
//    }
}
