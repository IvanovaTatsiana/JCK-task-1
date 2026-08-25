package com.innowise.jck.arraytask.factory;

import com.innowise.jck.arraytask.entity.CustomArray;
import com.innowise.jck.arraytask.exception.ArrayTaskException;
import com.innowise.jck.arraytask.factory.impl.ArrayFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayFactoryTest {
  private ArrayFactory factory;

  @BeforeEach
  void setUp() {
    factory = new ArrayFactoryImpl();
  }

  @Test
  void testCreateArraySuccess() throws ArrayTaskException {
    int[] source = {1, 2, 3};
    CustomArray result = factory.createArray(source);

    assertNotNull(result);
    assertNotNull(result.getId());
    assertArrayEquals(source, result.getElements());
  }

  @Test
  void testCreateArrayThrowsExceptionWhenNull() {
    assertThrows(ArrayTaskException.class, () -> factory.createArray(null));
  }

  @Test
  void testCreateArrayThrowsExceptionWhenEmpty() {
    assertThrows(ArrayTaskException.class, () -> factory.createArray(new int[0]));
  }
}
