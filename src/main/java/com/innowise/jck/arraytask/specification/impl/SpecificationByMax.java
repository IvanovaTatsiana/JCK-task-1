package com.innowise.jck.arraytask.specification.impl;

import com.innowise.jck.arraytask.entity.CustomArray;
import com.innowise.jck.arraytask.specification.Specification;
import com.innowise.jck.arraytask.warehouse.ArrayStats;
import com.innowise.jck.arraytask.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpecificationByMax extends Specification {
  private static final Logger logger = LogManager.getLogger(SpecificationByMax.class);
  private final int maxLimit;

  public SpecificationByMax(int maxLimit) {
    this.maxLimit = maxLimit;
  }

  @Override
  public boolean specify(CustomArray customArray) {
    logger.debug("Filtering model by specification bounds on max values");
    ArrayStats stats = Warehouse.getInstance().get(customArray.getId());
    return stats != null && stats.getMax() <= maxLimit;
  }
}
