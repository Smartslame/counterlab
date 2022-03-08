package ru.sbt.edu.concurrency.locks.theory;


import ru.sbt.edu.concurrency.locks.ILock;
import ru.sbt.edu.concurrency.util.TwoThreadIds;

public class LockTwo implements ILock {
    private volatile int victim;

    @Override
    public void lock() {
        int i = TwoThreadIds.me();
        victim = i;
        while (victim == i) {
        }
    }


    @Override
    public void unlock() {
    }
}
