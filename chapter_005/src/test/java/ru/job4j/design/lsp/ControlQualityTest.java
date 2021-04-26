package ru.job4j.design.lsp;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {

    @Test
    public void addToWarehouse() {
        List<Storage> storages = List.of(new Warehouse());
        ControlQuality cq = new ControlQuality(storages);
        Food meat = new Meat("Meat",
                LocalDate.of(2021, 7, 30),
                LocalDate.of(2021, 4, 1),
                100,
                0);
        cq.add(meat);
        assertThat(storages.get(0).get().get(0), is(meat));
    }

    @Test
    public void addToShop() {
        List<Storage> storages = List.of(new Shop());
        ControlQuality cq = new ControlQuality(storages);
        Food milk = new Milk("Milk",
                LocalDate.of(2021, 4, 30),
                LocalDate.of(2021, 1, 1),
                100,
                0);
        cq.add(milk);
        assertThat(storages.get(0).get().get(0), is(milk));
    }

    @Test
    public void addToShopWithDiscount() {
        List<Storage> storages = List.of(new Shop());
        ControlQuality cq = new ControlQuality(storages);
        Food milk = new Milk("Milk",
                LocalDate.of(2021, 4, 28),
                LocalDate.of(2021, 4, 1),
                100,
                0);
        cq.add(milk);
        milk.setDiscount(0.25);
        assertThat(storages.get(0).get().get(0), is(milk));
    }

    @Test
    public void addToTrash() {
        List<Storage> storages = List.of(new Trash());
        ControlQuality cq = new ControlQuality(storages);
        Food egg = new Egg("Egg",
                LocalDate.of(2021, 4, 20),
                LocalDate.of(2021, 4, 1),
                100,
                0);
        cq.add(egg);
        assertThat(storages.get(0).get().get(0), is(egg));
    }
}