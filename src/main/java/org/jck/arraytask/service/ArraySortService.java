package org.jck.arraytask.service;

import org.jck.arraytask.entity.CustomArray;

public interface ArraySortService {
  CustomArray bubbleSort(CustomArray customArray);

  CustomArray selectionSort(CustomArray customArray);
}
