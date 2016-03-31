package ex02a;

import java.util.Scanner;

public class ConsoleTicker {
  public static void main(String[] args) throws InterruptedException {
    Scanner scanner = new Scanner(System.in);

    Thread trheadA = new ConsoleTickerThread('A', 1000);
    Thread threadB = new ConsoleTickerThread('B', 500);

    // Set thread1 as daemon
    trheadA.setDaemon(true);
    threadB.setDaemon(true);

    trheadA.start();
    threadB.start();

    String x = scanner.next();
    if (!x.isEmpty()) {
      System.out.print("finished");
    }
  }
}
