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
    // given
    int[] rawNumbers = {1, 2, 3, 4, 5};

    // when
    CustomArray actualArray = factory.createArray(rawNumbers);

    // then
    Assertions.assertNotNull(actualArray);
    Assertions.assertArrayEquals(rawNumbers, actualArray.getElements());
  }

  @Test
  public void testCreateArrayShouldThrowExceptionForNullInput() {
    // given
    int[] nullNumbers = null;

    // when & then
    Assertions.assertThrows(
        ArrayTaskException.class,
        () -> {
          factory.createArray(nullNumbers);
        });
  }

  @Test
  public void testCreateArrayShouldThrowExceptionForEmptyInput() {
    // given
    int[] emptyNumbers = new int[0];

    // when & then
    Assertions.assertThrows(
        ArrayTaskException.class,
        () -> {
          factory.createArray(emptyNumbers);
        });
  }
}
