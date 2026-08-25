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
      logger.error("Reader configuration issue: filename is null");
      throw new ArrayTaskException("File name cannot be null");
    }
    logger.info("Locating resources targeting: {}", fileName);
    ClassLoader classLoader = getClass().getClassLoader();
    try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
      if (inputStream == null) {

        logger.debug("Target path not found for expected test fail: {}", fileName);
        throw new ArrayTaskException("Resource file not found: " + fileName);
      }
      try (BufferedReader reader =
          new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
          lines.add(line);
        }
        logger.info("Total entries successfully read: {}", lines.size());
        return lines;
      }
    } catch (ArrayTaskException e) {
      throw e;
    } catch (Exception e) {
      logger.error("Fatal I/O failure while processing file stream: {}", fileName, e);
      throw new ArrayTaskException("Error reading file: " + fileName, e);
    }
  }
}
