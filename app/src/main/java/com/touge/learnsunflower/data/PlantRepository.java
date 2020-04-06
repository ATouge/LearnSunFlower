package com.touge.learnsunflower.data;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * @Author Touge
 * @Date 2020/4/6 15:28
 * @Description
 */
public class PlantRepository {

    public static final Object LOCK = new Object();
    private static PlantRepository ourInstance;
    private AppDatabase mAppDatabase;

    private PlantRepository(AppDatabase appDatabase) {
        mAppDatabase = appDatabase;
    }

    private PlantRepository() {
    }

    public static PlantRepository getInstance(AppDatabase appDatabase) {
        if (ourInstance == null) {
            synchronized (LOCK) {
                if (ourInstance == null) {
                    ourInstance = new PlantRepository(appDatabase);
                }
            }
        }
        return ourInstance;
    }

    public LiveData<List<Plant>> getPlants() {
        return mAppDatabase.plantDao().getPlants();
    }

    public LiveData<Plant> getPlant(String plantId) {
        return mAppDatabase.plantDao().getPlant(plantId);
    }
}
