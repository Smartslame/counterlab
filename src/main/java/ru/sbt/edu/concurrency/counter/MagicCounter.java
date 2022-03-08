package ru.sbt.edu.concurrency.counter;

import ru.sbt.edu.concurrency.util.TwoThreadIds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MagicCounter implements Counter {
    private final List<Long> counter;

    public MagicCounter(int numPossibleThreads) {
        this.counter = new ArrayList<>(Collections.nCopies(numPossibleThreads, 0L));
    }

    @Override
    public void increment() {
        int threadId = TwoThreadIds.me();
        if (threadId + 1 > counter.size()) {
            System.out.println("My id: " + threadId + " is greater than pool size " + counter.size());
            return;
        }
        Long incremented = counter.get(threadId) + 1;
        counter.set(threadId, incremented);

    }

    @Override
    public long getValue() {
        return counter.stream().mapToLong(Long::longValue).sum();
    }
}
