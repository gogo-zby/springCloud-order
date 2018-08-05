package com.hmily.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqReceiver {
    //    @RabbitListener(queues = "myQueue") //不会自动创建队列，需要手动创建好队列后，项目才能启动
//    @RabbitListener(queuesToDeclare = @Queue("myQueue"))    //自动创建
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue("myQueue"),
                    exchange = @Exchange("myExchange")
            )
    )
    public void process(String message) {
        log.info("MqReceiver: {}", message);
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue("computerOrder"),
                    key = "computer",
                    exchange = @Exchange("myOrder")
            )
    )
    public void processComputer(String message) {
        log.info("computer MqReceiver: {}", message);
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue("fruitOrder"),
                    key = "fruit",
                    exchange = @Exchange("myOrder")
            )
    )
    public void processFruit(String message) {
        log.info("fruit computer MqReceiver: {}", message);
    }
}
