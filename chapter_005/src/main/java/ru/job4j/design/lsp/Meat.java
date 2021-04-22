package ru.job4j.design.lsp;

import java.time.LocalDate;

public class Meat extends Food {
    public Meat(String name, LocalDate expireDate, LocalDate createDate, double price, double discount) {
        super(name, expireDate, createDate, price, discount);
    }
}
