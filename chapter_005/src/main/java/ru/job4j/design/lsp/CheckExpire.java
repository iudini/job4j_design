package ru.job4j.design.lsp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CheckExpire {
    public static int check(Food food) {
        long lifeTime = food.getCreateDate().until(food.getExpireDate(), ChronoUnit.DAYS);
        long timeSpent = LocalDate.now().until(food.getExpireDate(), ChronoUnit.DAYS);
        return (int) (timeSpent * 100 / lifeTime);
    }
}
