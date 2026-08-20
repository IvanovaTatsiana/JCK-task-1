package org.jck.arraytask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jck.arraytask.entity.CustomArray;
import org.jck.arraytask.exception.ArrayTaskException;
import org.jck.arraytask.factory.ArrayFactory;
import org.jck.arraytask.parser.ArrayParser;
import org.jck.arraytask.parser.impl.ArrayParserImpl;
import org.jck.arraytask.reader.CustomFileReader;
import org.jck.arraytask.reader.impl.CustomFileReaderImpl;
import org.jck.arraytask.service.ArrayCalculationService;
import org.jck.arraytask.service.ArraySortService;
import org.jck.arraytask.service.impl.ArrayCalculationServiceImpl;
import org.jck.arraytask.service.impl.ArraySortServiceImpl;
import org.jck.arraytask.validator.StringArrayValidator;
import org.jck.arraytask.validator.impl.StringArrayValidatorImpl;

import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public class Main {

  private static final Logger logger = LogManager.getLogger(Main.class);

  public static void main(String[] args) {
    CustomFileReader fileReader = new CustomFileReaderImpl();
    StringArrayValidator validator = new StringArrayValidatorImpl();
    ArrayParser parser = new ArrayParserImpl();
    ArrayFactory factory = new ArrayFactory();

    ArrayCalculationService calculationService = new ArrayCalculationServiceImpl();
    ArraySortService sortService = new ArraySortServiceImpl();

    try {
      logger.info("Application started");

      String resourceName = "input.txt";
      List<String> lines = fileReader.readLines(resourceName);

      int lineNumber = 1;
      for (String line : lines) {
        boolean isLineValid = validator.isValid(line);

        if (isLineValid) {
          int[] rawNumbers = parser.parseToArray(line);
          CustomArray customArray = factory.createArray(rawNumbers);

          logger.info("Line " + lineNumber + " - CustomArray created successfully: " + customArray);

          OptionalInt optionalMin = calculationService.findMin(customArray);
          if (optionalMin.isPresent()) {
            int minVal = optionalMin.getAsInt();
            logger.info("  Min: " + minVal);
          }

          OptionalInt optionalMax = calculationService.findMax(customArray);
          if (optionalMax.isPresent()) {
            int maxVal = optionalMax.getAsInt();
            logger.info("  Max: " + maxVal);
          }

          int sum = calculationService.calculateSum(customArray);
          logger.info("  Sum: " + sum);

          OptionalDouble optionalAvg = calculationService.calculateAverage(customArray);
          if (optionalAvg.isPresent()) {
            double avgVal = optionalAvg.getAsDouble();
            logger.info("  Avg: " + avgVal);
          }

          CustomArray sortedBubble = sortService.bubbleSort(customArray);
          logger.info("  Sorted (Bubble): " + sortedBubble);

          CustomArray sortedSelection = sortService.selectionSort(customArray);
          logger.info("  Sorted (Selection): " + sortedSelection);

        } else {
          logger.warn("Line " + lineNumber + " ignored (invalid data)");
        }
        lineNumber++;
      }

      logger.info("Application finished successfully");

    } catch (ArrayTaskException e) {
      logger.error("Critical error occurred in Main runtime context", e);
    }
  }
}
