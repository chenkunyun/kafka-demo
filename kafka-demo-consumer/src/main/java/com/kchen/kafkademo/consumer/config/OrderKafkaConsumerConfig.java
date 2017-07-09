package com.kchen.kafkademo.consumer.config;

import com.kchen.kafkademo.common.bean.Order;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Validated
public class OrderKafkaConsumerConfig {

    @Value("${kafka.servers}")
    @NotNull
    private String servers;

    @Value("${kafka.order.topic}")
    @NotNull
    private String topic;

    @Value("${kafka.order.group-id}")
    @NotNull
    private String groupId;

    @Bean
    public Map<String, Object> consumerConfig() {
        final HashMap<String, Object> result = new HashMap<>();

        result.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        result.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        result.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        result.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

//        result.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        result.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return result;
    }

    @Bean
    public ConsumerFactory<String, Order> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfig(),
                new StringDeserializer(),
                new JsonDeserializer<>(Order.class));   // 指定类型很关键!
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, Order> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Order> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        containerFactory.setConsumerFactory(consumerFactory());
        containerFactory.setConcurrency(3);
        containerFactory.getContainerProperties().setPollTimeout(3000);
        return containerFactory;
    }
}
