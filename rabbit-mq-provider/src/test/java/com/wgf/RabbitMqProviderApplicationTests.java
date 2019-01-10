package com.wgf;

import com.wgf.constant.RabbitMqQueue;
import com.wgf.entry.TestEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqProviderApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * Exchangs.default
     * 对象发送
     */
    @Test
    public void send() {
        // 对象发送必须实现序列化，消息提供者，消费者对象全类名必须一致
        TestEntity testEntity = new TestEntity("wgf12", new Date());
        this.amqpTemplate.convertAndSend(RabbitMqQueue.TEST, testEntity);
    }

}

