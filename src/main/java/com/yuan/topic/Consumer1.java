package com.yuan.topic;

import com.rabbitmq.client.*;
import com.yuan.utils.RabbitMqUtils;

import java.io.IOException;

public class Consumer1 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机以及类型
        channel.exchangeDeclare("topics","topic");
        //创建一个临时队列
        String queue=channel.queueDeclare().getQueue();
        //绑定队列和交换机，动态通配符形式route key *代表一个字符串 #代表0或多个
        channel.queueBind(queue,"topics","user.*");
        //
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1："+new String(body));
            }
        });
    }
}
