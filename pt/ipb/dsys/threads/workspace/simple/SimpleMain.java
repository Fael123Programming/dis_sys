package pt.ipb.dsys.threads.workspace.simple;

public class SimpleMain {
    public static void main(String[] args) {
        SimpleWorker sw1 = new SimpleWorker(10, "A");
        SimpleWorker sw2 = new SimpleWorker(10, "B");
        new Thread(sw1).start();
        new Thread(sw2).start();
    }
}
