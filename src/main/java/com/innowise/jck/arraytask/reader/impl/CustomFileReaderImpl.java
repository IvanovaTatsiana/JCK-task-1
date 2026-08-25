package com.innowise.jck.arraytask.reader.impl;

import com.innowise.jck.arraytask.exception.ArrayTaskException;
import com.innowise.jck.arraytask.reader.CustomFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
      logger.error("I/O reader configuration tracking issue: targeting null parameter");
      throw new ArrayTaskException("Target filename path cannot be null");
    }
    logger.info("Locating resources system stream path for processing: {}", fileName);
    ClassLoader classLoader = getClass().getClassLoader();

    try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
      if (inputStream == null) {
        logger.error("Resources streaming endpoint unresolved: {}", fileName);
        throw new ArrayTaskException("Missing resource data source target: " + fileName);
      }
      try (BufferedReader reader =
          new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
          lines.add(line);
        }
        logger.info("Buffer pipeline closed. Rows extracted successfully: {}", lines.size());
        return lines;
      }
    } catch (Exception e) {
      logger.error(
          "Runtime exception state processing internal file stream for target path: " + fileName,
          e);
      throw new ArrayTaskException("File system resource operation error", e);
    }
  }
}
