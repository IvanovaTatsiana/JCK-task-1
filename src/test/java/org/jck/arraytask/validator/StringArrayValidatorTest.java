package org.jck.arraytask.validator;

import org.jck.arraytask.validator.impl.StringArrayValidatorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class StringArrayValidatorTest {

  private StringArrayValidator validator;

  @BeforeEach
  public void setUp() {
    validator = new StringArrayValidatorImpl();
  }

  @ParameterizedTest
  @ValueSource(strings = {"1 2 3 4 5", "10;20;30;40", "1, 2, 3", "-5, 10, +15", "42"})
  public void testIsValidShouldReturnTrueForValidLines(String validLine) {
    // given

    // when
    boolean actualResult = validator.isValid(validLine);

    // then
    Assertions.assertTrue(actualResult);
  }

  @ParameterizedTest
  @ValueSource(strings = {"1, 2, x3, 5", "6..5 7 8", "11- 2 42-", "", "   ", "1, 2, – 42"})
  public void testIsValidShouldReturnFalseForInvalidLines(String invalidLine) {
    // given

    // when
    boolean actualResult = validator.isValid(invalidLine);

    // then
    Assertions.assertFalse(actualResult);
  }

  @Test
  public void testIsValidShouldReturnFalseForNullLine() {
    // given
    String nullLine = null;

    // when
    boolean actualResult = validator.isValid(nullLine);

    // then
    Assertions.assertFalse(actualResult);
  }
}
