package com.touge.learnsunflower.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.touge.learnsunflower.data.GardenPlantingRepository
import com.touge.learnsunflower.data.PlantAndGardenPlantings

/**
 * @Author Touge
 * @Date 2020/4/29 21:07
 * @Description
 */
class GardenPlantingListViewModel internal constructor(
        private val gardenPlantingRepository: GardenPlantingRepository
) : ViewModel() {

    fun getGardenPlantings() = gardenPlantingRepository.getGardenPlantings()

    fun getPlantAndGardenPlantings(): LiveData<List<PlantAndGardenPlantings>> {
        return Transformations.map(gardenPlantingRepository.getPlantGardenPlantings()) { it ->
            it.filter { it.gardenPlantings.isNotEmpty() }
        }
    }


}