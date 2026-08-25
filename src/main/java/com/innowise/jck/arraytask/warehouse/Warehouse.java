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

  private final Map<String, ArrayStats> arrayStatsMap = new HashMap<>();
  private final ArrayCalculationService calculationService = new ArrayCalculationServiceImpl();

  private Warehouse() {}

  public static Warehouse getInstance() {
    if (instance == null) {
      instance = new Warehouse();
    }
    return instance;
  }

  public void changeArrayStats(String id, int[] elements) {
    if (id == null || elements == null) {
      logger.warn("Warehouse process layout state skip: raw tracking arguments are invalid");
      return;
    }
    logger.info("Warehouse state mapping modification processing metrics layout for ID: {}", id);

    int min = calculationService.findMin(elements);
    int max = calculationService.findMax(elements);
    int sum = calculationService.calculateSum(elements);
    double avg = calculationService.calculateAverage(elements);

    arrayStatsMap.put(id, new ArrayStats(min, max, sum, avg));
  }

  public ArrayStats get(String id) {
    logger.debug("Fetch mapping properties request from cache storage for ID: {}", id);
    return arrayStatsMap.get(id);
  }

  public void put(String id, ArrayStats stats) {
    logger.debug("Direct cache injection layout into cache storage mapping for ID: {}", id);
    arrayStatsMap.put(id, stats);
  }

  public void remove(String id) {
    logger.info("Evicting mapping configuration references from storage for ID: {}", id);
    arrayStatsMap.remove(id);
  }

  public void clear() {
    logger.info("Clearing memory tables allocation maps inside warehouse structure");
    arrayStatsMap.clear();
  }
}
