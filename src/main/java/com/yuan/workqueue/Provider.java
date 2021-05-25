package com.yuan.workqueue;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yuan.utils.RabbitMqUtils;

import java.io.IOException;

public class Provider {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel=connection.createChannel();
        channel.queueDeclare("work",true,false,false,null);
        for (int i=0;i<20;i++){
            channel.basicPublish("","work",null,(i+" hello workqueue").getBytes());
        }
        RabbitMqUtils.closeConnectionAndChannel(channel,connection);
    }
}
