package com.innowise.jck.arraytask.reader;

import com.innowise.jck.arraytask.exception.ArrayTaskException;
import java.util.List;

public interface CustomFileReader {
  List<String> readLines(String fileName) throws ArrayTaskException;
}
