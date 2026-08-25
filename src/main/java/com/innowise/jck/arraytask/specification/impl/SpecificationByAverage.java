package com.innowise.jck.arraytask.specification.impl;

import com.innowise.jck.arraytask.entity.CustomArray;
import com.innowise.jck.arraytask.specification.Specification;
import com.innowise.jck.arraytask.warehouse.ArrayStats;
import com.innowise.jck.arraytask.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpecificationByAverage extends Specification {
  private static final Logger logger = LogManager.getLogger(SpecificationByAverage.class);
  private final double averageLimit;

  public SpecificationByAverage(double averageLimit) {
    this.averageLimit = averageLimit;
  }

  @Override
  public boolean specify(CustomArray customArray) {
    logger.debug("Filtering model by specification bounds on mean elements evaluation");
    ArrayStats stats = Warehouse.getInstance().get(customArray.getId());
    return stats != null && stats.getAverage() > averageLimit;
  }
}
