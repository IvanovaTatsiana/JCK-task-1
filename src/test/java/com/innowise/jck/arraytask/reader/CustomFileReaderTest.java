package com.innowise.jck.arraytask.reader;

import com.innowise.jck.arraytask.exception.ArrayTaskException;
import com.innowise.jck.arraytask.reader.impl.CustomFileReaderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomFileReaderTest {
  private CustomFileReader fileReader;

  @BeforeEach
  void setUp() {
    fileReader = new CustomFileReaderImpl();
  }

  @Test
  void testReadLinesThrowsExceptionWhenFileNotFound() {
    assertThrows(ArrayTaskException.class, () -> fileReader.readLines("non_existent_file.txt"));
  }

  @Test
  void testReadLinesThrowsExceptionWhenNull() {
    assertThrows(ArrayTaskException.class, () -> fileReader.readLines(null));
  }
}
