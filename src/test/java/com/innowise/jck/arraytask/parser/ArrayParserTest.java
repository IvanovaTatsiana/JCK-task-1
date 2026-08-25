package com.innowise.jck.arraytask.parser;

import com.innowise.jck.arraytask.exception.ArrayTaskException;
import com.innowise.jck.arraytask.parser.impl.ArrayParserImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayParserTest {
  private ArrayParser parser;

  @BeforeEach
  void setUp() {
    parser = new ArrayParserImpl();
  }

  @Test
  void testParseToArraySuccessWithSpaces() throws ArrayTaskException {
    int[] expected = {1, -2, 3};
    int[] actual = parser.parseToArray("1 -2 3");
    assertArrayEquals(expected, actual);
  }

  @Test
  void testParseToArraySuccessWithCommasAndSemicolons() throws ArrayTaskException {
    int[] expected = {10, 20, 30};
    int[] actual = parser.parseToArray("10, 20; 30");
    assertArrayEquals(expected, actual);
  }

  @Test
  void testParseToArrayThrowsExceptionWhenNull() {
    assertThrows(ArrayTaskException.class, () -> parser.parseToArray(null));
  }

  @Test
  void testParseToArrayThrowsExceptionWhenInvalidData() {
    assertThrows(ArrayTaskException.class, () -> parser.parseToArray("1 a 3"));
  }
}
