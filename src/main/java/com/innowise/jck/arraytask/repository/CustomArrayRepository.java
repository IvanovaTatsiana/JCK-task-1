package com.innowise.jck.arraytask.repository;

import com.innowise.jck.arraytask.entity.CustomArray;
import com.innowise.jck.arraytask.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomArrayRepository {
  private static final Logger logger = LogManager.getLogger(CustomArrayRepository.class);
  private static CustomArrayRepository instance;

  private final List<CustomArray> arrays = new ArrayList<>();

  private CustomArrayRepository() {}

  public static CustomArrayRepository getInstance() {
    if (instance == null) {
      instance = new CustomArrayRepository();
    }
    return instance;
  }

  public void add(CustomArray array) {
    logger.info(
        "Injecting instance layer model reference block configuration layout into database memory list map for ID: {}",
        array.getId());
    arrays.add(array);
  }

  public void remove(CustomArray array) {
    logger.info(
        "Evicting instance layer model reference block configuration layout out of database memory list map for ID: {}",
        array.getId());
    arrays.remove(array);
  }

  public List<CustomArray> query(Specification specification) {
    logger.info(
        "Evaluating filtering loops execution algorithms for structural collections repository tier matching setup rules");
    List<CustomArray> result = new ArrayList<>();
    for (CustomArray array : arrays) {
      if (specification.specify(array)) {
        result.add(array);
      }
    }
    return result;
  }

  public List<CustomArray> sort(Comparator<CustomArray> comparator) {
    logger.info(
        "Applying row sequencing mapping evaluation algorithms against storage layer entities");
    List<CustomArray> copy = new ArrayList<>(arrays);
    copy.sort(comparator);
    return copy;
  }

  public List<CustomArray> getAll() {
    return new ArrayList<>(arrays);
  }
}
