package sieve;

public class SieveOfEratosthenes {

    public static void process(int n) {
        boolean bookKeeping[] = new boolean[n+1]; // by default boolean primitive are assigned as false

        for (int i = 2; i*i <= n; i++) {
            // if flag is not changed then it is prime
            if(bookKeeping[i] == false) {
                // update all the multiples of i as non-prime number
                for (int j = i*2; j <= n; j += i) {
                    bookKeeping[j] = true;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            if(bookKeeping[i] == false)
                System.out.println(i);
        }

    }

}
