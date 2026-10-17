package com.rabbitmq.sample.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Slf4j
@Component
public class CafeMessageListener {

    @Async
    @RabbitListener(queues = "coffee.queue")
    public void receiveMessage(final Message message) {
        log.info("receiveMessage : {}", message);
        try {
            Thread.sleep(Duration.ofSeconds(10));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("receiveMessage interrupted", e);
        }
        String body = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("body : {}", body);
    }

}
