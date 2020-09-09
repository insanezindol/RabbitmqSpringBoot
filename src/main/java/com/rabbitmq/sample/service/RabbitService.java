package com.rabbitmq.sample.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Slf4j
@EnableAsync
@Service
public class RabbitService {

    @Async
    public void ayncServiceTest() {
        log.info("[BEG] ayncServiceTest");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("[END] ayncServiceTest");
    }

}
