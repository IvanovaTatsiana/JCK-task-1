package com.innowise.jck.arraytask.comparator;

import com.innowise.jck.arraytask.entity.CustomArray;
import java.util.Comparator;

public enum CustomArrayComparator implements Comparator<CustomArray> {
  ID {
    @Override
    public int compare(CustomArray a1, CustomArray a2) {
      return a1.getId().compareTo(a2.getId());
    }
  },
  SIZE {
    @Override
    public int compare(CustomArray a1, CustomArray a2) {
      return Integer.compare(a1.getArraySize(), a2.getArraySize());
    }
  },
  FIRST_ELEMENT {
    @Override
    public int compare(CustomArray a1, CustomArray a2) {
      return Integer.compare(a1.getFirstElement(), a2.getFirstElement());
    }
  }
}
