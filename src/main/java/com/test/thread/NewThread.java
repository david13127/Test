package com.test.thread;

public class NewThread implements Runnable {
    private String name;
    private String msg;
    private Calme target;
    public Thread t;

    public NewThread(String threadname, Calme targ, String msg) {
        this.name = threadname;
        this.target = targ;
        this.msg = msg;
        t = new Thread(this, name);
        System.out.println("New Thread: " + t);
    }

    public void run() {
        synchronized (target) {
            target.call(msg);
        }

        System.out.println("Exiting Child Thread.");
    }
}
