package com.yuan.fanout;

import com.rabbitmq.client.*;
import com.yuan.utils.RabbitMqUtils;

import java.io.IOException;

public class Consumer3 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel=connection.createChannel();

        //通道绑定交换机 参数1：交换机名称 参数2：交换机类型：fanout direct topic
        channel.exchangeDeclare("logs","fanout");
        //获取临时队列
        String queueName = channel.queueDeclare().getQueue();
        //绑定交换机和队列
        channel.queueBind(queueName,"logs","");
        //消费消息
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者3："+new String(body));
            }
        });
    }
}
