package ru.job4j.design.lsp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CheckExpire {
    public static int check(Food food) {
        long lifeTime = food.createDate.until(food.expireDate, ChronoUnit.DAYS);
        long timeSpent = LocalDate.now().until(food.expireDate, ChronoUnit.DAYS);
        return (int) (timeSpent * 100 / lifeTime);
    }
}
