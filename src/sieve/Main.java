package sieve;

public class Main {

    public static void main(String[] args) {
        SieveOfEratosthenes s = new SieveOfEratosthenes(1000_000_00);
        MultiThreadSieveOfEratosthenes m = new MultiThreadSieveOfEratosthenes(1000_000_00, 10);

        Long start = System.nanoTime();
        System.gc();
        boolean[] bookKeeping = s.process();
        Long end = System.nanoTime();
        System.out.println("Time taken " + (end - start));

        Long mstart = System.nanoTime();
        System.gc();
        boolean[] mBookKeeping = m.process();
        Long mend = System.nanoTime();
        System.out.println("Time taken " + (mend - mstart));

        System.out.println("Size of primes calculated by SieveOfEratosthenes " + Util.sizeOfPrime(bookKeeping));
        System.out.println("Size of primes calculated by MultiThreadSieveOfEratosthenes " + Util.sizeOfPrime(mBookKeeping));
    }
}
