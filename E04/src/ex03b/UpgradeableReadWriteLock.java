package ex03b;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UpgradeableReadWriteLock {
  private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
  private Lock monitor = new ReentrantLock();

  public void readLock() throws InterruptedException {
    readWriteLock.readLock().lock();
  }

  public void readUnlock() {
    readWriteLock.readLock().unlock();
  }

  public void upgradeableReadLock() throws InterruptedException {
    monitor.lock();
  }

  public void upgradeableReadUnlock() {
    monitor.unlock();
  }

  public void writeLock() throws InterruptedException {
    monitor.lock();
    readWriteLock.writeLock().lock();
  }

  public void writeUnlock() {
    readWriteLock.writeLock().unlock();
    monitor.unlock();
  }
}
