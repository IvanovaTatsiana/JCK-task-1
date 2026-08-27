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
      logger.error("Parsing context validation failure: row row is null");
      throw new ArrayTaskException("Target context to parse cannot be null");
    }
    String trimmed = line.strip();
    logger.info("Executing token parsing layout logic on string row elements");

    String[] tokens = trimmed.split(DELIMITER_REGEX);
    int[] numbers = new int[tokens.length];
    try {
      for (int i = 0; i < tokens.length; i++) {
        numbers[i] = Integer.parseInt(tokens[i]);
      }
    } catch (NumberFormatException e) {
      logger.error("Conversion mismatch mapping integer for target segment text: " + trimmed, e);
      throw new ArrayTaskException("Row string token block parsing conversion error", e);
    }
    return numbers;
  }
}
