package com.innowise.jck.arraytask.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
  private static final Logger logger = LogManager.getLogger(IdGenerator.class);
  private static final AtomicLong counter = new AtomicLong(1);

  private IdGenerator() {}

  public static String generateId() {
    String id = String.valueOf(counter.getAndIncrement());
    logger.debug("Generated unique ID: {}", id);
    return id;
  }
}
