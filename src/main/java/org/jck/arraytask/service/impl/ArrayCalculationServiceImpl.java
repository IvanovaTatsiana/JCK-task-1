package org.jck.arraytask.service.impl;

import org.jck.arraytask.entity.CustomArray;
import org.jck.arraytask.service.ArrayCalculationService;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public class ArrayCalculationServiceImpl implements ArrayCalculationService {

  @Override
  public OptionalInt findMin(CustomArray customArray) {
    if (customArray == null) {
      return OptionalInt.empty();
    }
    int[] array = customArray.getElements();
    if (array.length == 0) {
      return OptionalInt.empty();
    }

    int min = array[0];
    for (int num : array) {
      if (num < min) {
        min = num;
      }
    }
    return OptionalInt.of(min);
  }

  @Override
  public OptionalInt findMax(CustomArray customArray) {
    if (customArray == null) {
      return OptionalInt.empty();
    }
    int[] array = customArray.getElements();
    if (array.length == 0) {
      return OptionalInt.empty();
    }

    int max = array[0];
    for (int num : array) {
      if (num > max) {
        max = num;
      }
    }
    return OptionalInt.of(max);
  }

  @Override
  public int calculateSum(CustomArray customArray) {
    if (customArray == null) {
      return 0;
    }
    int sum = 0;
    for (int num : customArray.getElements()) {
      sum += num;
    }
    return sum;
  }

  @Override
  public OptionalDouble calculateAverage(CustomArray customArray) {
    if (customArray == null) {
      return OptionalDouble.empty();
    }
    int[] array = customArray.getElements();
    if (array.length == 0) {
      return OptionalDouble.empty();
    }

    int sum = calculateSum(customArray);
    return OptionalDouble.of((double) sum / array.length);
  }
}
