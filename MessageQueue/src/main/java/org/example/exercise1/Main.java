package org.example.exercise1;

public class Main {
    public static void main(String[] args) {
        Producer p1 = new Producer("greetings", "My secret message!");
        System.out.println("Producer: " + p1);
        Consumer c1 = new Consumer("greetings");
        System.out.println("Consumer: " + c1);
        ThreadFactory.create(p1, false);
        ThreadFactory.create(c1, false);
    }
}
