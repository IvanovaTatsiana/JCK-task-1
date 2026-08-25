package com.innowise.jck.arraytask.specification.impl;

import com.innowise.jck.arraytask.entity.CustomArray;
import com.innowise.jck.arraytask.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpecificationById extends Specification {
  private static final Logger logger = LogManager.getLogger(SpecificationById.class);
  private final String targetId;

  public SpecificationById(String targetId) {
    this.targetId = targetId;
  }

  @Override
  public boolean specify(CustomArray customArray) {
    logger.debug("Filtering model by specification identity rules targeting: {}", targetId);
    return customArray.getId().equals(targetId);
  }
}
