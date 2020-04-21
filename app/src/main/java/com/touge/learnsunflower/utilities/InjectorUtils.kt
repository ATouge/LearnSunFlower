package com.touge.learnsunflower.utilities

import android.content.Context
import com.touge.learnsunflower.data.AppDatabase
import com.touge.learnsunflower.data.GardenPlantingRepository
import com.touge.learnsunflower.data.PlantRepository
import com.touge.learnsunflower.viewmodel.PlantDetailViewModelFactory
import com.touge.learnsunflower.viewmodel.PlantListViewModelFactory

/**
 * @Author Touge
 * @Date 2020/4/7 20:10
 * @Description 静态类：提供 ViewModelFactory
 */
object InjectorUtils {

    private fun getPlantRepository(context: Context): PlantRepository {
        return PlantRepository.getInstance(AppDatabase.getInstance(context).plantDao())
    }

    private fun getGardenPlantingRepository(context: Context): GardenPlantingRepository {
        return GardenPlantingRepository.getInstance(
                AppDatabase.getInstance(context).gardenPlantingDao())
    }

    @JvmStatic
    fun providePlantListViewModelFactory(context: Context): PlantListViewModelFactory {
        val repository = getPlantRepository(context)
        return PlantListViewModelFactory(repository)
    }

    @JvmStatic
    fun providePlantDetailViewModelFactory(context: Context, plantId: String): PlantDetailViewModelFactory {
        return PlantDetailViewModelFactory(getPlantRepository(context),
                getGardenPlantingRepository(context), plantId)
    }

}