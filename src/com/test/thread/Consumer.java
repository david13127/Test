package com.test.thread;

public class Consumer implements Runnable {
    private Quence quence;
    public Thread t;
    public <T extends Quence> Consumer(T quence) {
        this.quence = quence;
        t = new Thread(this, "Consumer");
    }

    public void run() {
        while (true) {
            quence.get();
        }
    }
}
