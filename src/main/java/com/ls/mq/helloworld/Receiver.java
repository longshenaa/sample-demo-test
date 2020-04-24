package com.ls.mq.helloworld;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;

public class Receiver {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://192.168.93.3:61616");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Destination destination = session.createQueue("hello");
        MessageConsumer consumer = session.createConsumer(destination);
        for (;;) {
            TextMessage message = (TextMessage)consumer.receive();
            message.acknowledge();
            if (message == null) break;
            System.out.println(message.getText());
        }
        connection.close();
    }
}
