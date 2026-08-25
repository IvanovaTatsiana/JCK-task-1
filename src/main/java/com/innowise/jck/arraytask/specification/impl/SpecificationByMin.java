package com.innowise.jck.arraytask.specification.impl;

import com.innowise.jck.arraytask.entity.CustomArray;
import com.innowise.jck.arraytask.specification.Specification;
import com.innowise.jck.arraytask.warehouse.Warehouse;

public class SpecificationByMin implements Specification {
  private final int minLimit;

  public SpecificationByMin(int minLimit) {
    this.minLimit = minLimit;
  }

  @Override
  public boolean specify(CustomArray customArray) {
    var stats = Warehouse.getInstance().get(customArray.getId());
    return stats != null && stats.getMin() >= minLimit;
  }
}
