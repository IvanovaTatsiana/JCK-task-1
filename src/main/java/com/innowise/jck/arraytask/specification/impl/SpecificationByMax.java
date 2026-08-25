package com.innowise.jck.arraytask.specification.impl;

import com.innowise.jck.arraytask.entity.CustomArray;
import com.innowise.jck.arraytask.specification.Specification;
import com.innowise.jck.arraytask.warehouse.Warehouse;

public class SpecificationByMax implements Specification {
  private final int maxLimit;

  public SpecificationByMax(int maxLimit) {
    this.maxLimit = maxLimit;
  }

  @Override
  public boolean specify(CustomArray customArray) {
    var stats = Warehouse.getInstance().get(customArray.getId());
    return stats != null && stats.getMax() <= maxLimit;
  }
}
