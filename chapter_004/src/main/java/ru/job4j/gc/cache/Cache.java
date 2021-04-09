package ru.job4j.gc.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class Cache {
    private final Map<String, SoftReference<String>> storage = new HashMap<>();
    private final String directory;

    public Cache(String directory) {
        this.directory = directory;
    }

    public String get(String name) {
        String rsl;
        SoftReference<String> rslSoft = storage.get(name);
        if (rslSoft != null) {
            rsl = rslSoft.get();
        } else {
            rsl = load(name);
            storage.put(name, new SoftReference<>(rsl));
        }
        return rsl;
    }

    private String load(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(directory + name))) {
            br.lines().forEach(str -> stringBuilder.append(str).append(System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
