package com.innowise.jck.arraytask.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IdGenerator {
  private static final Logger logger = LogManager.getLogger(IdGenerator.class);
  private static long counter = 1L;

  private IdGenerator() {}

  public static String generateId() {
    String id = String.valueOf(counter++);
    logger.debug("Generated unique ID: {}", id);
    return id;
  }
}
