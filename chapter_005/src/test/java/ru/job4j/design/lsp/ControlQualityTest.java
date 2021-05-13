package ru.job4j.design.lsp;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ControlQualityTest {
    @Test
    public void addToWarehouse() {
        Storage storage = new Warehouse(new InMemoryStore());
        CQStorage cqStorage = new InMemoryCQStorage(List.of(storage));
        ControlQuality cq = new ControlQuality(cqStorage);
        Food meat = new Meat("Meat",
                LocalDate.of(2021, 12, 30),
                LocalDate.of(2021, 4, 1),
                100,
                0);
        cq.add(meat);
        assertThat(cqStorage.getStorages().get(0).get().get(0).getName(), is("Meat"));
    }

    @Test
    public void addToShop() {
        Storage storage = new Shop(new InMemoryStore());
        CQStorage cqStorage = new InMemoryCQStorage(List.of(storage));
        ControlQuality cq = new ControlQuality(cqStorage);
        Food milk = new Milk("Milk",
                LocalDate.of(2021, 7, 30),
                LocalDate.of(2021, 1, 1),
                100,
                0);
        cq.add(milk);
        assertThat(cqStorage.getStorages().get(0).get().get(0).getName(), is("Milk"));
    }

    @Test
    public void addToShopWithDiscount() {
        Storage storage = new Shop(new InMemoryStore());
        CQStorage cqStorage = new InMemoryCQStorage(List.of(storage));
        ControlQuality cq = new ControlQuality(cqStorage);
        Food milk = new Milk("Milk",
                LocalDate.of(2021, 5, 31),
                LocalDate.of(2021, 1, 1),
                100,
                0);
        cq.add(milk);
        assertThat(cqStorage.getStorages().get(0).get().get(0).getDiscount(), is(0.25));
    }

    @Test
    public void addToTrash() {
        Storage storage = new Trash(new InMemoryStore());
        CQStorage cqStorage = new InMemoryCQStorage(List.of(storage));
        ControlQuality cq = new ControlQuality(cqStorage);
        Food egg = new Egg("Egg",
                LocalDate.of(2021, 4, 20),
                LocalDate.of(2021, 4, 1),
                100,
                0);
        cq.add(egg);
        assertThat(cqStorage.getStorages().get(0).get().get(0).getName(), is("Egg"));
    }

    @Test
    public void addToShopAfterChangeDatesAndResort() {
        Storage storage = new Shop(new InMemoryStore());
        CQStorage cqStorage = new InMemoryCQStorage(List.of(storage));
        ControlQuality cq = new ControlQuality(cqStorage);
        Food milk = new Milk("Milk",
                LocalDate.of(2021, 7, 30),
                LocalDate.of(2021, 1, 1),
                100,
                0);
        cq.add(milk);
        milk.setExpireDate(LocalDate.of(2021, 5, 30));
        cq.resort();
        assertThat(cqStorage.getStorages().get(0).get().get(0).getDiscount(), is(0.25));
    }
}