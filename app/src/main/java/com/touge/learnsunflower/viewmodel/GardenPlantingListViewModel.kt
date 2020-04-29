package com.touge.learnsunflower.viewmodel

import androidx.lifecycle.ViewModel
import com.touge.learnsunflower.data.GardenPlantingRepository

/**
 * @Author Touge
 * @Date 2020/4/29 21:07
 * @Description
 */
class GardenPlantingListViewModel internal constructor(
        private val gardenPlantingRepository: GardenPlantingRepository
) : ViewModel() {

    fun getGardenPlantings() = gardenPlantingRepository.getGardenPlantings()

    fun getPlantAndGardenPlantings() = gardenPlantingRepository.getPlantGardenPlantings()


}