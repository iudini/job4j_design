package ru.job4j.design.lsp;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {

    @Test
    public void addToWarehouse() {
        List<Storage> storages = List.of(new Warehouse());
        ControlQuality cq = new ControlQuality(storages);
        Food meat = new Meat("Meat",
                LocalDate.of(2021, 12, 30),
                LocalDate.of(2021, 4, 1),
                100,
                0);
        cq.add(meat);
        assertThat(((Food) storages.get(0).get().get(0)).getName(), is("Meat"));
    }

    @Test
    public void addToShop() {
        List<Storage> storages = List.of(new Shop());
        ControlQuality cq = new ControlQuality(storages);
        Food milk = new Milk("Milk",
                LocalDate.of(2021, 5, 30),
                LocalDate.of(2021, 1, 1),
                100,
                0);
        cq.add(milk);
        assertThat(((Food) storages.get(0).get().get(0)).getName(), is("Milk"));
    }

    @Test
    public void addToShopWithDiscount() {
        List<Storage> storages = List.of(new Shop());
        ControlQuality cq = new ControlQuality(storages);
        Food milk = new Milk("Milk",
                LocalDate.of(2021, 5, 3),
                LocalDate.of(2021, 4, 1),
                100,
                0);
        cq.add(milk);
        assertThat(((Food) storages.get(0).get().get(0)).getDiscount(), is(0.25));
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
        assertThat(((Food) storages.get(0).get().get(0)).getName(), is("Egg"));
    }
}