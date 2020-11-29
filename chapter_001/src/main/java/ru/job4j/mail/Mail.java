package ru.job4j.mail;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Mail {
    public static Map<String, Set<String>> merge(Map<String, Set<String>> map) {
        Map<String, Set<String>> result = new HashMap<>();
        for (var key : map.keySet()) {
            if (result.isEmpty()) {
                result.put(key, map.get(key));
                continue;
            }

            boolean mod = false;
            for (var rsl : result.entrySet()) {
                /** В новый set rslSet записываем значения из результирующей map result и запоминаем размер rslSetSize.
                 * Далее в rslSet добавляем значения из исходной map.
                 * Проверяем размер получившейся, если размер равен общему количеству элементов в двух set,
                 * т.е. одинаковых элементов не было, значит в этот ключ мы пропускаем,
                 * если размер не равен общему количеству, значит есть общие элементы и эти значения запишутся
                 * в результирующий set в текущий ключ, после чего цикл прервется.
                 * Если совпадений все размеры равны, то флаг модификации останется false, и после проверки,
                 * всех ключей в результирующем set, в него будет добавлен еще один ключ, значение.
                 */
                Set<String> rslSet = new HashSet<>(rsl.getValue());
                int rslSetSize = rslSet.size();
                rslSet.addAll(map.get(key));
                if (rslSetSize + map.get(key).size() != rslSet.size()) {
                    rsl.setValue(rslSet);
                    mod = true;
                    break;
                }
            }

            if (!mod) {
                result.put(key, map.get(key));
            }
        }

        return result;
    }
}
