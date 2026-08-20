package org.jck.arraytask.parser;

import org.jck.arraytask.exception.ArrayTaskException;
import org.jck.arraytask.parser.impl.ArrayParserImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayParserTest {

  private ArrayParser parser;

  @BeforeEach
  public void setUp() {
    parser = new ArrayParserImpl();
  }

  @Test
  public void testParseToArraySuccessWithSemicolons() throws ArrayTaskException {
    // given
    String validLine = "10;20;30";
    int[] expectedNumbers = {10, 20, 30};

    // when
    int[] actualNumbers = parser.parseToArray(validLine);

    // then
    Assertions.assertNotNull(actualNumbers);
    Assertions.assertArrayEquals(expectedNumbers, actualNumbers);
  }

  @Test
  public void testParseToArraySuccessWithMixedSpacesAndCommas() throws ArrayTaskException {
    // given
    String validLine = "  1,   2, 3  ";
    int[] expectedNumbers = {1, 2, 3};

    // when
    int[] actualNumbers = parser.parseToArray(validLine);

    // then
    Assertions.assertArrayEquals(expectedNumbers, actualNumbers);
  }

  @Test
  public void testParseToArrayShouldThrowExceptionForInvalidData() {
    // given
    String invalidLine = "10 20 x30";

    // when & then
    Assertions.assertThrows(
        ArrayTaskException.class,
        () -> {
          parser.parseToArray(invalidLine);
        });
  }

  @Test
  public void testParseToArrayShouldThrowExceptionForNullInput() {
    // given
    String nullLine = null;

    // when & then
    Assertions.assertThrows(
        ArrayTaskException.class,
        () -> {
          parser.parseToArray(nullLine);
        });
  }
}
