package ru.job4j.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            while (read.ready()) {
                String line = read.readLine();
                if (!line.isEmpty() && !line.startsWith("#") && line.contains("=")) {
                    String[] lineSeparated = line.split("=");
                    values.put(lineSeparated[0], lineSeparated[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }

    public static void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(source));
             PrintWriter writer = new PrintWriter(new BufferedOutputStream(
                     new FileOutputStream(target)))) {
            String serverDown = null;
            while (reader.ready()) {
                String status = reader.readLine();
                if (serverDown == null && (status.startsWith("400") || status.startsWith("500"))) {
                    writer.write(status.split(" ")[1] + ";");
                    serverDown = status;
                } else if (serverDown != null && (!status.isEmpty()
                        && !status.startsWith("400") && !status.startsWith("500"))) {
                    writer.write(status.split(" ")[1]);
                    serverDown = null;
                    writer.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}