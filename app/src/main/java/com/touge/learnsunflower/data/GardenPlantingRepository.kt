package com.touge.learnsunflower.data

import com.touge.learnsunflower.utilities.runOnIoThread

/**
 * @Author Touge
 * @Date 2020/4/21 20:07
 * @Description
 */
class GardenPlantingRepository private constructor(
        private val gardenPlantingDao: GardenPlantingDao
) {
    fun createGardenPlanting(plantId: String) {
        runOnIoThread {
            val gardenPlanting = GardenPlanting("gp$plantId", plantId)
            gardenPlantingDao.insertGardenPlanting(gardenPlanting)
        }
    }

    fun getGardenPlantingForPlant(plantId: String) =
            gardenPlantingDao.getGardenPlantingForPlant(plantId)

    fun getGardenPlantings() =
            gardenPlantingDao.getGardenPlantings()

    fun getPlantGardenPlantings() =
            gardenPlantingDao.getPlantAndGardenPlantings()

    companion object {
        @Volatile
        private var instance: GardenPlantingRepository? = null

        fun getInstance(gardenPlantingDao: GardenPlantingDao) =
                instance ?: synchronized(this) {
                    instance ?: GardenPlantingRepository(gardenPlantingDao).also { instance = it }
                }

    }
}