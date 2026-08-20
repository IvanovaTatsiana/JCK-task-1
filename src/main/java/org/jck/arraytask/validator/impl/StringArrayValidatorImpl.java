package org.jck.arraytask.validator.impl;

import org.jck.arraytask.validator.StringArrayValidator;

public class StringArrayValidatorImpl implements StringArrayValidator {

  private static final String ARRAY_REGEX = "^[+-]?\\d+([\\s,;]+[+-]?\\d+)*$";

  @Override
  public boolean isValid(String line) {
    if (line == null || line.isBlank()) {
      return false;
    }
    String trimmed = line.trim();
    return trimmed.matches(ARRAY_REGEX);
  }
}
