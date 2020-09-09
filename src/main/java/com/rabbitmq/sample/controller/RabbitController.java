package com.rabbitmq.sample.controller;

import com.rabbitmq.sample.service.RabbitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rabbit")
public class RabbitController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RabbitService rabbitService;

    @GetMapping("/pub/str")
    public String pubStr() {
        rabbitTemplate.convertAndSend("cafe.topic", "order.coffee.string", "Message");
        return "ok";
    }

    @GetMapping("/pub/builder")
    public String pubBuilder() {
        String json = "{\"shoes\":\"nike\"}";

        Message message = MessageBuilder
                .withBody(json.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();

        rabbitTemplate.send("cafe.topic", "order.coffee.builder", message);

        return "ok";
    }

    @GetMapping("/async")
    public String async() {
        log.info("[BEG] async");
        rabbitService.ayncServiceTest();
        log.info("[END] async");
        return "ok";
    }

}
