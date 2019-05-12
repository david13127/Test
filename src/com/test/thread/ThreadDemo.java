package com.test.thread;

public class ThreadDemo {
    public static void main1(String[] args) {
        Calme target = new Calme();
        NewThread nt1 = new NewThread("One", target, "Hello");
        nt1.t.setPriority(1);
        NewThread nt2 = new NewThread("Two", target, "Synchronized");
        nt2.t.setPriority(2);
        NewThread nt3 = new NewThread("Three", target, "World");
        nt3.t.setPriority(3);

        nt1.t.start();
        nt2.t.start();
        nt3.t.start();

        System.out.println("Thread One is alive: " + nt1.t.isAlive());
        System.out.println("Thread Two is alive: " + nt2.t.isAlive());
        System.out.println("Thread Three is alive: " + nt3.t.isAlive());

        try {
            System.out.println("Waiting for threads to finish.");
            nt1.t.join();
            nt2.t.join();
            nt3.t.join();
        } catch (InterruptedException e) {
            System.out.println("Main Thread Interrupted.");
            e.printStackTrace();
        }
        System.out.println("Thread One is alive: " + nt1.t.isAlive());
        System.out.println("Thread Two is alive: " + nt2.t.isAlive());
        System.out.println("Thread Three is alive: " + nt3.t.isAlive());

        System.out.println("Main Thread Exiting.");
    }

    public static void main(String[] args) {
        Quence quence = new Quence();
        Producer producer = new Producer(quence);
        Consumer consumer = new Consumer(quence);

        producer.t.start();
        consumer.t.start();
        System.out.println("Press Ctrl-C to stop");
    }
}
