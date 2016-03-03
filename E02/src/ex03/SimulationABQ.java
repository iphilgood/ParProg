package ex03;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

class ProducerABQ extends Thread {
  private final ArrayBlockingQueue<Long> buffer;
  private final int nofItems;

  public ProducerABQ(ArrayBlockingQueue<Long> buffer, int nofItems) {
    this.buffer = buffer;
    this.nofItems = nofItems;
  }

  public void run() {
    Random random = new Random();
    for (int i = 0; i < nofItems; i++) {
      try {
        buffer.put(random.nextLong());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("Producer finished " + getName());
  }
}

class ConsumerABQ extends Thread {
  private final ArrayBlockingQueue<Long> buffer;
  private final int nofItems;

  public ConsumerABQ(ArrayBlockingQueue<Long> buffer, int nofItems) {
    this.buffer = buffer;
    this.nofItems = nofItems;
  }

  public void run() {
    for (int i = 0; i < nofItems; i++) {
      try {
        buffer.take();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("Consumer finished " + getName());
  }
}

public class SimulationABQ {
  private static final int NOF_PRODUCERS = 1;
  private static final int NOF_CONSUMERS = 1;
  private static final int BUFFER_CAPACITY = 1;
  // TOTAL_ELEMENTS must be a multiple of ELEMENTS_PER_PRODUCER and ELEMENTS_PER_CONSUMER
  private static final int TOTAL_ELEMENTS = 1000000;
  private static final int ELEMENTS_PER_PRODUCER = TOTAL_ELEMENTS / NOF_PRODUCERS;
  private static final int ELEMENTS_PER_CONSUMER = TOTAL_ELEMENTS / NOF_CONSUMERS;

  public static void main(String[] args) throws InterruptedException {
    List<Thread> threads = new ArrayList<Thread>();
    ArrayBlockingQueue<Long> buffer = new ArrayBlockingQueue<Long>(BUFFER_CAPACITY);
    long startTime = System.currentTimeMillis();
    for (int i = 0; i < NOF_PRODUCERS; i++) {
      threads.add(new ProducerABQ(buffer, ELEMENTS_PER_PRODUCER));
    }
    for (int i = 0; i < NOF_CONSUMERS; i++) {
      threads.add(new ConsumerABQ(buffer, ELEMENTS_PER_CONSUMER));
    }
    for (Thread thread: threads) {
      thread.start();
    }
    for (Thread thread: threads) {
      thread.join();
    }
    long stopTime = System.currentTimeMillis();
    System.out.println("Producer-consumer simulation finished");
    System.out.println("Total time: " + (stopTime - startTime) + " ms");
  }
}
