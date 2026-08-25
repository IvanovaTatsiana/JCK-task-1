package com.innowise.jck.arraytask.validator;

import com.innowise.jck.arraytask.validator.impl.StringArrayValidatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class StringArrayValidatorTest {
  private StringArrayValidator validator;

  @BeforeEach
  void setUp() {
    validator = new StringArrayValidatorImpl();
  }

  @ParameterizedTest
  @ValueSource(strings = {"1 2 3 -4", "1, 2; 3   4", "+5 -10 0", "  42  "})
  void testIsValidTrueForValidFormats(String validLine) {
    assertTrue(validator.isValid(validLine));
  }

  @Test
  void testIsValidTrueWithSpaces() {
    assertTrue(validator.isValid("1 2 3 -4"));
  }

  @Test
  void testIsValidTrueWithDelimiters() {
    assertTrue(validator.isValid("1, 2; 3   4"));
  }

  @Test
  void testIsValidFalseWithLetters() {
    assertFalse(validator.isValid("1 2 a 4"));
  }

  @Test
  void testIsValidFalseWhenNull() {
    assertFalse(validator.isValid(null));
  }

  @Test
  void testIsValidFalseWhenBlank() {
    assertFalse(validator.isValid("   "));
  }
}
