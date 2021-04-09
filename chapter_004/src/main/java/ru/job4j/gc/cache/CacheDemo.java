package ru.job4j.gc.cache;

public class CacheDemo {
    public static void main(String[] args) {
        Cache cache = new Cache("chapter_004/src/main/resources/");
        String rsl = cache.get("names.txt");
        System.out.println(rsl);
        rsl = cache.get("addresses.txt");
        System.out.println(rsl);
    }
}
