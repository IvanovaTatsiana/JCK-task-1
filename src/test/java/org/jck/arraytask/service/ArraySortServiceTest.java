package org.jck.arraytask.service;

import org.jck.arraytask.entity.CustomArray;
import org.jck.arraytask.service.impl.ArraySortServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArraySortServiceTest {

  private ArraySortService sortService;

  @BeforeEach
  public void setUp() {

    sortService = new ArraySortServiceImpl();
  }

  @Test
  public void testBubbleSortSuccess() {

    int[] rawNumbers = {5, -1, 3, 12, 0};
    CustomArray customArray = new CustomArray(rawNumbers);
    int[] expectedNumbers = {-1, 0, 3, 5, 12};

    CustomArray actualArray = sortService.bubbleSort(customArray);

    Assertions.assertNotNull(actualArray, "Resulting CustomArray should not be null");
    Assertions.assertArrayEquals(
        expectedNumbers, actualArray.getElements(), "Bubble sort algorithm failed");
  }

  @Test
  public void testSelectionSortSuccess() {

    int[] rawNumbers = {99, -50, 0, 15, -3};
    CustomArray customArray = new CustomArray(rawNumbers);
    int[] expectedNumbers = {-50, -3, 0, 15, 99};

    CustomArray actualArray = sortService.selectionSort(customArray);

    Assertions.assertNotNull(actualArray, "Resulting CustomArray should not be null");
    Assertions.assertArrayEquals(
        expectedNumbers, actualArray.getElements(), "Selection sort algorithm failed");
  }
}
