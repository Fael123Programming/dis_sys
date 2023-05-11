package org.example.exercise2;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

public class EvenOddChecker implements Runnable, ExceptionListener {
    private String queue;

    public EvenOddChecker(String queue) {
        this.queue = queue;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    @Override
    public String toString() {
        return String.format("{\"queue\": \"%s\"}", queue);
    }

    @Override
    public void run() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(Constants.BROKER_URL);
        boolean transacted = false;
        int acknowledgeMode = Session.AUTO_ACKNOWLEDGE;
        long timeout = 1000;
        try (
                Connection connection = connectionFactory.createConnection();
                Session session = connection.createSession(transacted, acknowledgeMode);
        ) {
            connection.start();
            connection.setExceptionListener(this);
            Destination destination = session.createQueue(queue);
            MessageConsumer msgConsumer = session.createConsumer(destination);
            Message msg = msgConsumer.receive(timeout);
            String msgTxt;
            if (msg instanceof TextMessage txtMessage) {
                msgTxt = txtMessage.getText();
                if (Integer.parseInt(msgTxt) % 2 == 0) {
                    msgTxt = msgTxt + " is even";
                } else {
                    msgTxt = msgTxt + " is odd";
                }
            } else {
                msgTxt = msg == null ? "null" : msg.toString();
            }
            System.out.println(msgTxt);
            msgConsumer.close();
        } catch (Exception e) {
            System.out.println("Exception thrown: " + e.getMessage());
        }
    }

    @Override
    public synchronized void onException(JMSException ex) {
        System.out.println("JMS Exception occurred.  Shutting down client.");
    }
}
