package org.jck.arraytask.service;

import org.jck.arraytask.entity.CustomArray;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public interface ArrayCalculationService {

  OptionalInt findMin(CustomArray customArray);

  OptionalInt findMax(CustomArray customArray);

  int calculateSum(CustomArray customArray);

  OptionalDouble calculateAverage(CustomArray customArray);
}
