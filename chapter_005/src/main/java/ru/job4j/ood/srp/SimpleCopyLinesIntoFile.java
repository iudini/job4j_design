package ru.job4j.ood.srp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleCopyLinesIntoFile implements copyLinesIntoFile {
    /* нарушение в том, что получаем строки из файла в этом классе,
    а надо было иметь уже сразу готорый лист строк
     */
    @Override
    public List<String> getLines(String input, String start, int length) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(input))) {
            while (bf.ready()) {
                rsl = bf.lines()
                        .filter(s -> s.startsWith(start) && s.length() <= length)
                        .collect(Collectors.toList());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public Boolean writeLines(List<String> list, String output) {
        boolean rsl = false;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(output))) {
            for (var element : list) {
                bw.write(element);
            }
            rsl = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }
}
