package ex03;

import java.util.ArrayList;
import java.util.Collection;

public class PrimeCounter {
  private static final long START = 1_000_000L;
  private static final long END = 10_000_000L;
  private static final long NUM_OF_THREADS = Runtime.getRuntime().availableProcessors();

  public static void main(String[] args) throws InterruptedException {
    long result = 0;

    long startTime = System.currentTimeMillis();

    Collection<PrimeCounterThread> threads = new ArrayList<>();

    final long range = (END - START) / NUM_OF_THREADS;

    for (int i = 0; i < NUM_OF_THREADS; i++) {
      long start = START + i * range;
      long end = start + range;

      System.out.printf("Starting thread %d to count primes in the range [%d, %d]\n", i, start, end);

      PrimeCounterThread thread = new PrimeCounterThread(start, end);
      threads.add(thread);
      thread.start();
    }

    for (PrimeCounterThread thread : threads) {
      thread.join();
      result += thread.getCount();
    }

    long endTime = System.currentTimeMillis();
    System.out.println("#Primes: " + result + "\tTime: " + (endTime - startTime) + "ms");
  }
}
