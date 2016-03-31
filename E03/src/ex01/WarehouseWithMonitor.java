package ex01;

public class WarehouseWithMonitor implements Warehouse {
  private final int capacity;
  private int size;

  public WarehouseWithMonitor(int capacity) {
    this.capacity = capacity;
    this.size = 0;
  }

  @Override
  public synchronized void put(int amount) throws InterruptedException {
    while (size + amount > capacity) {
      wait();
    }
    size += amount;
    notifyAll();
  }

  @Override
  public synchronized void get(int amount) throws InterruptedException {
    while (size - amount < 0) {
      wait();
    }
    size -= amount;
    notifyAll();
  }
}
