package org.jck.arraytask.validator;

public class StringArrayValidator {

  private static final String ARRAY_REGEX = "^[+-]?\\d+([\\s,;-]+[+-]?\\d+)*$";

  public boolean isValid(String line) {
    if (line == null) {
      return false;
    }

    String trimmed = line.trim();
    if (trimmed.isEmpty()) {
      return false;
    }

    boolean isMatched = trimmed.matches(ARRAY_REGEX);
    return isMatched;
  }
}
