package com.innowise.jck.arraytask.warehouse;

public class ArrayStats {
  private final int min;
  private final int max;
  private final int sum;
  private final double average;

  public ArrayStats(int min, int max, int sum, double average) {
    this.min = min;
    this.max = max;
    this.sum = sum;
    this.average = average;
  }

  public int getMin() {
    return min;
  }

  public int getMax() {
    return max;
  }

  public int getSum() {
    return sum;
  }

  public double getAverage() {
    return average;
  }

  @Override
  public String toString() {
    return String.format("ArrayStats{min=%d, max=%d, sum=%d, avg=%.2f}", min, max, sum, average);
  }
}
