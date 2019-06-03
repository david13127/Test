package main.java.com.test.thread;

public class Quence {
    private int n;
    private Boolean isValueSet = false;
    public synchronized int get() {
        while (!isValueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Get: " + n);
        isValueSet = false;
        notify();
        return n;
    }

    public synchronized void put(int n) {
        while (isValueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.n = n;
        isValueSet = true;
        System.out.println("Put: " + n);
        notify();
    }
}
