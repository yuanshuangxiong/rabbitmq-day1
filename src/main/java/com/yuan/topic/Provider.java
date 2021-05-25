package com.yuan.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yuan.utils.RabbitMqUtils;

import java.io.IOException;

public class Provider {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel=connection.createChannel();
        channel.exchangeDeclare("topics","topic");
        String routeKey="user.save";
        channel.basicPublish("topics",routeKey,null,("这是topic路由模型，routeKey:["+routeKey+"]").getBytes());
        RabbitMqUtils.closeConnectionAndChannel(channel,connection);
    }
}
