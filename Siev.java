/****************************
* Counting the number of primes below an
* integer n using the Siev of Eratosthenes algorithm
*****************************/

public class Siev {

  private static int count(int n) {
    boolean[] isPrime = new boolean[n + 1];
    int count = 1;

    for (int i = 2; i < n + 1; i++)
      isPrime[i] = true;

    for (int p = 2; p * p < n + 1; p++) {
      if (isPrime[p]) {
        for (int i = p * p; i < n + 1; i += p) {
          if (isPrime[i]) {
            isPrime[i] = false;
            count++;
          }
        }
      }
    }
    return n - count;
  }

  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int primes = count(n);

    System.out.println("Number of primes below n: " + primes);
  }
}
