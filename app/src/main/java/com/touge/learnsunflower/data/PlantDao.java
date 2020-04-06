package com.touge.learnsunflower.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * @Author Touge
 * @Date 2020/4/6 16:28
 * @Description Dao 层
 */
@Dao
public interface PlantDao {

    @Query("SELECT * FROM plants")
    LiveData<List<Plant>> getPlants();

    @Query("SELECT * FROM plants WHERE id = :plantId")
    LiveData<Plant> getPlant(String plantId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Plant> plants);

}
