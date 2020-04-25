package com.touge.learnsunflower.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.touge.learnsunflower.data.GardenPlantingRepository
import com.touge.learnsunflower.data.PlantRepository

/**
 * @Author Touge
 * @Date 2020/4/25 20:56
 * @Description
 */
class PlantDetailViewModelFactory(
        private val plantRepository: PlantRepository,
        private val gardenPlantingRepository: GardenPlantingRepository,
        private val plantId: String
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlantDetailViewModel(plantRepository, gardenPlantingRepository, plantId) as T
    }

}