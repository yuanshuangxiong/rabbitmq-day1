package com.yuan.workqueue;

import com.rabbitmq.client.*;
import com.yuan.utils.RabbitMqUtils;

import java.io.IOException;

public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        final Channel channel = connection.createChannel();
        channel.basicQos(1);
        channel.queueDeclare("work",true,false,false,null);
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1："+new String(body));
                //参数1：确认队列中具体哪个消息。 参数2：是否开启多个消息同时确认
                channel.basicAck(envelope.getDeliveryTag(),false);

            }
        });
    }
}
