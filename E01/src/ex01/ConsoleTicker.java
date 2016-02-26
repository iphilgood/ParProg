package ex01;

public class ConsoleTicker {
  private static void periodTicker(char sign, int intervallMillis) throws InterruptedException {
    while (true) {
      System.out.print(sign);
      Thread.sleep(intervallMillis);
    }
  }

  private static void startMyThread(char sign, int intervallMillis) {
    new Thread(() -> {
      while (true) {
        System.out.print(sign);

        try {
          Thread.sleep(intervallMillis);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  public static void main(String[] args) throws InterruptedException {
    // periodTicker('.', 10);
    // TODO: Concurrent periodTicker('*', 20);

    // a)
    // new ConsoleTickerThread('A', 10).start();
    // new ConsoleTickerThread('B', 10).start();

    // b)
    // new Thread(new ConsoleTickerRunnable('A', 1000)).start();
    // new Thread(new ConsoleTickerRunnable('B', 1000)).start();

    // c)
    startMyThread('A', 100);
    startMyThread('B', 100);
  }
}
