package pt.ipb.dsys.threads.workspace.coordination;

public class CoordMain {
    public static void main(String... args) {
        Accumulator ac = new Accumulator(10);
        // Producer.
        new Thread(() -> {
            while (true) {
                int n = (int) Math.floor(Math.random() * 5000);
                ac.put(n);
                System.out.println("Putting " + n + " to the accumulator");
                try {
                    Thread.sleep((long) Math.floor(Math.random() * 5000));
                } catch (InterruptedException e) {}
            }
        }).start();
        // Consumer.
        new Thread(() -> {
            while (true) {
                System.out.println("Accumulator has sum " + ac.getSum());
                try {
                    Thread.sleep((long) Math.floor(Math.random() * 5000));
                } catch (InterruptedException e) {}
            }
        }).start();
    }
}
