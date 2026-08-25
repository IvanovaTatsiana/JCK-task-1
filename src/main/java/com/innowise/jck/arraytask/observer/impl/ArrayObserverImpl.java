package com.innowise.jck.arraytask.observer.impl;

import com.innowise.jck.arraytask.entity.CustomArray;
import com.innowise.jck.arraytask.observer.ArrayObserver;
import com.innowise.jck.arraytask.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayObserverImpl implements ArrayObserver {
  private static final Logger logger = LogManager.getLogger(ArrayObserverImpl.class);

  @Override
  public void update(String id, int[] array) {
    if (id == null || array == null) {
      logger.error(
          "Observer state update rejected: identification key or elements matrix is missing (null)");
      return;
    }
    logger.info("Observer verified dynamic update action tracking context for ID: {}", id);
    try {
      Warehouse.getInstance().changeArrayStats(id, array);
    } catch (Exception e) {
      logger.error(
          "Internal processing operation failure inside warehouse data update tier for ID: " + id,
          e);
    }
  }

  @Override
  public void add(CustomArray array) {
    if (array == null) {
      logger.error(
          "Observer layer tracking assignment rejected: provided reference target is missing (null)");
      return;
    }
    logger.info(
        "Registering monitoring lifecycle triggers context targeting ID: {}", array.getId());
    array.setObserver(this);
    try {
      Warehouse.getInstance().changeArrayStats(array.getId(), array.getElements());
    } catch (Exception e) {
      logger.error(
          "Failed to execute initial metrics calculation layout mapping for newly registered ID: "
              + array.getId(),
          e);
    }
  }

  @Override
  public void remove(String id) {
    if (id == null) {
      logger.warn(
          "Observer deregistration processing dropped: missing target identity string key reference");
      return;
    }
    logger.info(
        "Evicting properties references inside system cache tracking memory tier for ID: {}", id);
    Warehouse.getInstance().remove(id);
  }
}
