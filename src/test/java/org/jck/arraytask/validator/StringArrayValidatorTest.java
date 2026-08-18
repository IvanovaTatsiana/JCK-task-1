package org.jck.arraytask.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class StringArrayValidatorTest {

  private StringArrayValidator validator;

  @BeforeEach
  public void setUp() {
    validator = new StringArrayValidator();
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "1 2 3 4 5", // Standard spaces
        "10;20;30;40", // Semicolons
        "1, 2, 3", // Commas with spaces
        "-5, 10, +15", // Numbers with signs
        "42" // Single number array
      })
  public void testIsValidShouldReturnTrueForValidLines(String validLine) {

    boolean actualResult = validator.isValid(validLine);

    Assertions.assertTrue(actualResult, "Validator should return true for: " + validLine);
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "1, 2, x3, 5", // Contains a letter
        "6..5 7 8", // Double dots
        "11- 2 42-", // Trailing minus signs
        "", // Empty string
        "   ", // Blank string
        "1, 2, – 42" // Contains long dash symbol
      })
  public void testIsValidShouldReturnFalseForInvalidLines(String invalidLine) {

    boolean actualResult = validator.isValid(invalidLine);

    Assertions.assertFalse(actualResult, "Validator should return false for: " + invalidLine);
  }

  @Test
  public void testIsValidShouldReturnFalseForNullLine() {

    String nullLine = null;

    boolean actualResult = validator.isValid(nullLine);

    Assertions.assertFalse(actualResult, "Validator should return false for null input");
  }
}
