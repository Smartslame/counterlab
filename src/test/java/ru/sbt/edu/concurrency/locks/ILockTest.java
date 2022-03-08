package ru.sbt.edu.concurrency.locks;

import org.junit.Test;
import ru.sbt.edu.concurrency.counter.*;
import ru.sbt.edu.concurrency.locks.theory.LockOne;
import ru.sbt.edu.concurrency.locks.theory.LockTwo;
import ru.sbt.edu.concurrency.locks.theory.LockZero;
import ru.sbt.edu.concurrency.locks.theory.PetersonLock;
import ru.sbt.edu.concurrency.util.TwoThreadIds;

import static junit.framework.TestCase.assertEquals;

public class ILockTest {
    private static final int NUM_ITER = 10000;

    @Test
    public void testLockZeroCounter() {
        testTheoryLock(new LockZero());
    }

    @Test
    public void testLockOneCounter() {
        testTheoryLock(new LockOne());
    }

    @Test
    public void testLockTwoCounter() {
        testTheoryLock(new LockTwo());
    }

    @Test
    public void testPetersonLockCounter() {
        testTheoryLock(new PetersonLock());
    }

    @Test
    public void testMutexCounter() {
        testCounter(new MutexCounter(), NUM_ITER);
    }

    @Test
    public void testReentrantLockCounter() {
        testCounter(new ReentrantLockCounter(), NUM_ITER);
    }

    @Test
    public void testConcurrentCounter() {
        testCounter(new ConcurrentCounter(), NUM_ITER);
    }

    @Test
    public void testMagicCounter() {
        testCounter(new MagicCounter(Runtime.getRuntime().availableProcessors()), NUM_ITER);
    }


    private void testTheoryLock(ILock lock) {
        Counter counter = new ILockCounter(lock);
        testCounter(counter, NUM_ITER);
    }

    @Test
    public void testNaiveCounter() {
        Counter counter = new SeqCounter();

        testCounter(counter, 1000);
    }

    private void testCounter(Counter counter, int iters) {
        Runnable increment = () -> {
            System.out.println(TwoThreadIds.me());
            for (int i = 0; i < iters; i++) {
                counter.increment();
            }
        };

        Thread t0 = new Thread(increment);
        Thread t1 = new Thread(increment);
        t0.start();
        t1.start();

        try {
            t0.join();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long count = counter.getValue();
        System.out.println(count);
        assertEquals("Oops! Unexpected Behaviour!", iters * 2, count);
    }
}