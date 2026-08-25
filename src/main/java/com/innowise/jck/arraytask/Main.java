package com.innowise.jck.arraytask;

import com.innowise.jck.arraytask.comparator.CustomArrayComparatorClassic;
import com.innowise.jck.arraytask.entity.CustomArray;
import com.innowise.jck.arraytask.factory.ArrayFactory;
import com.innowise.jck.arraytask.factory.impl.ArrayFactoryImpl;
import com.innowise.jck.arraytask.observer.ArrayObserver;
import com.innowise.jck.arraytask.observer.impl.ArrayObserverImpl;
import com.innowise.jck.arraytask.parser.ArrayParser;
import com.innowise.jck.arraytask.parser.impl.ArrayParserImpl;
import com.innowise.jck.arraytask.reader.CustomFileReader;
import com.innowise.jck.arraytask.reader.impl.CustomFileReaderImpl;
import com.innowise.jck.arraytask.repository.CustomArrayRepository;
import com.innowise.jck.arraytask.specification.impl.SpecificationByAverage;
import com.innowise.jck.arraytask.specification.impl.SpecificationById;
import com.innowise.jck.arraytask.specification.impl.SpecificationByMax;
import com.innowise.jck.arraytask.specification.impl.SpecificationByMin;
import com.innowise.jck.arraytask.specification.impl.SpecificationBySum;
import com.innowise.jck.arraytask.validator.StringArrayValidator;
import com.innowise.jck.arraytask.validator.impl.StringArrayValidatorImpl;
import com.innowise.jck.arraytask.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class Main {
  private static final Logger logger = LogManager.getLogger(Main.class);

  public static void main(String[] args) {
    logger.info("Application starting system context orchestration...");
    try {
      CustomFileReader reader = new CustomFileReaderImpl();
      StringArrayValidator validator = new StringArrayValidatorImpl();
      ArrayParser parser = new ArrayParserImpl();
      ArrayFactory factory = new ArrayFactoryImpl();

      CustomArrayRepository repository = CustomArrayRepository.getInstance();
      Warehouse warehouse = Warehouse.getInstance();
      ArrayObserver observer = new ArrayObserverImpl();

      System.out.println("--- Reading and processing file ---");
      List<String> lines = reader.readLines("input.txt");

      for (String line : lines) {
        if (validator.isValid(line)) {
          int[] parsedNumbers = parser.parseToArray(line);
          CustomArray customArray = factory.createArray(parsedNumbers);

          observer.add(customArray);
          repository.add(customArray);

          System.out.println("Added to repository: " + customArray);
        } else {
          System.out.println("Line skipped (invalid format): [" + line + "]");
        }
      }

      System.out.println("\n--- Warehouse State ---");
      for (CustomArray array : repository.getAll()) {
        System.out.println(
            "ID '" + array.getId() + "' -> Metrics: " + warehouse.get(array.getId()));
      }

      System.out.println("\n--- Testing Observer Automatic Recalculation ---");
      if (!repository.getAll().isEmpty()) {
        CustomArray targetArray = repository.getAll().get(0);
        System.out.println("Stats BEFORE modification: " + warehouse.get(targetArray.getId()));

        targetArray.setElement(0, 9999);
        System.out.println(
            "Stats AFTER automatic recalculation: " + warehouse.get(targetArray.getId()));
      }

      System.out.println("\n--- Testing Observer Removal Logic ---");
      if (!repository.getAll().isEmpty()) {
        CustomArray arrayToRemove = repository.getAll().get(repository.getAll().size() - 1);
        String idToRemove = arrayToRemove.getId();
        System.out.println(
            "Stats in warehouse for ID '"
                + idToRemove
                + "' BEFORE removal: "
                + warehouse.get(idToRemove));

        repository.remove(arrayToRemove);
        observer.remove(idToRemove);

        System.out.println(
            "Stats in warehouse for ID '"
                + idToRemove
                + "' AFTER removal: "
                + warehouse.get(idToRemove));
      }

      System.out.println("\n--- Specification Query Search (from impl package) ---");
      if (!repository.getAll().isEmpty()) {
        String firstId = repository.getAll().get(0).getId();
        System.out.println("Searching for array with specific ID '" + firstId + "':");
        repository.query(new SpecificationById(firstId)).forEach(System.out::println);
      }

      System.out.println("\nArrays with Avg > 10.0:");
      repository.query(new SpecificationByAverage(10.0)).forEach(System.out::println);

      System.out.println("\nArrays with Min >= 0:");
      repository.query(new SpecificationByMin(0)).forEach(System.out::println);

      System.out.println("\nArrays with Max <= 100:");
      repository.query(new SpecificationByMax(100)).forEach(System.out::println);

      System.out.println("\nArrays with Sum > 50:");
      repository.query(new SpecificationBySum(50)).forEach(System.out::println);

      System.out.println("\n--- Enum Sorting Demonstration ---");
      System.out.println("Sorting by array ID:");
      List<CustomArray> sortedById = repository.sort(CustomArrayComparatorClassic.ID);
      sortedById.forEach(System.out::println);

      System.out.println("\nSorting by array SIZE:");
      List<CustomArray> sortedBySize = repository.sort(CustomArrayComparatorClassic.SIZE);
      sortedBySize.forEach(System.out::println);

      System.out.println("\nSorting by FIRST_ELEMENT:");
      List<CustomArray> sortedByFirst = repository.sort(CustomArrayComparatorClassic.FIRST_ELEMENT);
      sortedByFirst.forEach(System.out::println);

    } catch (Exception e) {
      logger.fatal("Fatal runtime core failure handled!", e);
      System.err.println("Critical error occurred: " + e.getMessage());
      e.printStackTrace();
    }
    logger.info("Application execution runtime successfully stopped.");
  }
}
