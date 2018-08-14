package sieve;

public class Util {

    public static void printPrime(boolean[] bookKeeping) {
        System.out.println("List of primes ");
        for (int i = 0; i < bookKeeping.length; i++)
            if (!bookKeeping[i])
                System.out.println(i);
    }

    public static int sizeOfPrime(boolean[] bookKeeping) {
        int size = 0;
        for (int i = 0; i < bookKeeping.length; i++)
            if (!bookKeeping[i])
                ++size;
        return size;
    }
}
