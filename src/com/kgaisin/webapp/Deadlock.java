package com.kgaisin.webapp;

public class Deadlock {
    private static Object LOCK1;
    private static Object LOCK2;

    public static void main(String[] args) {
        LOCK1 = "lock 1";
        LOCK2 = "lock 2";
        new Thread(() -> holdLocks(LOCK1, LOCK2)).start();
        new Thread(() -> holdLocks(LOCK2, LOCK1)).start();
    }

    private static void holdLocks(Object lock1, Object lock2) {
        synchronized (lock1) {
            System.out.println("Holding " + lock1);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("Waiting for " + lock2);
            synchronized (lock2) {
                System.out.println("Holding locks 1 and 2");
            }
        }
    }
}
