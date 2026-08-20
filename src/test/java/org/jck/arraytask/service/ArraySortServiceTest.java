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
    // given
    int[] rawNumbers = {5, -1, 3, 12, 0};
    CustomArray customArray = new CustomArray(rawNumbers);
    int[] expectedNumbers = {-1, 0, 3, 5, 12};

    // when
    CustomArray actualArray = sortService.bubbleSort(customArray);

    // then
    Assertions.assertNotNull(actualArray);
    Assertions.assertArrayEquals(expectedNumbers, actualArray.getElements());
  }

  @Test
  public void testSelectionSortSuccess() {
    // given
    int[] rawNumbers = {99, -50, 0, 15, -3};
    CustomArray customArray = new CustomArray(rawNumbers);
    int[] expectedNumbers = {-50, -3, 0, 15, 99};

    // when
    CustomArray actualArray = sortService.selectionSort(customArray);

    // then
    Assertions.assertNotNull(actualArray);
    Assertions.assertArrayEquals(expectedNumbers, actualArray.getElements());
  }
}
