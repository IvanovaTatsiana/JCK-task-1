package org.jck.arraytask.parser;

import org.jck.arraytask.exception.ArrayTaskException;

public class ArrayParser {

  private static final String DELIMITER_REGEX = "[\\s,;]+";

  public int[] parseToArray(String line) throws ArrayTaskException {
    if (line == null) {
      throw new ArrayTaskException("Line cannot be null");
    }

    String trimmed = line.trim();
    String[] stringNumbers = trimmed.split(DELIMITER_REGEX);

    int size = stringNumbers.length;
    int[] resultNumbers = new int[size];

    try {
      for (int i = 0; i < size; i++) {
        String currentString = stringNumbers[i];
        int parsedNumber = Integer.parseInt(currentString);
        resultNumbers[i] = parsedNumber;
      }
    } catch (NumberFormatException e) {
      throw new ArrayTaskException("Error converting text to number", e);
    }

    return resultNumbers;
  }
}
