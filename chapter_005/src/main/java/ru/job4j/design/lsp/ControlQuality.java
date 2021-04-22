package ru.job4j.design.lsp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ControlQuality {
    private final Storage warehouse = new Warehouse();
    private final Storage shop = new Shop();
    private final Storage trash = new Trash();
    private final double discount = 0.25;

    public Storage getWarehouse() {
        return warehouse;
    }

    public Storage getShop() {
        return shop;
    }

    public Storage getTrash() {
        return trash;
    }

    public void add(Food food) {
        int validity = checkExpire(food);
        if (validity > 75) {
            warehouse.add(food);
        } else if (validity > 25) {
            shop.add(food);
        } else if (validity > 0) {
            food.setDiscount(discount);
            shop.add(food);
        } else {
            trash.add(food);
        }
    }

    private int checkExpire(Food food) {
        long lifeTime = food.createDate.until(food.expireDate, ChronoUnit.DAYS);
        long timeSpent = LocalDate.now().until(food.expireDate, ChronoUnit.DAYS);
        return (int) (timeSpent * 100 / lifeTime);
    }
}
