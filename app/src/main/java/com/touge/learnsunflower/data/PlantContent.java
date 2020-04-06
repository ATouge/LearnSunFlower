package com.touge.learnsunflower.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Touge
 * @Date 2020/4/5 9:18
 * @Description
 */
class PlantContent {

    private static final int NUM_ITEMS = 4;

    static final List<Plant> ITEMS = new ArrayList<>(NUM_ITEMS);

    static final Map<String, Plant> ITEM_MAP = new HashMap<>(NUM_ITEMS);

    static {
        addItem(new Plant("1", "Apple", "A red fruit"));
        addItem(new Plant("2", "Beet", "A red root vegetable"));
        addItem(new Plant("3", "Cucumber", "A green vegetable"));
        addItem(new Plant("4", "Tomato", "A red vegetable"));
    }

    private static void addItem(Plant plant) {
        ITEMS.add(plant);
        ITEM_MAP.put(plant.getPlantId(), plant);
    }

}
