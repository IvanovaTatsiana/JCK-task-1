package com.innowise.jck.arraytask.service;

import com.innowise.jck.arraytask.service.impl.ArrayCalculationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayCalculationServiceTest {
  private ArrayCalculationService calculationService;

  @BeforeEach
  void setUp() {
    calculationService = new ArrayCalculationServiceImpl();
  }

  @Test
  void testFindMin() {
    int[] array = {5, 2, 9, -3, 3};
    assertEquals(-3, calculationService.findMin(array));
  }

  @Test
  void testFindMax() {
    int[] array = {5, 2, 9, -3, 3};
    assertEquals(9, calculationService.findMax(array));
  }

  @Test
  void testCalculateSum() {
    int[] array = {1, 2, 3, 4};
    assertEquals(10, calculationService.calculateSum(array));
  }

  @Test
  void testCalculateAverage() {
    int[] array = {1, 2, 3, 4};
    assertEquals(2.5, calculationService.calculateAverage(array), 0.001);
  }
}
