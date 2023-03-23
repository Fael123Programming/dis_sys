package pt.ipb.dsys.threads.workspace.coordination;

import java.util.Arrays;

public class Accumulator {
    private int[] nums;
    private int n;

    public Accumulator(int size) {
        this.nums = new int[size];
    }

    public synchronized int getSum() {
        while (this.n < this.nums.length) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        this.n = 0;
        notifyAll();
        return Arrays.stream(this.nums).reduce(0, Integer::sum);
    }

    public synchronized void put(int parcel) {
        while (this.n == this.nums.length) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        this.nums[this.n++] = parcel;
        notifyAll();
    }
}
