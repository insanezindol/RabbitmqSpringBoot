package com.rabbitmq.sample.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableAsync
public class CafeMessageListener {

    @Async
    @RabbitListener(queues = "coffee.queue")
    public void receiveMessage(final Message message) {
        log.info("receiveMessage : {}", message);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String body = new String(message.getBody());
        log.info("body : {}", body);
    }

}
