package ru.job4j.ood.srp;

import java.util.List;

public interface copyLinesIntoFile {
    List<String> getLines (String input, String start, int length);
    Boolean writeLines (List<String> list, String output);
}
