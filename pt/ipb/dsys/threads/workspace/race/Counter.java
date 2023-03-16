package pt.ipb.dsys.threads.workspace.race;

public class Counter {
    private int counter;

    // Using synchronized does that only one thread will be able to call this method per time.
    public synchronized int getCounter() {
        return this.counter;
    }
    
    public synchronized void increment() {
        this.counter++;
    }
}
