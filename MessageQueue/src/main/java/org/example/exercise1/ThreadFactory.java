package org.example.exercise1;

public class ThreadFactory {
    public static void create(Runnable runnable, boolean isDaemon) {
        Thread thread = new Thread(runnable);
        thread.setDaemon(isDaemon);
        thread.start();
    }
}
