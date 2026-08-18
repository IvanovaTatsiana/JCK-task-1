package org.jck.arraytask.factory;

import org.jck.arraytask.entity.CustomArray;
import org.jck.arraytask.exception.ArrayTaskException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayFactoryTest {

  private ArrayFactory factory;

  @BeforeEach
  public void setUp() {

    factory = new ArrayFactory();
  }

  @Test
  public void testCreateArraySuccess() throws ArrayTaskException {

    int[] rawNumbers = {1, 2, 3, 4, 5};

    CustomArray actualArray = factory.createArray(rawNumbers);

    Assertions.assertNotNull(actualArray, "Factory should not return a null object");
    Assertions.assertArrayEquals(
        rawNumbers, actualArray.getElements(), "Factory elements mismatch");
  }

  @Test
  public void testCreateArrayShouldThrowExceptionForNullInput() {

    int[] nullNumbers = null;

    Assertions.assertThrows(
        ArrayTaskException.class,
        () -> {
          factory.createArray(nullNumbers);
        },
        "Factory should throw ArrayTaskException when the input array is null");
  }

  @Test
  public void testCreateArrayShouldThrowExceptionForEmptyInput() {

    int[] emptyNumbers = new int[0];

    Assertions.assertThrows(
        ArrayTaskException.class,
        () -> {
          factory.createArray(emptyNumbers);
        },
        "Factory should throw ArrayTaskException when the input array is empty");
  }
}
