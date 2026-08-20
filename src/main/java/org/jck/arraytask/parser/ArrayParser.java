package org.jck.arraytask.parser;

import org.jck.arraytask.exception.ArrayTaskException;

public interface ArrayParser {
    int[] parseToArray(String line) throws ArrayTaskException;
}