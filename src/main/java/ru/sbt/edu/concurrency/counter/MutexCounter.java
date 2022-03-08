package ru.sbt.edu.concurrency.counter;

public class MutexCounter implements Counter {
    private long count;

    public MutexCounter() {
        this.count = 0;
    }

    @Override
    public synchronized void increment() {
        count++;
    }

    @Override
    public long getValue() {
        return count;
    }
}
