package com.innowise.jck.arraytask.warehouse;

import com.innowise.jck.arraytask.service.ArrayCalculationService;
import com.innowise.jck.arraytask.service.impl.ArrayCalculationServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.Map;

public class Warehouse {
  private static final Logger logger = LogManager.getLogger(Warehouse.class);
  private static Warehouse instance;
  private final Map<String, ArrayStats> arrayStatsMap;
  private final ArrayCalculationService calculationService = new ArrayCalculationServiceImpl();

  private Warehouse() {
    this.arrayStatsMap = new HashMap<>();
  }

  public static Warehouse getInstance() {
    if (instance == null) {
      instance = new Warehouse();
    }
    return instance;
  }

  public void changeArrayStats(String id, int[] elements) {
    if (id == null || elements == null) {
      logger.warn("Processing failed in warehouse: invalid null parameters passed");
      return;
    }
    logger.info("Recalculating and modifying stats inside warehouse for ID: {}", id);

    int min = calculationService.findMin(elements);
    int max = calculationService.findMax(elements);
    int sum = calculationService.calculateSum(elements);
    double avg = calculationService.calculateAverage(elements);

    ArrayStats stats = new ArrayStats(min, max, sum, avg);
    arrayStatsMap.put(id, stats);
  }

  public ArrayStats get(String id) {
    logger.info("Get stats from warehouse for ID: {}", id);
    return arrayStatsMap.get(id);
  }

  public void put(String id, ArrayStats stats) {
    logger.info("Put stats into warehouse for ID: {}", id);
    arrayStatsMap.put(id, stats);
  }

  public void remove(String id) {
    logger.info("Remove stats from warehouse for ID: {}", id);
    arrayStatsMap.remove(id);
  }

  public void clear() {
    logger.info("Clearing complete cache memory inside warehouse");
    arrayStatsMap.clear();
  }
}
