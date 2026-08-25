package com.innowise.jck.arraytask.service.impl;

import com.innowise.jck.arraytask.service.ArraySortService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArraySortServiceImpl implements ArraySortService {
  private static final Logger logger = LogManager.getLogger(ArraySortServiceImpl.class);

  @Override
  public void bubbleSort(int[] array) {
    if (array == null) return;
    logger.info("Bubble sort activated for primitive sequence size {}", array.length);
    int n = array.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (array[j] > array[j + 1]) {
          int temp = array[j];
          array[j] = array[j + 1];
          array[j + 1] = temp;
        }
      }
    }
  }

  @Override
  public void insertionSort(int[] array) {
    if (array == null) return;
    logger.info("Insertion sort activated for primitive sequence size {}", array.length);
    int n = array.length;
    for (int i = 1; i < n; ++i) {
      int key = array[i];
      int j = i - 1;
      while (j >= 0 && array[j] > key) {
        array[j + 1] = array[j];
        j = j - 1;
      }
      array[j + 1] = key;
    }
  }

  @Override
  public void selectionSort(int[] array) {
    if (array == null) return;
    logger.info("Selection sort activated for primitive sequence size {}", array.length);
    int n = array.length;
    for (int i = 0; i < n - 1; i++) {
      int minIdx = i;
      for (int j = i + 1; j < n; j++) {
        if (array[j] < array[minIdx]) {
          minIdx = j;
        }
      }
      int temp = array[minIdx];
      array[minIdx] = array[i];
      array[i] = temp;
    }
  }
}
