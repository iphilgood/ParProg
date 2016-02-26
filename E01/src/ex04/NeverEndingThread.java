package ex04;

public class NeverEndingThread extends Thread {

  @Override
  public void run() {
    try {
      while (true) {
        Thread.sleep(1000000);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
