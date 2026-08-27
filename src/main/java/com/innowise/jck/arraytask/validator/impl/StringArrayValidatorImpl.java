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
      logger.debug("Validation structural criteria check dropped: string reference is empty");
      return false;
    }
    boolean matches = line.strip().matches(ARRAY_REGEX);
    logger.debug("Regular expression matching output for raw row data block: {}", matches);
    return matches;
  }
}
