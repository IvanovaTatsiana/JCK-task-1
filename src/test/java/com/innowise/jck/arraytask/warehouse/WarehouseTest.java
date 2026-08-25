package com.innowise.jck.arraytask.warehouse;

import com.innowise.jck.arraytask.entity.CustomArray;
import com.innowise.jck.arraytask.exception.ArrayTaskException;
import com.innowise.jck.arraytask.observer.ArrayObserver;
import com.innowise.jck.arraytask.observer.impl.ArrayObserverImpl;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WarehouseTest {
  private static Warehouse warehouse;
  private static ArrayObserver observer;
  private static CustomArray array;

  @BeforeAll
  static void setUp() {
    warehouse = Warehouse.getInstance();
    warehouse.clear();
    observer = new ArrayObserverImpl();
    array = new CustomArray("test-id", new int[] {10, 20, 30});
  }

  @Test
  @Order(1)
  void testObserverAndWarehouseInteraction() throws ArrayTaskException {
    observer.add(array);

    ArrayStats stats = warehouse.get("test-id");
    assertNotNull(stats);
    assertEquals(10, stats.getMin());
    assertEquals(30, stats.getMax());
    assertEquals(60, stats.getSum());
    assertEquals(20.0, stats.getAverage());
  }

  @Test
  @Order(2)
  void testAutomaticRecalculation() {
    array.setElement(0, 40);
    ArrayStats stats = warehouse.get("test-id");
    assertNotNull(stats);
    assertEquals(20, stats.getMin());
    assertEquals(40, stats.getMax());
    assertEquals(90, stats.getSum());
    assertEquals(30.0, stats.getAverage());
  }

  @Test
  @Order(3)
  void testRemoveFromWarehouse() {
    observer.remove("test-id");
    assertNull(warehouse.get("test-id"));
  }
}
