package com.yuan.direct;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yuan.utils.RabbitMqUtils;

import java.io.IOException;

public class Provider {

    //route模式
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel=connection.createChannel();
        String exchangName="logs_direct";
        //通过通道声明交换机 参数1：交换机名称 参数2：direct 路由模式
        channel.exchangeDeclare(exchangName,"direct");
        String routeKey="error";

        channel.basicPublish(exchangName,routeKey,null,("这是direct模型发布的基于route key:["+routeKey+"]发送的消息。").getBytes());
        RabbitMqUtils.closeConnectionAndChannel(channel,connection);

    }
}
