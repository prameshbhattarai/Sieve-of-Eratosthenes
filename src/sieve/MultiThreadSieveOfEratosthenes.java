package sieve;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadSieveOfEratosthenes {

    private final boolean bookKeeping[];
    private final int workers;
    private int i = 2;
    private ReentrantLock lock = new ReentrantLock();

    public MultiThreadSieveOfEratosthenes(int n, int workers) {
        this.bookKeeping =  new boolean[n+1]; // by default boolean primitive are assigned as false
        this.workers = workers;
    }

    public boolean[] process() {
        ExecutorService executors = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int i = 0; i < workers; i++) {
            executors.execute(new Processor());
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
            // trying to acquire lock
            if(lock.tryLock()) {
                while(i*i < bookKeeping.length) {
                    // if flag is not changed then it is prime
                    if(bookKeeping[i] == false) {
                        // update all the multiples of i as non-prime number
                        for (int j = i*2; j < bookKeeping.length; j += i) {
                            bookKeeping[j] = true;
                        }
                    }
                    i++; // increment the index
                }
                lock.unlock();
            }
        }
    }
}
