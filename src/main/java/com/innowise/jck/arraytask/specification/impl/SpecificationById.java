package com.innowise.jck.arraytask.specification.impl;

import com.innowise.jck.arraytask.entity.CustomArray;
import com.innowise.jck.arraytask.specification.Specification;

public class SpecificationById implements Specification {
  private final String targetId;

  public SpecificationById(String targetId) {
    this.targetId = targetId;
  }

  @Override
  public boolean specify(CustomArray customArray) {
    return customArray.getId().equals(targetId);
  }
}
