package org.example.exercise2;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class NumberProducer implements Runnable {
    private String queue;
    private int lastInteger, from, to;

    public NumberProducer(String queue, int from, int to) {
        this.queue = queue;
        this.from = from;
        this.to = to;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    @Override
    public String toString() {
        return String.format("{\"queue\": \"%s\", \"lastInteger\": %d}", queue, lastInteger);
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
            lastInteger = random(this.from, this.to);
            String msg = Integer.toString(lastInteger);
            TextMessage txtMsg = session.createTextMessage(msg);
            System.out.println("Sent message: " + msg.hashCode() + " : " + Thread.currentThread().getName());
            msgProducer.send(txtMsg);
        } catch (Exception e) {
            System.out.println("Exception thrown: " + e.getMessage());
        }
    }

    private int random(int a, int b) {
        return (int) (a + Math.random() * (b - a + 1));
    }
}
