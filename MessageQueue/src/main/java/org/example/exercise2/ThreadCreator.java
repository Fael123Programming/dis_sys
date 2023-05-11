package org.example.exercise2;

public class ThreadCreator {
    public static void create(Runnable runnable, boolean isDaemon) {
        Thread thread = new Thread(runnable);
        thread.setDaemon(isDaemon);
        thread.start();
    }
}
