package org.example.exercise1;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class Producer implements Runnable {
    private String queue, message;

    public Producer(String queue, String message) {
        this.queue = queue;
        this.message = message;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("{\"queue\": \"%s\", \"message\": \"%s\"}", queue, message);
    }

    @Override
    public void run() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(Constants.BROKER_URL);
        boolean transacted = false;
        int acknowledgeMode = Session.AUTO_ACKNOWLEDGE;
        try(
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(transacted, acknowledgeMode);
        ) {
            connection.start();
            Destination destination = session.createQueue(queue);
            MessageProducer msgProducer = session.createProducer(destination);
            msgProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            String msg = String.format("[%s] from %s", message, this.getOrigin());
            TextMessage txtMsg = session.createTextMessage(msg);
            System.out.println("Sent message: " + msg.hashCode() + " : " + Thread.currentThread().getName());
            msgProducer.send(txtMsg);
        } catch (Exception e) {
            System.out.println("Exception thrown: " + e.getMessage());
        }
    }

    private String getOrigin() {
        return String.format("%s:%d", Thread.currentThread().getName(), this.hashCode());
    }
}
