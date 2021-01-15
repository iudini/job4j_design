package ru.job4j.io;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Shell {
    Path root = Paths.get("");

    public void cd(String path) {
        Path absPath = root;
        if (path.equals("/")) {
            root = root.toAbsolutePath().getRoot();
        } else {
            String[] str = path.split("/");
            for (String s : str) {
                if (s.equals("..") && absPath.getParent() == null) {
                    absPath = Paths.get("/");
                } else if (s.equals("..")) {
                    absPath = absPath.getParent();
                } else {
                    absPath = absPath.resolve(s);
                }
            }
            root = absPath;
        }
    }

    public String pwd() {
        return root.toString();
    }
}
