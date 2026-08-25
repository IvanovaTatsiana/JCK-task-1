package com.innowise.jck.arraytask.specification.impl;

import com.innowise.jck.arraytask.entity.CustomArray;
import com.innowise.jck.arraytask.specification.Specification;
import com.innowise.jck.arraytask.warehouse.Warehouse;

public class SpecificationBySum implements Specification {
  private final int sumLimit;

  public SpecificationBySum(int sumLimit) {
    this.sumLimit = sumLimit;
  }

  @Override
  public boolean specify(CustomArray customArray) {
    var stats = Warehouse.getInstance().get(customArray.getId());
    return stats != null && stats.getSum() > sumLimit;
  }
}
