package org.example.exercise1;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

public class Consumer implements Runnable, ExceptionListener {
    private String queue;

    public Consumer(String queue) {
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
            System.out.print("Received: ");
            String msgTxt;
            if (msg instanceof TextMessage txtMessage) {
                msgTxt = txtMessage.getText();
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
