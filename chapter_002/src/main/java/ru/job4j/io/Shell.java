package ru.job4j.io;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Shell {
    List<String> pathList = new LinkedList<>();

    public void cd(String path) {
        if (path.contains("..")) {
            if (path.equals("..")) {
                if (pathList.size() != 0) {
                    pathList.remove(pathList.size() - 1);
                }
            } else {
                String[] paths = path.split("/");
                pathList.addAll(Arrays.asList(paths).subList(0, paths.length - 2));
            }
        } else {
            pathList.addAll(Arrays.asList(path.split("/")));
        }
    }

    public String pwd() {
        StringBuilder result = new StringBuilder();
        for (String path : pathList) {
            result.append("/");
            result.append(path);
        }
        if (result.length() == 0) {
            result.append("/");
        }
        return result.toString();
    }
}
