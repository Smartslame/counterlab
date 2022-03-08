package ru.sbt.edu.concurrency.counter;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements Counter {
    private final ReentrantLock lock;
    private long count;

    public ReentrantLockCounter() {
        this.lock = new ReentrantLock();
        this.count = 0;
    }

    @Override
    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public long getValue() {
        return count;
    }
}
