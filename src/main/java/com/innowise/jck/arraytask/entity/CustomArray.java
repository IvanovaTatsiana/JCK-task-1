package com.innowise.jck.arraytask.entity;

import com.innowise.jck.arraytask.observer.ArrayObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;

public class CustomArray {
  private static final Logger logger = LogManager.getLogger(CustomArray.class);

  private final String id;
  private int[] elements;
  private ArrayObserver observer;

  public CustomArray(String id, int[] elements) {
    this.id = id;
    this.elements = elements != null ? elements.clone() : new int[0];
    logger.info("CustomArray instance initialized. ID: {}, Size: {}", id, this.elements.length);
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
    logger.info("Setting new elements array block for ID: {}", id);
    this.elements = elements != null ? elements.clone() : new int[0];
    notifyObserver();
  }

  public void setElement(int index, int value) {
    if (index < 0 || index >= elements.length) {
      logger.error("Index exception for ID: {}. Index {} is out of bounds", id, index);
      throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }
    logger.info("Updating element at index {} to value {} for ID: {}", index, value, id);
    this.elements[index] = value;
    notifyObserver();
  }

  public void setObserver(ArrayObserver observer) {
    this.observer = observer;
  }

  private void notifyObserver() {
    if (observer != null) {
      logger.debug("Notifying observer tracking context for ID: {}", id);
      // Прямой вызов без try-catch оверхеда!
      observer.update(id, elements);
    } else {
      logger.warn("No active observer attached for ID: {}", id);
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
