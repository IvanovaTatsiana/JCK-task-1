package com.innowise.jck.arraytask.service.impl;

import com.innowise.jck.arraytask.service.ArrayCalculationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;

public class ArrayCalculationServiceImpl implements ArrayCalculationService {
  private static final Logger logger = LogManager.getLogger(ArrayCalculationServiceImpl.class);

  @Override
  public int findMin(int[] array) {
    logger.debug("Calculating min primitive element");
    if (array == null || array.length == 0) return 0;
    return Arrays.stream(array).min().orElse(0);
  }

  @Override
  public int findMax(int[] array) {
    logger.debug("Calculating max primitive element");
    if (array == null || array.length == 0) return 0;
    return Arrays.stream(array).max().orElse(0);
  }

  @Override
  public int calculateSum(int[] array) {
    logger.debug("Summing total matrix state");
    if (array == null) return 0;
    return Arrays.stream(array).sum();
  }

  @Override
  public double calculateAverage(int[] array) {
    logger.debug("Calculating mean state");
    if (array == null || array.length == 0) return 0.0;
    return Arrays.stream(array).average().orElse(0.0);
  }
}
