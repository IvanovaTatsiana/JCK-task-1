package org.jck.arraytask.service.impl;

import org.jck.arraytask.entity.CustomArray;
import org.jck.arraytask.service.ArraySortService;

public class ArraySortServiceImpl implements ArraySortService {

  @Override
  public CustomArray bubbleSort(CustomArray customArray) {
    if (customArray == null) {
      return null;
    }

    int[] array = customArray.getElements();
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

    return new CustomArray(array);
  }

  @Override
  public CustomArray selectionSort(CustomArray customArray) {
    if (customArray == null) {
      return null;
    }

    int[] array = customArray.getElements();
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

    return new CustomArray(array);
  }
}
