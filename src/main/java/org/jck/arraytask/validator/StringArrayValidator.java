package org.jck.arraytask.validator;

public class StringArrayValidator {

  private static final String ARRAY_REGEX = "^[+-]?\\d+([\\s,;]+[+-]?\\d+)*$";

  public boolean isValid(String line) {

    if (line == null || line.isBlank()) {
      return false;
    }

    String trimmed = line.trim();

    return trimmed.matches(ARRAY_REGEX);
  }
}
