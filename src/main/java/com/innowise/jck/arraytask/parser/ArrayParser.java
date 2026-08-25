package com.innowise.jck.arraytask.parser;

import com.innowise.jck.arraytask.exception.ArrayTaskException;

public interface ArrayParser {
  int[] parseToArray(String line) throws ArrayTaskException;
}
