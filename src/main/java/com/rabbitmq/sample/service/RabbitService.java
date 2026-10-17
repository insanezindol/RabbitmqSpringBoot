package com.rabbitmq.sample.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Slf4j
@Service
public class RabbitService {

    @Async
    public void ayncServiceTest() {
        log.info("[BEG] ayncServiceTest");
        try {
            Thread.sleep(Duration.ofSeconds(10));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("ayncServiceTest interrupted", e);
        }
        log.info("[END] ayncServiceTest");
    }

}
