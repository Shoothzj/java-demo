package com.github.shoothzj.demo.basic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicUpdateDemo {

    private static final AtomicIntegerFieldUpdater<AtomicUpdateDemo> AVAILABLE_PERMITS_UPDATER = AtomicIntegerFieldUpdater
            .newUpdater(AtomicUpdateDemo.class, "availablePermits");

    private volatile int availablePermits = 0;

    public static void main(String[] args) {
        AtomicUpdateDemo atomicUpdateDemo = new AtomicUpdateDemo();
        AVAILABLE_PERMITS_UPDATER.addAndGet(atomicUpdateDemo, 5);
        int i = AVAILABLE_PERMITS_UPDATER.get(atomicUpdateDemo);
        System.out.println(i);
    }

}
