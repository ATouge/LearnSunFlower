package com.touge.learnsunflower.data

/**
 * @Author Touge
 * @Date 2020/4/8 20:26
 * @Description Repository层
 */
class PlantRepository private constructor(private val plantDao: PlantDao) {

    fun getPlant(plantId: String) = plantDao.getPlant(plantId)

    fun getPlants() = plantDao.getPlants()

    fun getPlantsWithGrowZoneNumber(growZoneNumber: Int) =
            plantDao.getPlantWithGrowZoneNumber(growZoneNumber)

    companion object {

        @Volatile
        private var instance: PlantRepository? = null

        @JvmStatic
        fun getInstance(plantDao: PlantDao): PlantRepository {
            return instance ?: synchronized(this) {
                instance ?: PlantRepository(plantDao).also { instance = it }
            }
        }

    }
}