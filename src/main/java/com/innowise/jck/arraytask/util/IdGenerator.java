package com.innowise.jck.arraytask.util;

public class IdGenerator {
  private static long counter = 1L;

  private IdGenerator() {}

  public static String generateId() {
    return String.valueOf(counter++);
  }
}
