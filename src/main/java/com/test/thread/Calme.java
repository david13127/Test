package main.java.com.test.thread;

public class Calme {
    public void call(String msg) {
        System.out.println("[" + msg + "]");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
            e.printStackTrace();
        }
    }
}
