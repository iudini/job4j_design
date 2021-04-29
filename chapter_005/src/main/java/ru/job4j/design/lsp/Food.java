package ru.job4j.design.lsp;


import java.time.LocalDate;

public class Food {
    String name;
    LocalDate expireDate;
    LocalDate createDate;
    double price;
    double discount;

    public Food(String name, LocalDate expireDate, LocalDate createDate, double price, double discount) {
        if (createDate.compareTo(expireDate) > 0) {
            throw new IllegalArgumentException(createDate + " after " + expireDate);
        }
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
