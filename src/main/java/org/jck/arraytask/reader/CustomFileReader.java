package org.jck.arraytask.reader;

import org.jck.arraytask.exception.ArrayTaskException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CustomFileReader {

  private static final System.Logger LOGGER = System.getLogger(CustomFileReader.class.getName());

  public List<String> readLines(String fileName) throws ArrayTaskException {
    if (fileName == null) {
      throw new ArrayTaskException("File name cannot be null");
    }

    List<String> lines = new ArrayList<>();

    ClassLoader classLoader = getClass().getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(fileName);

    if (inputStream == null) {
      throw new ArrayTaskException("Resource file not found: " + fileName);
    }

    InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

    try (BufferedReader bufferedReader = new BufferedReader(streamReader)) {
      String currentLine;

      while ((currentLine = bufferedReader.readLine()) != null) {
        lines.add(currentLine);
      }
    } catch (Exception e) {
      throw new ArrayTaskException("Error reading resource file: " + fileName, e);
    }

    return lines;
  }
}
