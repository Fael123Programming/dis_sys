package org.example.exercise2;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            ThreadCreator.create(new NumberProducer("numbers", 1, 200000000), false);
        }
        for (int i = 0; i < 2000; i++) {
            ThreadCreator.create(new PrimeChecker("numbers"), false);
        }
    }
}
