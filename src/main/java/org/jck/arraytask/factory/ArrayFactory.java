package org.jck.arraytask.factory;

import org.jck.arraytask.entity.CustomArray;
import org.jck.arraytask.exception.ArrayTaskException;

public class ArrayFactory {

  public CustomArray createArray(int[] elements) throws ArrayTaskException {
    if (elements == null || elements.length == 0) {
      throw new ArrayTaskException("Cannot create CustomArray: array is empty or null");
    }

    return new CustomArray(elements);
  }
}
