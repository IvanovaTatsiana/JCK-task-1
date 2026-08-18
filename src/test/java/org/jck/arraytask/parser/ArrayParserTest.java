package org.jck.arraytask.parser;

import org.jck.arraytask.exception.ArrayTaskException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayParserTest {

  private ArrayParser parser;

  @BeforeEach
  public void setUp() {

    parser = new ArrayParser();
  }

  @Test
  public void testParseToArraySuccessWithSemicolons() throws ArrayTaskException {

    String validLine = "10;20;30";
    int[] expectedNumbers = {10, 20, 30};

    int[] actualNumbers = parser.parseToArray(validLine);

    Assertions.assertNotNull(actualNumbers, "The parsed array should not be null");
    Assertions.assertArrayEquals(
        expectedNumbers,
        actualNumbers,
        "Parser failed to split and convert semicolon-separated data");
  }

  @Test
  public void testParseToArraySuccessWithMixedSpacesAndCommas() throws ArrayTaskException {

    String validLine = "  1,   2, 3  ";
    int[] expectedNumbers = {1, 2, 3};

    int[] actualNumbers = parser.parseToArray(validLine);

    Assertions.assertArrayEquals(
        expectedNumbers,
        actualNumbers,
        "Parser failed to process mixed spaces and commas with trailing gaps");
  }

  @Test
  public void testParseToArrayShouldThrowExceptionForInvalidData() {

    String invalidLine = "10 20 x30";

    Assertions.assertThrows(
        ArrayTaskException.class,
        () -> {
          parser.parseToArray(invalidLine);
        },
        "Parser should throw ArrayTaskException when encountering non-numeric text data");
  }

  @Test
  public void testParseToArrayShouldThrowExceptionForNullInput() {

    String nullLine = null;

    Assertions.assertThrows(
        ArrayTaskException.class,
        () -> {
          parser.parseToArray(nullLine);
        },
        "Parser should throw ArrayTaskException when the input line is completely null");
  }
}
