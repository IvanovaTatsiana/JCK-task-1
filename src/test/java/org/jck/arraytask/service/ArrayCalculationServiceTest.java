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
    // given
    int[] rawNumbers = {10, -5, 20, 0, 35};
    CustomArray customArray = new CustomArray(rawNumbers);
    int expectedMin = -5;

    // when
    OptionalInt actualMinOptional = calculationService.findMin(customArray);

    // then
    Assertions.assertTrue(actualMinOptional.isPresent());
    Assertions.assertEquals(expectedMin, actualMinOptional.getAsInt());
  }

  @Test
  public void testFindMaxSuccess() {
    // given
    int[] rawNumbers = {10, -5, 20, 0, 35};
    CustomArray customArray = new CustomArray(rawNumbers);
    int expectedMax = 35;

    // when
    OptionalInt actualMaxOptional = calculationService.findMax(customArray);

    // then
    Assertions.assertTrue(actualMaxOptional.isPresent());
    Assertions.assertEquals(expectedMax, actualMaxOptional.getAsInt());
  }

  @Test
  public void testCalculateSumSuccess() {
    // given
    int[] rawNumbers = {1, 2, 3, 4, 5};
    CustomArray customArray = new CustomArray(rawNumbers);
    int expectedSum = 15;

    // when
    int actualSum = calculationService.calculateSum(customArray);

    // then
    Assertions.assertEquals(expectedSum, actualSum);
  }

  @Test
  public void testCalculateAverageSuccess() {
    // given
    int[] rawNumbers = {1, 2, 3, 4};
    CustomArray customArray = new CustomArray(rawNumbers);
    double expectedAverage = 2.5;

    // when
    OptionalDouble actualAverageOptional = calculationService.calculateAverage(customArray);

    // then
    Assertions.assertTrue(actualAverageOptional.isPresent());
    Assertions.assertEquals(expectedAverage, actualAverageOptional.getAsDouble(), 0.0001);
  }
}
