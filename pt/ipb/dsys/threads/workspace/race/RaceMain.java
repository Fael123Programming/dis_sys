package pt.ipb.dsys.threads.workspace.race;

public class RaceMain {
    public static void main(String[] args) {
        Counter counter = new Counter();
        RaceWorker rw1 = new RaceWorker(20, "A", counter), rw2 = new RaceWorker(20, "B", counter);
        new Thread(rw1).start();
        new Thread(rw2).start();
    }
}
