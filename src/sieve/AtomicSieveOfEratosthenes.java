package sieve;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicSieveOfEratosthenes {

    private final boolean bookKeeping[];
    private final int workers;
    private AtomicInteger optimistic = new AtomicInteger(2);
    private Object sync = new Object();

    public AtomicSieveOfEratosthenes(int n, int workers) {
        this.bookKeeping =  new boolean[n+1]; // by default boolean primitive are assigned as false
        this.workers = workers;
    }

    public boolean[] process() {
        ExecutorService executors = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int i = 0; i < workers; i++) {
            executors.execute(new AtomicSieveOfEratosthenes.Processor());
        }
        executors.shutdown();
        try {
            executors.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            return bookKeeping;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    class Processor implements Runnable {

        @Override
        public void run() {
            synchronized (sync) {
                while(optimistic.get()*optimistic.get() < bookKeeping.length) {
                    // if flag is not changed then it is prime
                    if(bookKeeping[optimistic.get()] == false) {
                        // update all the multiples of i as non-prime number
                        for (int j = optimistic.get()*2; j < bookKeeping.length; j += optimistic.get()) {
                            bookKeeping[j] = true;
                        }
                    }
                    optimistic.getAndIncrement(); // increment the index
                }
            }
        }
    }
}
