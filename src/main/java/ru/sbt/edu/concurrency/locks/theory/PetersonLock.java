package ru.sbt.edu.concurrency.locks.theory;

import ru.sbt.edu.concurrency.locks.ILock;
import ru.sbt.edu.concurrency.util.TwoThreadIds;

public class PetersonLock implements ILock {
    private final boolean[] flag = new boolean[2];
    private volatile int victim;

    @Override
    public void lock() {
        int i = TwoThreadIds.me();
        int j = TwoThreadIds.not(i);
        flag[i] = true;
        victim = i;
        while (flag[j] && victim == i) {
        }
    }


    @Override
    public void unlock() {
        int i = TwoThreadIds.me();
        flag[i] = false;
    }

}
