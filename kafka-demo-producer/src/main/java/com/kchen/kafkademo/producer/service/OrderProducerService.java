package com.kchen.kafkademo.producer.service;

import com.kchen.kafkademo.common.bean.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Service
@Validated
public class OrderProducerService {

    private static Logger logger = LoggerFactory.getLogger(OrderProducerService.class);

    @Value("${kafka.order.topic}")
    @NotNull
    private String topic;

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    public void sendOrder(Order order) {
        logger.info("sending message, topic:{}, orderid:{}, content:{}",
                topic, order.getOrderId(), order);
        ListenableFuture<SendResult<String, Order>> future = kafkaTemplate.send(topic, order);
        try {
            future.get();
            logger.info("sent message, topic:{}, orderid:{}",
                    topic, order.getOrderId());
        } catch (Exception e) {
            logger.error("sent message failed, topic:{}, orderid:{}, exception:{}",
                    topic, order.getOrderId(), e);
        }
    }
}
