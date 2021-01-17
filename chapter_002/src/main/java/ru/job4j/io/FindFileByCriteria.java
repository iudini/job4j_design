package ru.job4j.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

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
            list = search(criteria.getDirectory(), getPredicate(criteria.getMfr(), criteria.getName()));
            for (Path path : list) {
                out.println(path.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Predicate<Path> getPredicate(String mfr, String name) {
        Predicate<Path> predicate = null;
        if (mfr.equals("-f")) {
            predicate = p -> p.getFileName().toString().equals(name);
        } else if (mfr.equals("-m")) {
            String regex = name.replace("*", "(.*)");
            Pattern regPattern = Pattern.compile(regex);
            predicate = p -> regPattern.matcher(p.getFileName().toString()).matches();
        } else if (mfr.equals("-r")) {
            Pattern pattern = Pattern.compile(name);
            predicate = p -> pattern.matcher(p.getFileName().toString()).matches();
        }
        return predicate;
    }

    private static List<Path> search(String dir, Predicate<Path> predicate) throws IOException {
        Path root = Paths.get(dir);
        SearchFiles searcher = new SearchFiles(predicate);
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
