package com.ls.mq.helloworld;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;

public class Sender {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://192.168.93.3:61616");
        //连接默认是关闭的需要打开
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Destination destination = session.createQueue("hello");
        MessageProducer producer = session.createProducer(null);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        for (int i = 0; i < 6; i++) {
            TextMessage textMessage = new ActiveMQTextMessage();
            textMessage.setText("helloworld" + i);
//            producer.send(textMessage);
            producer.send(destination, textMessage, DeliveryMode.NON_PERSISTENT, i, 1000 * 60 * 24);
        }
//        session.commit();
        connection.close();
    }
}
