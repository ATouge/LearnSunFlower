package com.touge.learnsunflower.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.touge.learnsunflower.data.PlantRepository

/**
 * @Author Touge
 * @Date 2020/4/7 20:02
 * @Description
 *
 */
class PlantListViewModelFactory(
        private val plantRepository: PlantRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlantListViewModel(plantRepository) as T
    }
}