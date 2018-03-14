package com.botscrew.messenger.cdk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Test {
    public static void main(String[] args) {
        SpringApplication.run(Test.class, args);
        log.info("STARTED");
    }
}
