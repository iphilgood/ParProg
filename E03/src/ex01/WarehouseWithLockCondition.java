package ex01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WarehouseWithLockCondition implements Warehouse {
  private int capacity;
  private int size;
  private Lock monitor;
  private Condition nonFull;
  private Condition nonEmpty;

  public WarehouseWithLockCondition(int capacity, boolean fair) {
    this.capacity = capacity;
    this.monitor = new ReentrantLock(fair);
    this.nonFull = monitor.newCondition();
    this.nonEmpty = monitor.newCondition();
    this.size = 0;
  }

  @Override
  public void put(int amount) throws InterruptedException {
    monitor.lock();
    try {
      while (size + amount > capacity) {
        nonFull.await();
      }
      size += amount;
      nonEmpty.signalAll();
    } finally {
      monitor.unlock();
    }
  }

  @Override
  public void get(int amount) throws InterruptedException {
    monitor.lock();
    try {
      while (size - amount < 0) {
        nonEmpty.await();
      }
      size -= amount;
      nonFull.signalAll();
    } finally {
      monitor.unlock();
    }
  }
}
