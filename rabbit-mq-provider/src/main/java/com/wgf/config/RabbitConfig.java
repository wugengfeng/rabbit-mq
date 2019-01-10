package com.wgf.config;

import com.wgf.constant.RabbitMqQueue;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by acer on 2019/1/7.
 */
@Configuration
public class RabbitConfig {

    @Bean(name = RabbitMqQueue.TEST)
    public Queue queue() {
        return new Queue(RabbitMqQueue.TEST);
    }

}
