package ru.job4j.design.lsp;

import java.time.LocalDate;

public class Food {
    private String name;
    private LocalDate expireDate;
    private LocalDate createDate;
    private double price;
    private double discount;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
