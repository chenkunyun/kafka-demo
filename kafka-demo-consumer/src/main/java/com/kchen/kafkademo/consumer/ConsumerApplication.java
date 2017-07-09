package com.kchen.kafkademo.consumer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ConsumerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConsumerApplication.class)
                .web(true)
                .run(args);
    }
}
