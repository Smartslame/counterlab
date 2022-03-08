package ru.sbt.edu.concurrency.counter;

import java.util.concurrent.Semaphore;

public class ConcurrentCounter implements Counter {
    private final Semaphore semaphore;
    private long count;

    public ConcurrentCounter() {
        this.count = 0;
        this.semaphore = new Semaphore(1, false);
    }

    @Override
    public void increment() {
        try {
            semaphore.acquireUninterruptibly();
            count++;
        } finally {
            semaphore.release();
        }
    }

    @Override
    public long getValue() {
        return count;
    }
}
