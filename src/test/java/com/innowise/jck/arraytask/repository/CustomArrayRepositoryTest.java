package com.innowise.jck.arraytask.repository;

import com.innowise.jck.arraytask.comparator.CustomArrayComparator;
import com.innowise.jck.arraytask.entity.CustomArray;
import com.innowise.jck.arraytask.exception.ArrayTaskException;
import com.innowise.jck.arraytask.observer.ArrayObserver;
import com.innowise.jck.arraytask.observer.impl.ArrayObserverImpl;
import com.innowise.jck.arraytask.specification.impl.*;
import com.innowise.jck.arraytask.warehouse.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayRepositoryTest {
  private CustomArrayRepository repository;
  private ArrayObserver observer;

  @BeforeEach
  void setUp() throws ArrayTaskException {
    repository = CustomArrayRepository.getInstance();
    repository.getAll().forEach(repository::remove);
    Warehouse.getInstance().clear();
    observer = new ArrayObserverImpl();

    CustomArray a1 = new CustomArray("A", new int[] {1, 2, 3});
    CustomArray a2 = new CustomArray("C", new int[] {10, 20});
    CustomArray a3 = new CustomArray("B", new int[] {-5, 0, 5, 10});

    observer.add(a1);
    observer.add(a2);
    observer.add(a3);

    repository.add(a1);
    repository.add(a2);
    repository.add(a3);
  }

  @Test
  void testQueryById() {
    List<CustomArray> result = repository.query(new SpecificationById("C"));
    assertEquals(1, result.size());
    assertEquals("C", result.get(0).getId());
  }

  @Test
  void testQueryByAverage() {
    List<CustomArray> result = repository.query(new SpecificationByAverage(5.0));
    assertEquals(1, result.size());
    assertEquals("C", result.get(0).getId());
  }

  @Test
  void testQueryByMin() {
    List<CustomArray> result = repository.query(new SpecificationByMin(0));
    assertEquals(2, result.size());
  }

  @Test
  void testQueryByMax() {
    List<CustomArray> result = repository.query(new SpecificationByMax(5));
    assertEquals(1, result.size());
  }

  @Test
  void testQueryBySum() {
    List<CustomArray> result = repository.query(new SpecificationBySum(15));
    assertEquals(1, result.size());
  }

  @Test
  void testSortById() {
    List<CustomArray> sorted = repository.sort(CustomArrayComparator.ID);
    assertEquals("A", sorted.get(0).getId());
    assertEquals("B", sorted.get(1).getId());
    assertEquals("C", sorted.get(2).getId());
  }

  @Test
  void testSortBySize() {
    List<CustomArray> sorted = repository.sort(CustomArrayComparator.SIZE);
    assertEquals(2, sorted.get(0).getArraySize());
    assertEquals(3, sorted.get(1).getArraySize());
    assertEquals(4, sorted.get(2).getArraySize());
  }

  @Test
  void testSortByFirstElement() {
    List<CustomArray> sorted = repository.sort(CustomArrayComparator.FIRST_ELEMENT);
    assertEquals(-5, sorted.get(0).getFirstElement());
    assertEquals(1, sorted.get(1).getFirstElement());
    assertEquals(10, sorted.get(2).getFirstElement());
  }
}
