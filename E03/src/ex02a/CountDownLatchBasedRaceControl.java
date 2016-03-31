package ex02a;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchBasedRaceControl extends AbstractRaceControl {
  private int startingGridReady = NOF_RACE_CARS;
  private int waitingForLapOfHonor = NOF_RACE_CARS;
  CountDownLatch carsReady = new CountDownLatch(startingGridReady);
  CountDownLatch startSignal = new CountDownLatch(1);

  @Override
  protected void waitForAllToBeReady() throws InterruptedException {
    carsReady.await();
  }

  @Override
  public void readyToStart() {
    carsReady.countDown();
  }

  @Override
  protected void giveStartSignal() {
    startSignal.countDown();
  }

  @Override
  public void waitForStartSignal() throws InterruptedException {
    startSignal.await();
  }

  @Override
  protected void waitForFinishing() throws InterruptedException {

  }

  @Override
  public boolean isOver() {
    return false;
  }

  @Override
  public void passFinishLine() {

  }

  @Override
  public void waitForLapOfHonor() throws InterruptedException {

  }
}
