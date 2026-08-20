package org.jck.arraytask.reader;

import org.jck.arraytask.exception.ArrayTaskException;
import org.jck.arraytask.reader.impl.CustomFileReaderImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class CustomFileReaderTest {

  private CustomFileReader fileReader;

  @BeforeEach
  public void setUp() {
    fileReader = new CustomFileReaderImpl();
  }

  @Test
  public void testReadLinesSuccess() throws ArrayTaskException {
    // given
    String validFileName = "input.txt";

    // when
    List<String> lines = fileReader.readLines(validFileName);

    // then
    Assertions.assertNotNull(lines);
    Assertions.assertFalse(lines.isEmpty());
  }

  @Test
  public void testReadLinesShouldThrowExceptionForMissingFile() {
    // given
    String missingFileName = "non_existent_file.txt";

    // when & then
    Assertions.assertThrows(
        ArrayTaskException.class,
        () -> {
          fileReader.readLines(missingFileName);
        });
  }

  @Test
  public void testReadLinesShouldThrowExceptionForNullFileName() {
    // given
    String nullFileName = null;

    // when & then
    Assertions.assertThrows(
        ArrayTaskException.class,
        () -> {
          fileReader.readLines(nullFileName);
        });
  }
}
