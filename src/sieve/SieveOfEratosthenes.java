package sieve;

public class SieveOfEratosthenes {

    final boolean bookKeeping[];

    public SieveOfEratosthenes(int n) {
        bookKeeping = new boolean[n+1]; // by default boolean primitive are assigned as false
    }


    public boolean[] process() {

        for (int i = 2; i*i < bookKeeping.length; i++) {
            // if flag is not changed then it is prime
            if(bookKeeping[i] == false) {
                // update all the multiples of i as non-prime number
                for (int j = i*2; j < bookKeeping.length; j += i) {
                    bookKeeping[j] = true;
                }
            }
        }
        return bookKeeping;
    }

}
