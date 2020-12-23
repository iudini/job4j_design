package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public String getName() {
        return name;
    }

    public boolean isOpened() {
        return opened;
    }

    public Building getBuilding() {
        return building;
    }

    public int[] getSchedule() {
        return schedule;
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
        JSONObject jsonShop = new JSONObject("{\"floors\":2}");

        List<Integer> list = new ArrayList<>();
        list.add(8);
        list.add(23);
        JSONArray jsonSchedule = new JSONArray(list);

        final Shop shop = new Shop("Mega", true, new Building(3), 10, 22);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", shop.name);
        jsonObject.put("opened", shop.opened);
        jsonObject.put("building", jsonShop);
        jsonObject.put("schedule", jsonSchedule);

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(shop));
    }
}
