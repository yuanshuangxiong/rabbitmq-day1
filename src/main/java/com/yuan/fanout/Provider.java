package com.yuan.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yuan.utils.RabbitMqUtils;

import java.io.IOException;

public class Provider {

    //fanout：广播模型
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel=connection.createChannel();
        //参数一：交换机名称 参数2：交换机类型 fanout：广播类型
        channel.exchangeDeclare("logs","fanout");
        //发送消息
        channel.basicPublish("logs","",null,"fanout type message".getBytes());

        RabbitMqUtils.closeConnectionAndChannel(channel,connection);

    }
}
