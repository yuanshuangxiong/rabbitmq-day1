package com.yuan.direct;

import com.rabbitmq.client.*;
import com.yuan.utils.RabbitMqUtils;

import java.io.IOException;

public class Consumer2 {

    public static void main(String[] args) throws IOException {

        Connection connection = RabbitMqUtils.getConnection();
        Channel channel=connection.createChannel();
        //声明交换机
        channel.exchangeDeclare("logs_direct","direct");
        String queue=channel.queueDeclare().getQueue();

        channel.queueBind(queue,"logs_direct","info");
        channel.queueBind(queue,"logs_direct","error");
        channel.queueBind(queue,"logs_direct","warning");

        //消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2："+new String(body));
            }
        });

    }
}
