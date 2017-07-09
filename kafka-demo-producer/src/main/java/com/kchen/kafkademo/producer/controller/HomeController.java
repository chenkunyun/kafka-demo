package com.kchen.kafkademo.producer.controller;

import com.kchen.kafkademo.common.bean.Order;
import com.kchen.kafkademo.producer.service.OrderProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private OrderProducerService orderProducerService;

    @RequestMapping("/produce")
    public void produceOrder() {
        orderProducerService.sendOrder(new Order());
    }
}
