package org.jck.arraytask.entity;

import java.util.Arrays;

public class CustomArray {

  private final int[] elements;

  public CustomArray(int[] elements) {

    this.elements = elements != null ? elements.clone() : new int[0];
  }

  public int[] getElements() {
    return elements.clone();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CustomArray danger = (CustomArray) o;
    return Arrays.equals(elements, danger.elements);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(elements);
  }

  @Override
  public String toString() {
    return "CustomArray{" + "elements=" + Arrays.toString(elements) + '}';
  }
}
