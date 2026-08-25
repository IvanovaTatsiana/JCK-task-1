package com.innowise.jck.arraytask.entity;

import com.innowise.jck.arraytask.observer.ArrayObserver;
import java.util.Arrays;

public class CustomArray {
  private final String id;
  private int[] elements;
  private ArrayObserver observer;

  public CustomArray(String id, int[] elements) {
    this.id = id;
    this.elements = elements != null ? elements.clone() : new int[0];
  }

  public String getId() {
    return id;
  }

  public int[] getElements() {
    return elements.clone();
  }

  public int getArraySize() {
    return elements.length;
  }

  public int getFirstElement() {
    return elements.length > 0 ? elements[0] : 0;
  }

  public void setElements(int[] elements) {
    this.elements = elements != null ? elements.clone() : new int[0];
    notifyObserver();
  }

  public void setElement(int index, int value) throws IndexOutOfBoundsException {
    if (index < 0 || index >= elements.length) {
      throw new IndexOutOfBoundsException("Invalid index: " + index);
    }
    this.elements[index] = value;
    notifyObserver();
  }

  public void setObserver(ArrayObserver observer) {
    this.observer = observer;
  }

  private void notifyObserver() {
    if (observer != null) {
      try {
        observer.update(id, elements);
      } catch (Exception e) {
        org.apache.logging.log4j.LogManager.getLogger(CustomArray.class)
            .error("Error notifying observer for array ID: {}", id, e);
      }
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CustomArray that = (CustomArray) o;
    return id.equals(that.id) && Arrays.equals(elements, that.elements);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + Arrays.hashCode(elements);
    return result;
  }

  @Override
  public String toString() {
    return "CustomArray{id='" + id + "', elements=" + Arrays.toString(elements) + "}";
  }
}
