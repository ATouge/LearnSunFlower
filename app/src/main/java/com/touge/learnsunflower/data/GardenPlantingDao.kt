package com.touge.learnsunflower.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

/**
 * @Author Touge
 * @Date 2020/4/20 21:26
 * @Description
 */
@Dao
interface GardenPlantingDao {

    @Query("SELECT * FROM garden_plantings")
    fun getGardenPlantings(): LiveData<List<GardenPlanting>>

    @Query("SELECT * FROM garden_plantings WHERE id = :gardenPlantingId")
    fun getGardenPlanting(gardenPlantingId: String): LiveData<GardenPlanting>

    @Query("SELECT * FROM garden_plantings WHERE plant_id =:plantId")
    fun getGardenPlantingForPlant(plantId: String): LiveData<GardenPlanting>

    @Transaction
    @Query("SELECT * FROM plants")
    fun getPlantAndGardenPlantings(): LiveData<List<PlantAndGardenPlantings>>

    @Insert
    fun insertGardenPlanting(gardenPlanting: GardenPlanting)

}