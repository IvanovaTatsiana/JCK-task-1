package com.innowise.jck.arraytask.factory;

import com.innowise.jck.arraytask.entity.CustomArray;
import com.innowise.jck.arraytask.exception.ArrayTaskException;

public interface ArrayFactory {
  CustomArray createArray(int[] elements) throws ArrayTaskException;
}
