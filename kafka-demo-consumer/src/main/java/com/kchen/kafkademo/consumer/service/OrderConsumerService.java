package com.kchen.kafkademo.consumer.service;


import com.kchen.kafkademo.common.bean.Order;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumerService {

    private static Logger logger = LoggerFactory.getLogger(OrderConsumerService.class);

    @KafkaListener(topics = "${kafka.order.topic}")
    public void processOrder(ConsumerRecord<String, Order> record) {
        logger.info("begin processing order:{}", record.value());
    }
}
