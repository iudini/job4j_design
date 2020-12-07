package ru.job4j.io;

import java.util.function.Predicate;

public class ArgZip {

    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        if (directory() != null && exclude() != null && output() != null) {
            return true;
        }
        return false;
    }

    public String directory() {
        return keyCheck(x -> x.contains("-d="), args);
    }

    public String exclude() {
        return keyCheck(x -> x.contains("-e="), args);
    }

    public String output() {
        return keyCheck(x -> x.contains("-o="), args);
    }

    private static String keyCheck(Predicate<String> predicate, String[] args) {
        for (var arg : args) {
            if (predicate.test(arg)) {
                return arg.substring(arg.indexOf("=") + 1);
            }
        }
        return null;
    }
}
