package com.innowise.jck.arraytask.repository;

import com.innowise.jck.arraytask.entity.CustomArray;
import com.innowise.jck.arraytask.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    logger.info("Pushing item with String ID {} into custom repository tier", array.getId());
    arrays.add(array);
  }

  public void remove(CustomArray array) {
    logger.info("Dropping item with String ID {} out of custom repository tier", array.getId());
    arrays.remove(array);
  }

  public List<CustomArray> query(Specification specification) {
    logger.info("Evaluating state mapping search inside repository tier");
    return arrays.stream().filter(specification::specify).collect(Collectors.toList());
  }

  public List<CustomArray> sort(Comparator<CustomArray> comparator) {
    logger.info("Processing structural collections sorting using target comparator parameters");
    return arrays.stream().sorted(comparator).collect(Collectors.toList());
  }

  public List<CustomArray> getAll() {
    return new ArrayList<>(arrays);
  }
}
