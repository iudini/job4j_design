package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Must be 2 arguments, first is a root folder, second file extension.");
        }
        search(Paths.get(args[0]), args[1]).forEach(System.out::println);
        duplicate(Paths.get(args[0])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static List<Path> duplicate(Path root) throws IOException {
        SearchDuplicate searchDuplicate = new SearchDuplicate();
        Files.walkFileTree(root, searchDuplicate);
        return searchDuplicate.getPaths();
    }

    private static class SearchFiles implements FileVisitor<Path> {
        Predicate<Path> predicate;
        List<Path> paths = new ArrayList<>();

        public SearchFiles(Predicate<Path> predicate) {
            this.predicate = predicate;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (predicate.test(file)) {
                paths.add(file);
            }
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            return CONTINUE;
        }

        public List<Path> getPaths() {
            return paths;
        }
    }

    private static class SearchDuplicate  implements FileVisitor<Path> {
        List<Path> paths = new ArrayList<>();
        Map<String, Path> map = new HashMap<>();

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            String fileName = file.getFileName().toString();
            if (map.containsKey(fileName)
                    && Files.size(map.get(fileName)) == Files.size(file)) {
                paths.add(map.get(fileName));
                paths.add(file);
            } else {
                map.put(fileName, file);
            }
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            return CONTINUE;
        }

        public List<Path> getPaths() {
            return paths;
        }
    }
}
