package ex01;

import java.util.concurrent.RecursiveAction;

public class QuickSortTask extends RecursiveAction {
  private final int THRESHOLD = 100_000;
  private int left, right;
  private int[] numberArray;

  public QuickSortTask(int[] numberArray, int left, int right) {
    this.numberArray = numberArray;
    this.left = left;
    this.right = right;
  }

  private void quickSort(int left, int right) {
    // split into two partitions
    int i = left, j = right;
    long m = numberArray[(left + right) / 2];
    do {
      while (numberArray[i] < m) {
        i++;
      }
      while (numberArray[j] > m) {
        j--;
      }
      if (i <= j) {
        int t = numberArray[i];
        numberArray[i] = numberArray[j];
        numberArray[j] = t;
        i++;
        j--;
      }
    } while (i <= j);

    // Recursively sort the two partitions
    if (j - left > THRESHOLD && right - i > THRESHOLD) {
      invokeAll(new QuickSortTask(numberArray, left, j), new QuickSortTask(numberArray, i, right));
    } else {
      if (j > left) {
        quickSort(left, j);
      }
      if (i < right) {
        quickSort(i, right);
      }
    }
  }

  @Override
  protected void compute() {
    quickSort(left, right);
  }
}
