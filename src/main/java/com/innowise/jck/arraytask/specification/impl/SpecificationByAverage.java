package com.innowise.jck.arraytask.specification.impl;

import com.innowise.jck.arraytask.entity.CustomArray;
import com.innowise.jck.arraytask.specification.Specification;
import com.innowise.jck.arraytask.warehouse.Warehouse;

public class SpecificationByAverage implements Specification {
  private final double averageLimit;

  public SpecificationByAverage(double averageLimit) {
    this.averageLimit = averageLimit;
  }

  @Override
  public boolean specify(CustomArray customArray) {
    var stats = Warehouse.getInstance().get(customArray.getId());
    return stats != null && stats.getAverage() > averageLimit;
  }
}
