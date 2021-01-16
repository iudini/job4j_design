package ru.job4j.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class FindFileByCriteria {
    static List<Path> list;

    public static void main(String[] args) {
        Criteria criteria = new Criteria(args);
        if (!criteria.valid()) {
            throw new IllegalArgumentException(
                    "Введите строку вида: "
                    + "-d \"директория поиска\" -n \"что искать\"\n"
                    + "-o \"куда записать результат\""
                    + " ключ -f по имени -m по маске -r по регулярному выражению");
        }
        try (PrintWriter out = new PrintWriter(new FileWriter(criteria.getOutput()))) {
            if (criteria.getMfr().equals("-f")) {
                list = searchByFullName(criteria.getDirectory(), criteria.getName());
            } else if (criteria.getMfr().equals("-m")) {
                list = searchByMask(criteria.getDirectory(), criteria.getName());
            } else if (criteria.getMfr().equals("-r")) {
                list = searchByRegex(criteria.getDirectory(), criteria.getName());
            }
            for (Path path : list) {
                out.println(path.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Path> searchByRegex(String dir, String name) throws IOException {
        Path root = Paths.get(dir);
        SearchFiles searcher = new SearchFiles(p -> p.getFileName().toString().matches(name));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static List<Path> searchByMask(String dir, String name) throws IOException {
        Path root = Paths.get(dir);
        String regex = name.replace("*", "(.*)");
        SearchFiles searcher = new SearchFiles(p -> p.getFileName().toString().matches(regex));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static List<Path> searchByFullName(String dir, String name) throws IOException {
        Path root = Paths.get(dir);
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().equals(name));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static class SearchFiles extends SimpleFileVisitor<Path> {
        Predicate<Path> predicate;
        List<Path> paths = new ArrayList<>();

        public SearchFiles(Predicate<Path> predicate) {
            this.predicate = predicate;
        }

        public List<Path> getPaths() {
            return paths;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (predicate.test(file)) {
                paths.add(file);
            }
            return CONTINUE;
        }
    }
}
