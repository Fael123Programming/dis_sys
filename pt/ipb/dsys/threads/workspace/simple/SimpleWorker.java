package pt.ipb.dsys.threads.workspace.simple;

public class SimpleWorker implements Runnable {
    int times;
    String message;

    public SimpleWorker(int times, String message) {
        this.times = times;
        this.message = message;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.times; i++) {
            System.out.printf("[{%s}] {%d}%n", this.message, i);
            try{
                Thread.sleep((long) Math.random() * 1000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted esxception has been thrown");
            }
        }
    }
}
