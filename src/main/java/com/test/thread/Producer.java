package main.java.com.test.thread;

public class Producer implements Runnable {
    private Quence quence;
    public Thread t;

    public Producer(Quence quence) {
        this.quence = quence;
        t = new Thread(this, "Producer");
    }

    public void run() {
        int i = 0;
        while (true) {
            quence.put(i++);
        }
    }
}
