package org.jck.arraytask.reader.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jck.arraytask.exception.ArrayTaskException;
import org.jck.arraytask.reader.CustomFileReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CustomFileReaderImpl implements CustomFileReader {

  private static final Logger logger = LogManager.getLogger(CustomFileReaderImpl.class);

  @Override
  public List<String> readLines(String fileName) throws ArrayTaskException {
    if (fileName == null) {
      logger.error("File name configuration is null");
      throw new ArrayTaskException("File name cannot be null");
    }

    logger.info("Attempting to read resource file: {}", fileName);
    List<String> lines = new ArrayList<>();

    Class<?> currentClass = getClass();
    ClassLoader classLoader = currentClass.getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(fileName);

    if (inputStream == null) {
      logger.error("Target resource file not found: {}", fileName);
      throw new ArrayTaskException("Resource file not found: " + fileName);
    }

    InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

    try (BufferedReader bufferedReader = new BufferedReader(streamReader)) {
      String currentLine;
      while ((currentLine = bufferedReader.readLine()) != null) {
        lines.add(currentLine);
      }
      logger.info("Successfully read {} lines from file", lines.size());
    } catch (Exception e) {
      logger.error("Error occurred while processing file: {}", fileName, e);
      throw new ArrayTaskException("Error reading resource file: " + fileName, e);
    }

    return lines;
  }
}
