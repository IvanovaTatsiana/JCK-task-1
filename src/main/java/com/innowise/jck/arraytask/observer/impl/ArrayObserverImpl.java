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
      logger.error("State modification intercept failed: payload is null");
      throw new ArrayTaskException("Parameters for update method cannot be null");
    }
    logger.info("Observer action update triggered for array ID: {}. Informing Warehouse.", id);
    Warehouse.getInstance().changeArrayStats(id, array);
  }

  @Override
  public void add(CustomArray array) throws ArrayTaskException {
    if (array == null) {
      logger.error("Registration in observer ecosystem failed: target entity is null");
      throw new ArrayTaskException("Target custom array object cannot be null");
    }
    logger.info("Binding new structural CustomArray entity targeting ID: {}", array.getId());
    array.setObserver(this);
    Warehouse.getInstance().changeArrayStats(array.getId(), array.getElements());
  }

  @Override
  public void remove(String id) {
    if (id == null) {
      logger.warn("Deregistration process dropped: ID reference is null");
      return;
    }
    logger.info("Detaching completely state properties inside Warehouse for target ID: {}", id);
    Warehouse.getInstance().remove(id);
  }
}
