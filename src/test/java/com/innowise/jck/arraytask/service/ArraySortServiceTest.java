package com.innowise.jck.arraytask.service;

import com.innowise.jck.arraytask.service.impl.ArraySortServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class ArraySortServiceTest {
  private ArraySortService sortService;

  @BeforeEach
  void setUp() {
    sortService = new ArraySortServiceImpl();
  }

  @Test
  @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
  void testBubbleSort() {
    int[] array = {4, 2, 5, 1, 3};
    sortService.bubbleSort(array);
    assertArrayEquals(new int[] {1, 2, 3, 4, 5}, array);
  }

  @Test
  void testInsertionSort() {
    int[] array = {4, 2, 5, 1, 3};
    sortService.insertionSort(array);
    assertArrayEquals(new int[] {1, 2, 3, 4, 5}, array);
  }

  @Test
  void testSelectionSort() {
    int[] array = {4, 2, 5, 1, 3};
    sortService.selectionSort(array);
    assertArrayEquals(new int[] {1, 2, 3, 4, 5}, array);
  }
}
