package com.touge.learnsunflower.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.touge.learnsunflower.data.GardenPlantingRepository
import com.touge.learnsunflower.data.Plant
import com.touge.learnsunflower.data.PlantRepository

/**
 * @Author Touge
 * @Date 2020/4/25 20:49
 * @Description
 */
class PlantDetailViewModel(
        plantRepository: PlantRepository,
        private val gardenPlantingRepository: GardenPlantingRepository,
        private val plantId: String
) : ViewModel() {
    val isPlanted: LiveData<Boolean>
    val plant: LiveData<Plant>

    init {
        val gardenPlantingForPlant = gardenPlantingRepository.getGardenPlantingForPlant(plantId)
        isPlanted = Transformations.map(gardenPlantingForPlant) { it != null }
        plant = plantRepository.getPlant(plantId)
    }

    fun addPlantToGarden() {
        gardenPlantingRepository.createGardenPlanting(plantId)
    }


}