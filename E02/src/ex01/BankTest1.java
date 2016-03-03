package ex01;

import java.util.ArrayList;
import java.util.Collection;

class BankAccount {
  private int balance = 0;

  public synchronized void deposit(int amount) {
    balance += amount;
    notifyAll();
  }

  public synchronized void withdraw(int amount) throws InterruptedException {
    while (amount > balance) {
      wait();
    }
    balance -= amount;
  }

  public synchronized int getBalance() {
    return balance;
  }
}

class BankCustomer extends Thread {
  private static final int NOF_TRANSACTIONS = 10000000;
  private final BankAccount account;

  public BankCustomer(BankAccount account) {
    this.account = account;
  }

  @Override
  public void run() {
    for (int k = 0; k < NOF_TRANSACTIONS; k++) {
      try {
        account.deposit(100);
        account.withdraw(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

public class BankTest1 {
  private static final int NOF_CUSTOMERS = 10;

  public static void main(String[] args) throws InterruptedException {
    long startTime = System.currentTimeMillis();

    Collection<BankCustomer> threads = new ArrayList<>();
    BankAccount account = new BankAccount();

    for (int i = 0; i < NOF_CUSTOMERS; i++) {
      BankCustomer bc = new BankCustomer(account);
      threads.add(bc);
      bc.start();
    }

    for (BankCustomer bc : threads) {
      bc.join();
    }

    long endTime = System.currentTimeMillis();
    System.out.println("Time: " + (endTime - startTime) + "ms");
  }
}
