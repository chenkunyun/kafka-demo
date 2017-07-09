package com.kchen.kafkademo.producer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ProducerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ProducerApplication.class)
                .web(true)
                .run(args);
    }
}
