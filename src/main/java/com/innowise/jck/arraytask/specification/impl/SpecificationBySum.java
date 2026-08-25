package com.innowise.jck.arraytask.specification.impl;

import com.innowise.jck.arraytask.entity.CustomArray;
import com.innowise.jck.arraytask.specification.Specification;
import com.innowise.jck.arraytask.warehouse.ArrayStats;
import com.innowise.jck.arraytask.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpecificationBySum extends Specification {
  private static final Logger logger = LogManager.getLogger(SpecificationBySum.class);
  private final int sumLimit;

  public SpecificationBySum(int sumLimit) {
    this.sumLimit = sumLimit;
  }

  @Override
  public boolean specify(CustomArray customArray) {
    logger.debug("Filtering model by specification bounds on summary elements data");
    ArrayStats stats = Warehouse.getInstance().get(customArray.getId());
    return stats != null && stats.getSum() > sumLimit;
  }
}
