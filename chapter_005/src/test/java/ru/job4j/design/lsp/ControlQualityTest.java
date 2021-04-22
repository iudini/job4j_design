package ru.job4j.design.lsp;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {

    @Test
    public void addToWarehouse() {
        ControlQuality cq = new ControlQuality();
        Food meat = new Meat("Meat",
                LocalDate.of(2021, 7, 30),
                LocalDate.of(2021, 4, 1),
                100,
                0);
        cq.add(meat);
        assertThat(cq.getWarehouse().get().get(0), is(meat));
    }

    @Test
    public void addToShop() {
        ControlQuality cq = new ControlQuality();
        Food milk = new Milk("Milk",
                LocalDate.of(2021, 4, 30),
                LocalDate.of(2021, 1, 1),
                100,
                0);
        cq.add(milk);
        assertThat(cq.getShop().get().get(0), is(milk));
    }

    @Test
    public void addToShopWithDiscount() {
        ControlQuality cq = new ControlQuality();
        Food milk = new Milk("Milk",
                LocalDate.of(2021, 4, 23),
                LocalDate.of(2021, 4, 1),
                100,
                0);
        cq.add(milk);
        milk.setDiscount(0.25);
        assertThat(cq.getShop().get().get(0), is(milk));
    }

    @Test
    public void addToTrash() {
        ControlQuality cq = new ControlQuality();
        Food egg = new Egg("Egg",
                LocalDate.of(2021, 4, 20),
                LocalDate.of(2021, 4, 1),
                100,
                0);
        cq.add(egg);
        assertThat(cq.getTrash().get().get(0), is(egg));
    }
}