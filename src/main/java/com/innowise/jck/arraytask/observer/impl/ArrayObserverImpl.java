package com.innowise.jck.arraytask.observer.impl;

import com.innowise.jck.arraytask.entity.CustomArray;
import com.innowise.jck.arraytask.exception.ArrayTaskException;
import com.innowise.jck.arraytask.observer.ArrayObserver;
import com.innowise.jck.arraytask.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayObserverImpl implements ArrayObserver {
  private static final Logger logger = LogManager.getLogger(ArrayObserverImpl.class);

  @Override
  public void update(String id, int[] array) throws ArrayTaskException {
    if (id == null || array == null) {
      logger.error("Observer layer caught invalid notification schema elements");
      throw new ArrayTaskException(
          "Intercept update action validation failure: target fields null");
    }
    logger.info("Observer tracking validation trigger activated for target object ID: {}", id);
    Warehouse.getInstance().changeArrayStats(id, array);
  }

  @Override
  public void add(CustomArray array) throws ArrayTaskException {
    if (array == null) {
      logger.error("Observer mapping register dropped: reference entity instance is missing");
      throw new ArrayTaskException(
          "Failed to attach validation monitor: target mapping instance missing");
    }
    logger.info(
        "Binding model target notification layout parameters for tracked ID: {}", array.getId());
    array.setObserver(this);
    Warehouse.getInstance().changeArrayStats(array.getId(), array.getElements());
  }

  @Override
  public void remove(String id) {
    if (id != null) {
      logger.info(
          "Detaching monitoring pipeline structure context for targeted instance ID: {}", id);
      Warehouse.getInstance().remove(id);
    }
  }
}
