package ru.job4j.mail;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Mail {

    /**
     * Проверяем возвращаемую map на не пустоту, если пустая, то заполняем по первому ключу,
     * при этом заполняем checkMap.
     *
     * Проверяем содержит ли checkMap email по данному ключу, после чего добавляем эти email в checkMap,
     * если содержит, то записываем ключ, который содержит значение, в containedKey и перезаписываем еще раз checkMap.
     *
     * Если совпадений не было, то в результирующий массив добавляем значения по ключу, иначе по ключу containedKey
     * дописываем значения.
     */

    public static Map<String, Set<String>> merge(Map<String, Set<String>> map) {
        Map<String, Set<String>> result = new HashMap<>();
        Map<String, String> checkMap = new HashMap<>();
        for (var key : map.keySet()) {
            if (result.isEmpty()) {
                result.put(key, map.get(key));
                for (String email : map.get(key)) {
                    checkMap.put(email, key);
                }
                continue;
            }

            boolean mod = false;
            String containedKey = "";
            for (String email : map.get(key)) {
                if (checkMap.containsKey(email)) {
                    containedKey = checkMap.get(email);
                    mod = true;
                    break;
                }
                checkMap.put(email, key);
            }

            if (!mod) {
                result.put(key, map.get(key));
            } else {
                for (String email : map.get(key)) {
                    checkMap.put(email, containedKey);
                }

                Set<String> tmp = new HashSet<>();
                tmp.addAll(result.get(containedKey));
                tmp.addAll(map.get(key));
                result.put(containedKey, tmp);
            }
        }

        return result;
    }
}
