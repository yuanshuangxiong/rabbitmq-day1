package com.yuan.helloworld;

import com.rabbitmq.client.*;
import com.yuan.utils.RabbitMqUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //通过工具类获取连接
        Connection connection = RabbitMqUtils.getConnection();
        //通过连接获取通道
        Channel channel = connection.createChannel();
        //绑定队列
      //  autoAck：true 接收到传递过来的消息后acknowledged（应答服务器），false 接收到消息后不应答服务器
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            /*
            4：消息队列中取出的消息
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String(body)="+new String(body));
//                super.handleDelivery(consumerTag, envelope, properties, body);
            }
        });
        //changele如果不关闭会一直监听信息
    }

    @Test
    public void test() throws IOException, TimeoutException {


    }
}
