package ex01;

public class ConsoleTickerRunnable implements Runnable {
  private char sign;
  private int intervallMillis;

  public ConsoleTickerRunnable(char sign, int intervallMillis) {
    this.sign = sign;
    this.intervallMillis = intervallMillis;
  }

  @Override
  public void run() {
    while (true) {
      System.out.print(sign);

      try {
        Thread.sleep(intervallMillis);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
