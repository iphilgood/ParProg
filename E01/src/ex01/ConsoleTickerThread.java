package ex01;

public class ConsoleTickerThread extends Thread {
  private char sign;
  private int intervallMillis;

  public ConsoleTickerThread(char sign, int intervallMillis) {
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
