package com.touge.learnsunflower.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * @Author Touge
 * @Date 2020/4/8 20:35
 * @Description 植物数据库 Dao 层
 */
@Dao
interface PlantDao {

    @Query("SELECT * FROM plants")
    fun getPlants(): LiveData<List<Plant>>

    @Query("SELECT * FROM plants WHERE id = :plantId")
    fun getPlant(plantId: String): LiveData<Plant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(plants: List<Plant>)

    @Query("SELECT * FROM plants WHERE growZoneNumber = :growZoneNumber")
    fun getPlantWithGrowZoneNumber(growZoneNumber: Int): LiveData<List<Plant>>

}