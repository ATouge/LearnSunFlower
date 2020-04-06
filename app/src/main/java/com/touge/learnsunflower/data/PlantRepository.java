package com.touge.learnsunflower.data;

import java.util.List;

/**
 * @Author Touge
 * @Date 2020/4/6 15:28
 * @Description
 */
public class PlantRepository {
    private static final PlantRepository ourInstance = new PlantRepository();

    public static PlantRepository getInstance() {
        return ourInstance;
    }

    private PlantRepository() {
    }

    public List<Plant> getPlants() {
        return PlantContent.ITEMS;
    }

    public Plant getPlant(String plantId) {
        return PlantContent.ITEM_MAP.get(plantId);
    }
}
