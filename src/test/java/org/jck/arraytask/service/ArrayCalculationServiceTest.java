package org.jck.arraytask.service;

import org.jck.arraytask.entity.CustomArray;
import org.jck.arraytask.service.impl.ArrayCalculationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.OptionalDouble;
import java.util.OptionalInt;

public class ArrayCalculationServiceTest {

  private ArrayCalculationService calculationService;

  @BeforeEach
  public void setUp() {
    calculationService = new ArrayCalculationServiceImpl();
  }

  @Test
  public void testFindMinSuccess() {

    int[] rawNumbers = {10, -5, 20, 0, 35};

    CustomArray customArray = new CustomArray(rawNumbers);
    int expectedMin = -5;

    OptionalInt actualMinOptional = calculationService.findMin(customArray);

    Assertions.assertTrue(actualMinOptional.isPresent(), "Optional should contain a value");
    int actualMin = actualMinOptional.getAsInt();
    Assertions.assertEquals(expectedMin, actualMin, "Minimum value calculation failed");
  }

  @Test
  public void testFindMaxSuccess() {

    int[] rawNumbers = {10, -5, 20, 0, 35};
    CustomArray customArray = new CustomArray(rawNumbers);
    int expectedMax = 35;

    OptionalInt actualMaxOptional = calculationService.findMax(customArray);

    Assertions.assertTrue(actualMaxOptional.isPresent(), "Optional should contain a value");
    int actualMax = actualMaxOptional.getAsInt();
    Assertions.assertEquals(expectedMax, actualMax, "Maximum value calculation failed");
  }

  @Test
  public void testCalculateSumSuccess() {

    int[] rawNumbers = {1, 2, 3, 4, 5};
    CustomArray customArray = new CustomArray(rawNumbers);
    int expectedSum = 15; // 1+2+3+4+5 = 15

    int actualSum = calculationService.calculateSum(customArray);

    Assertions.assertEquals(expectedSum, actualSum, "Sum calculation failed");
  }

  @Test
  public void testCalculateAverageSuccess() {

    int[] rawNumbers = {1, 2, 3, 4};
    CustomArray customArray = new CustomArray(rawNumbers);
    double expectedAverage = 2.5; // 10 / 4 = 2.5

    OptionalDouble actualAverageOptional = calculationService.calculateAverage(customArray);

    Assertions.assertTrue(actualAverageOptional.isPresent(), "Optional should contain a value");
    double actualAverage = actualAverageOptional.getAsDouble();
    Assertions.assertEquals(expectedAverage, actualAverage, 0.0001, "Average calculation failed");
  }
}
