package ex03;

public class PrimeCounterThread extends Thread {
  private long start;
  private long end;
  private long count = 0;

  public PrimeCounterThread(long start, long end) {
    this.start = start;
    this.end = end;
  }

  private static boolean isPrime(long number) {
    for (long factor = 2; factor * factor <= number; factor++) {
      if (number % factor == 0) {
        return false;
      }
    }
    return true;
  }

  public long getCount() {
    return count;
  }

  @Override
  public void run() {
    for (long number = start; number < end; number++) {
      if (isPrime(number)) {
        count++;
      }
    }
  }
}
