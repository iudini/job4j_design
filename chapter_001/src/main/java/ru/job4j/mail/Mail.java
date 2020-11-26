package ru.job4j.mail;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Mail {
    public static Map<String, Set<String>> merge(Map<String, Set<String>> map) {
        Map<String, Set<String>> result = new HashMap<>();
        for (var entry : map.entrySet()) {
            Set<String> set = new HashSet<>(entry.getValue());
            if (result.isEmpty()) {
                result.put(entry.getKey(), set);
                continue;
            }

            boolean mod = false;
            for (var rsl : result.entrySet()) {
                Set<String> rslSet = new HashSet<>(rsl.getValue());
                int rslSetSize = rslSet.size();
                rslSet.addAll(set);
                if (rslSetSize + entry.getValue().size() != rslSet.size()) {
                    rsl.setValue(rslSet);
                    mod = true;
                    break;
                }
            }

            if (!mod) {
                result.put(entry.getKey(), entry.getValue());
            }
        }

        return result;
    }
}
