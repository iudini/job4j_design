package ru.job4j.io;

public class Criteria {
    private String directory;
    private String name;
    private String output;
    private String mfr;

    public Criteria(String[] args) {
        parse(args);
    }

    public void parse(String[] args) {
        String variable = "";
        for (String str : args) {
            if (str.startsWith("-")) {
                variable = str;
                if (str.equals("-m") || str.equals("-f") || str.equals("-r")) {
                    mfr = str;
                }
            } else if (variable.startsWith("-")) {
                if (variable.equals("-d")) {
                    directory = str;
                } else if (variable.equals("-n")) {
                    name = str;
                } else if (variable.equals("-o")) {
                    output = str;
                }
            }
        }
    }

    public boolean valid() {
        return directory != null && name != null && output != null && mfr != null;
    }

    public String getDirectory() {
        return directory;
    }

    public String getName() {
        return name;
    }

    public String getOutput() {
        return output;
    }

    public String getMfr() {
        return mfr;
    }
}
