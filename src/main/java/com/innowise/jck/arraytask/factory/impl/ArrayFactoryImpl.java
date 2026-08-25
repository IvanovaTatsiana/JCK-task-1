package com.innowise.jck.arraytask.factory.impl;

import com.innowise.jck.arraytask.entity.CustomArray;
import com.innowise.jck.arraytask.exception.ArrayTaskException;
import com.innowise.jck.arraytask.factory.ArrayFactory;
import com.innowise.jck.arraytask.util.IdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayFactoryImpl implements ArrayFactory {
  private static final Logger logger = LogManager.getLogger(ArrayFactoryImpl.class);

  @Override
  public CustomArray createArray(int[] elements) throws ArrayTaskException {
    if (elements == null || elements.length == 0) {
      logger.error("Failed to create CustomArray: source array is empty or null");
      throw new ArrayTaskException("Cannot create CustomArray: source array is empty or null");
    }
    String generatedId = IdGenerator.generateId();
    logger.info("CustomArray successfully created with String ID: {}", generatedId);
    return new CustomArray(generatedId, elements);
  }
}
