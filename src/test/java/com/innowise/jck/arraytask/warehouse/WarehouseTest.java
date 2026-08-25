package com.innowise.jck.arraytask.warehouse;

import com.innowise.jck.arraytask.entity.CustomArray;
import com.innowise.jck.arraytask.exception.ArrayTaskException;
import com.innowise.jck.arraytask.observer.ArrayObserver;
import com.innowise.jck.arraytask.observer.impl.ArrayObserverImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {
  private Warehouse warehouse;
  private ArrayObserver observer;

  @BeforeEach
  void setUp() {
    warehouse = Warehouse.getInstance();
    warehouse.clear();
    observer = new ArrayObserverImpl();
  }

  @Test
  void testObserverAndWarehouseInteraction() throws ArrayTaskException {
    CustomArray array = new CustomArray("test-id", new int[] {10, 20, 30});
    observer.add(array);

    ArrayStats stats = warehouse.get("test-id");
    assertNotNull(stats);
    assertEquals(10, stats.getMin());
    assertEquals(30, stats.getMax());
    assertEquals(60, stats.getSum());
    assertEquals(20.0, stats.getAverage());

    // Проверка динамического пересчета
    array.setElement(0, 40); // {40, 20, 30}
    stats = warehouse.get("test-id");
    assertEquals(20, stats.getMin());
    assertEquals(40, stats.getMax());
    assertEquals(90, stats.getSum());
    assertEquals(30.0, stats.getAverage());
  }

  @Test
  void testRemoveFromWarehouse() throws ArrayTaskException {
    CustomArray array = new CustomArray("remove-id", new int[] {1, 2});
    observer.add(array);
    assertNotNull(warehouse.get("remove-id"));

    observer.remove("remove-id");
    assertNull(warehouse.get("remove-id"));
  }
}
