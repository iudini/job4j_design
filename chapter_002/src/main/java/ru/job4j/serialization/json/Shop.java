package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Shop {
    private final String name;
    private final boolean opened;
    private final Building building;
    private final int[] schedule;

    public Shop(String name, boolean opened, Building building, int... schedule) {
        this.name = name;
        this.opened = opened;
        this.building = building;
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Shop{"
                + "name='" + name + '\''
                + ", opened=" + opened
                + ", building=" + building
                + ", schedule=" + Arrays.toString(schedule)
                + '}';
    }

    public static void main(String[] args) {
        final Shop shop = new Shop("Lenta", true, new Building(2), 0, 24);

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(shop));

        final String shopToJson =
                "{"
                + "\"name\"=\"Auschan\","
                + "\"opened\"=false,"
                + "\"building\":"
                + "{"
                + "\"floors\":1"
                + "},"
                + "\"schedule\":[8,22]"
                + "}";
        final Shop shopFromJson = gson.fromJson(shopToJson, Shop.class);
        System.out.println(shopFromJson);
    }
}
