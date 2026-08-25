package com.innowise.jck.arraytask;

import com.innowise.jck.arraytask.comparator.CustomArrayComparator;
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
    logger.info("Starting context system initialization pipeline steps...");
    try {
      CustomFileReader reader = new CustomFileReaderImpl();
      StringArrayValidator validator = new StringArrayValidatorImpl();
      ArrayParser parser = new ArrayParserImpl();
      ArrayFactory factory = new ArrayFactoryImpl();

      CustomArrayRepository repository = CustomArrayRepository.getInstance();
      Warehouse warehouse = Warehouse.getInstance();
      ArrayObserver observer = new ArrayObserverImpl();

      System.out.println("--- Load & Parse Data ---");
      List<String> lines = reader.readLines("input.txt");

      for (String line : lines) {
        if (validator.isValid(line)) {
          int[] numbers = parser.parseToArray(line);
          CustomArray array = factory.createArray(numbers);

          // Теперь выполняется чисто, без потенциальных рисков проброса checked-ошибок
          observer.add(array);
          repository.add(array);

          System.out.println("Registered: " + array);
        } else {
          System.out.println("Skipped invalid format: [" + line + "]");
        }
      }

      System.out.println("\n--- Initial Warehouse State ---");
      for (CustomArray array : repository.getAll()) {
        System.out.println("ID '" + array.getId() + "' metrics -> " + warehouse.get(array.getId()));
      }

      System.out.println("\n--- Test Observer Updates ---");
      if (!repository.getAll().isEmpty()) {
        CustomArray target = repository.getAll().get(0);
        System.out.println("Before modification: " + warehouse.get(target.getId()));
        target.setElement(0, 5000);
        System.out.println("After modification:  " + warehouse.get(target.getId()));
      }

      System.out.println("\n--- Test Observer Removal ---");
      if (!repository.getAll().isEmpty()) {
        CustomArray target = repository.getAll().get(repository.getAll().size() - 1);
        String id = target.getId();
        System.out.println("Warehouse status before removal: " + warehouse.get(id));
        repository.remove(target);
        observer.remove(id);
        System.out.println("Warehouse status after removal:  " + warehouse.get(id));
      }

      System.out.println("\n--- Filter Queries ---");
      if (!repository.getAll().isEmpty()) {
        String id = repository.getAll().get(0).getId();
        System.out.println("Find by ID '" + id + "':");
        repository.query(new SpecificationById(id)).forEach(System.out::println);
      }

      System.out.println("\nFind by Avg > 10.0:");
      repository.query(new SpecificationByAverage(10.0)).forEach(System.out::println);

      System.out.println("\nFind by Min >= 0:");
      repository.query(new SpecificationByMin(0)).forEach(System.out::println);

      System.out.println("\nFind by Max <= 100:");
      repository.query(new SpecificationByMax(100)).forEach(System.out::println);

      System.out.println("\nFind by Sum > 50:");
      repository.query(new SpecificationBySum(50)).forEach(System.out::println);

      System.out.println("\n--- Repository Sorting ---");
      System.out.println("By ID:");
      repository.sort(CustomArrayComparator.ID).forEach(System.out::println);

      System.out.println("\nBy Size:");
      repository.sort(CustomArrayComparator.SIZE).forEach(System.out::println);

      System.out.println("\nBy First Element:");
      repository.sort(CustomArrayComparator.FIRST_ELEMENT).forEach(System.out::println);

    } catch (Exception e) {
      logger.error(
          "Global processing operation layer caught execution exception failure mapping logs", e);
      System.err.println("Fatal execution error: " + e.getMessage());
    }
  }
}
