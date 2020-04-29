package com.touge.learnsunflower.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.touge.learnsunflower.data.GardenPlantingRepository

/**
 * @Author Touge
 * @Date 2020/4/29 21:12
 * @Description
 */
class GardenPlantingListViewModelFactory(
        private val gardenPlantingRepository: GardenPlantingRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GardenPlantingListViewModel(gardenPlantingRepository) as T
    }

}