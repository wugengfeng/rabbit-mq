package com.wgf.listener;

import com.alibaba.fastjson.JSON;
import com.wgf.constant.RabbitMqQueue;
import com.wgf.entry.TestEntity;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by acer on 2019/1/7.
 */
@Component
@RabbitListener(queues = RabbitMqQueue.TEST)
public class TestReceiver {

    @RabbitHandler
    public void process(TestEntity testEntity) {
        System.out.println(">>>>>>>>>> 接收消息 >>>>>>>>>>");
        System.out.println(JSON.toJSONString(testEntity));
        System.out.println("<<<<<<<<<< 接收完毕 <<<<<<<<<<");
    }
}
