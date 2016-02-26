package ex04;

public class NeverEndingStory {

  public static void main(String[] args) {
    int count = 0;

    while (true) {
      new NeverEndingThread().start();
      System.out.println("Thread #" + (++count) + " started");
    }
  }
}
