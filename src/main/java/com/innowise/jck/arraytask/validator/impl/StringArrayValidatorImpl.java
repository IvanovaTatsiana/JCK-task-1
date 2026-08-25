package com.innowise.jck.arraytask.validator.impl;

import com.innowise.jck.arraytask.validator.StringArrayValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StringArrayValidatorImpl implements StringArrayValidator {
  private static final Logger logger = LogManager.getLogger(StringArrayValidatorImpl.class);
  private static final String ARRAY_REGEX = "^[+-]?\\d+([\\s,;]+[+-]?\\d+)*$";

  @Override
  public boolean isValid(String line) {
    if (line == null || line.isBlank()) {
      logger.debug("Line validation failed: source string is null or empty");
      return false;
    }
    boolean matches = line.trim().matches(ARRAY_REGEX);
    logger.debug("Line [{}] validation result: {}", line.trim(), matches);
    return matches;
  }
}
