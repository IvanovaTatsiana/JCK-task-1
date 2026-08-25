package com.innowise.jck.arraytask.observer;

import com.innowise.jck.arraytask.entity.CustomArray;
import com.innowise.jck.arraytask.exception.ArrayTaskException;

public interface ArrayObserver {
  void update(String id, int[] array) throws ArrayTaskException;

  void add(CustomArray array) throws ArrayTaskException;

  void remove(String id);
}
