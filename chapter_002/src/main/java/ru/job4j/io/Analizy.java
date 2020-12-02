package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(
                new FileReader(source));
             PrintWriter out = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target)))) {
            String begin = "";
            boolean unavailable = false;
            while (read.ready()) {
                String line = read.readLine();
                if (!unavailable && (line.contains("400") || line.contains("500"))) {
                    begin = line.split(" ")[1];
                    unavailable = true;
                } else if (unavailable && (line.contains("200") || line.contains("300"))) {
                    out.println(begin + ";" + line.split(" ")[1] + ";");
                    unavailable = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}