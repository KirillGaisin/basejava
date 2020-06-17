package com.kgaisin.webapp;

public class Deadlock {
    private static final Object Lock1 = new Object();
    private static final Object Lock2 = new Object();

    public static void main(String[] args) {
        Runnable task1 = () -> {
            synchronized (Lock1) {
                System.out.println("Holding lock 1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.println("Waiting for lock 2");
                synchronized (Lock2) {
                    System.out.println("Holding lock 1 and 2");
                }
            }
        };

        Runnable task2 = () -> {
            synchronized (Lock2) {
                System.out.println("Holding lock 2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.println("Waiting for lock 1");
                synchronized (Lock1) {
                    System.out.println("Holding lock 1 and 2");
                }
            }
        };
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        thread1.start();
        thread2.start();
    }
}
