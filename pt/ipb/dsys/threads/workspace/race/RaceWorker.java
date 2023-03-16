package pt.ipb.dsys.threads.workspace.race;

public class RaceWorker implements Runnable {
    private int times;
    private String name;
    private Counter counter;

    public RaceWorker(int times, String name, Counter counter) {
        this.times = times;
        this.name = name;
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.times; i++) {
            synchronized(counter) {  // counter is the shared resource.
                try {
                    Thread.sleep((long) Math.random() * 100);
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException has been thrown");
                }
                this.counter.increment();
                System.out.printf("[{%s}] counter: {%d}%n", this.name, this.counter.getCounter());
            }
        }
    }
}
