package com.innowise.jck.arraytask.observer;

import com.innowise.jck.arraytask.entity.CustomArray;
import com.innowise.jck.arraytask.exception.ArrayTaskException;

public interface ArrayObserver {
  void update(String id, int[] array);

  void add(CustomArray array);

  void remove(String id);
}
