package org.jck.arraytask;

import java.io.InputStream;
import java.util.logging.LogManager;
import org.jck.arraytask.entity.CustomArray;
import org.jck.arraytask.exception.ArrayTaskException;
import org.jck.arraytask.factory.ArrayFactory;
import org.jck.arraytask.parser.ArrayParser;
import org.jck.arraytask.reader.CustomFileReader;
import org.jck.arraytask.service.ArrayCalculationService;
import org.jck.arraytask.service.ArraySortService;
import org.jck.arraytask.service.impl.ArrayCalculationServiceImpl;
import org.jck.arraytask.service.impl.ArraySortServiceImpl;
import org.jck.arraytask.validator.StringArrayValidator;

import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public class Main {

  private static final System.Logger LOGGER = System.getLogger(Main.class.getName());

  public static void main(String[] args) {
    // Load logging configuration from logging.properties
    try (InputStream configStream =
        Main.class.getClassLoader().getResourceAsStream("logging.properties")) {
      if (configStream != null) {
        LogManager.getLogManager().readConfiguration(configStream);
      }
    } catch (Exception e) {
      System.err.println("Could not load logging configuration: " + e.getMessage());
    }

    CustomFileReader fileReader = new CustomFileReader();
    StringArrayValidator validator = new StringArrayValidator();
    ArrayParser parser = new ArrayParser();
    ArrayFactory factory = new ArrayFactory();

    ArrayCalculationService calculationService = new ArrayCalculationServiceImpl();
    ArraySortService sortService = new ArraySortServiceImpl();

    try {
      LOGGER.log(System.Logger.Level.INFO, "Application started");

      // Relative path to resource without "src" directory
      String resourceName = "input.txt";
      List<String> lines = fileReader.readLines(resourceName);

      int lineNumber = 1;
      for (String line : lines) {
        boolean isLineValid = validator.isValid(line);

        // Positive scenario goes first
        if (isLineValid) {
          int[] rawNumbers = parser.parseToArray(line);
          CustomArray customArray = factory.createArray(rawNumbers);

          // Law of Demeter (one dot per line) for Optional values
          OptionalInt optionalMin = calculationService.findMin(customArray);
          if (optionalMin.isPresent()) {
            int minVal = optionalMin.getAsInt();
            LOGGER.log(System.Logger.Level.INFO, "Line {0} - Min: {1}", lineNumber, minVal);
          }

          OptionalInt optionalMax = calculationService.findMax(customArray);
          if (optionalMax.isPresent()) {
            int maxVal = optionalMax.getAsInt();
            LOGGER.log(System.Logger.Level.INFO, "Line {0} - Max: {1}", lineNumber, maxVal);
          }

          int sum = calculationService.calculateSum(customArray);
          LOGGER.log(System.Logger.Level.INFO, "Line {0} - Sum: {1}", lineNumber, sum);

          OptionalDouble optionalAvg = calculationService.calculateAverage(customArray);
          if (optionalAvg.isPresent()) {
            double avgVal = optionalAvg.getAsDouble();
            LOGGER.log(System.Logger.Level.INFO, "Line {0} - Avg: {1}", lineNumber, avgVal);
          }

          // Testing Algorithm 1: Bubble Sort
          CustomArray sortedBubble = sortService.bubbleSort(customArray);
          LOGGER.log(
              System.Logger.Level.INFO,
              "Line {0} - Sorted (Bubble): {1}",
              lineNumber,
              sortedBubble);

          // Testing Algorithm 2: Selection Sort
          CustomArray sortedSelection = sortService.selectionSort(customArray);
          LOGGER.log(
              System.Logger.Level.INFO,
              "Line {0} - Sorted (Selection): {1}",
              lineNumber,
              sortedSelection);

        } else {
          // Negative scenario goes to the else block
          LOGGER.log(System.Logger.Level.WARNING, "Line {0} ignored (invalid data)", lineNumber);
        }
        lineNumber++;
      }

      LOGGER.log(System.Logger.Level.INFO, "Application finished successfully");

    } catch (ArrayTaskException e) {
      LOGGER.log(System.Logger.Level.ERROR, "Critical error occurred", e);
    }
  }
}
