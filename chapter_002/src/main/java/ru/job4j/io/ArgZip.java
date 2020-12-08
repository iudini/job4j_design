package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgZip {

    private final String[] args;
    private Map<String, String> keys = new HashMap<>();

    public ArgZip(String[] args) {
        this.args = args;
        for (var arg : args) {
            if (arg.contains("=") && arg.startsWith("-")) {
                String[] splitKey = arg.split("=");
                keys.put(splitKey[0].substring(1), splitKey[1]);
            }
        }
    }

    public boolean valid() {
        return directory() != null && exclude() != null && output() != null;
    }

    public String directory() {
        return keys.get("d");
    }

    public String exclude() {
        return keys.get("e");
    }

    public String output() {
        return keys.get("o");
    }
}
