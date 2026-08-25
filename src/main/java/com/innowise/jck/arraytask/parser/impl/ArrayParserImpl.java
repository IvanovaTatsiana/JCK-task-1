package com.innowise.jck.arraytask.parser.impl;

import com.innowise.jck.arraytask.exception.ArrayTaskException;
import com.innowise.jck.arraytask.parser.ArrayParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayParserImpl implements ArrayParser {
  private static final Logger logger = LogManager.getLogger(ArrayParserImpl.class);
  private static final String DELIMITER_REGEX = "[\\s,;]+";

  @Override
  public int[] parseToArray(String line) throws ArrayTaskException {
    if (line == null) {
      logger.error("Parsing failed: line is null");
      throw new ArrayTaskException("Line cannot be null");
    }
    String trimmed = line.trim();
    logger.info("Parsing line sequence: [{}]", trimmed);
    String[] stringNumbers = trimmed.split(DELIMITER_REGEX);
    int size = stringNumbers.length;
    int[] resultNumbers = new int[size];
    try {
      for (int i = 0; i < size; i++) {
        resultNumbers[i] = Integer.parseInt(stringNumbers[i]);
      }
    } catch (NumberFormatException e) {
      logger.error("Parsing error: line contains non-integer values: {}", e.getMessage());
      throw new ArrayTaskException("Error converting text to number", e);
    }
    return resultNumbers;
  }
}
