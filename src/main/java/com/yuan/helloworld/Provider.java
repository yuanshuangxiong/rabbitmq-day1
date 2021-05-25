package com.yuan.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.yuan.utils.RabbitMqUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Provider {

    //生产消息 直达，不需交换机
    @Test
    public void testSendMessage() throws IOException, TimeoutException {
        //通过工具类获取连接
        Connection connection = RabbitMqUtils.getConnection();
        //通过连接获取通道
        Channel channel = connection.createChannel();
        //通道绑定对应消息队列
        /*参数：1.队列名称 如果不存在自动创建
         2:定义队列是否要持久化 ,只能保證队列持久化，不能保证消息持久化
         3：是否独占队列 true独占
         4:autoDelete:是否在消费完成后自动删除队列，false自动不删除 当消费者不再绑定队列，且消息已经消费完就会自动删除队列
         5：额外的参数
         */
        channel.queueDeclare("hello", true, false, false, null);
        //发布消息
        /*
        1.交换机 2：队列 3：传递消息的属性  MessageProperties.PERSISTENT_TEXT_PLAIN 代表消息持久化
        4：消息具体内容
        直达，不需要
         */
        channel.basicPublish("", "hello", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello rabbitmq".getBytes());

        RabbitMqUtils.closeConnectionAndChannel(channel,connection);
    }
}
