package com.wgf;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.wgf.constant.RabbitMqQueue;
import com.wgf.entry.TestEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

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

    @Test
    public void sendExchange() {
        this.amqpTemplate.convertAndSend("amazon.order", null, "hello");
    }


    /**
     * 创建fanout类型交换机 + 绑定队列
     * @throws IOException
     * @throws TimeoutException
     */
    @Test
    public void create() throws IOException, TimeoutException {
        //交换机名字
        String exchangeName = "testchange";
        //交换机名字类型
        String exchangeType = "fanout";
        //消息队列名字
        String queueName1 = "fanout.queue1";
        String queueName2 = "fanout.queue2";
        String queueName3 = "fanout.queue3";
        //实例连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置地址
        connectionFactory.setHost("47.106.207.16");
        //设置端口
        connectionFactory.setPort(5672);
        //设置用户名
        connectionFactory.setUsername("rabbitmq");
        //设置密码
        connectionFactory.setPassword("wgf123");
        //获取连接（跟jdbc很像）
        Connection connection = connectionFactory.newConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明队列。
        //参数1：队列名
        //参数2：持久化 （true表示是，队列将在服务器重启时依旧存在）
        //参数3：独占队列（创建者可以使用的私有队列，断开后自动删除）
        //参数4：当所有消费者客户端连接断开时是否自动删除队列
        //参数5：队列的其他参数
        channel.queueDeclare(queueName1, true, false, false, null);
        channel.queueDeclare(queueName2, true, false, false, null);
        channel.queueDeclare(queueName3, true, false, false, null);

        //声明交换机
        channel.exchangeDeclare(exchangeName, exchangeType);

        //队列绑定到交换机
        channel.queueBind(queueName1, exchangeName, "");
        channel.queueBind(queueName2, exchangeName, "");
        channel.queueBind(queueName3, exchangeName, "");

        channel.close();
        connection.close();
    }
}