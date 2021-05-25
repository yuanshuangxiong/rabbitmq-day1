package com.yuan.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMqUtils {
    private static ConnectionFactory connectionFactory;
    //在类加载时就创建
    static{
        connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("192.168.81.128");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
    }
    //定义提供连接的方法
    public static Connection getConnection(){
        try{
            return connectionFactory.newConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    //关闭通道和关闭连接方法
    public static void closeConnectionAndChannel(Channel channel,Connection connection){
        try {
            if(channel!=null) channel.close();
            if(connection!=null)connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
