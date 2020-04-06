package com.touge.learnsunflower.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.touge.learnsunflower.data.Plant
import com.touge.learnsunflower.data.PlantRepository

/**
 * @Author Touge
 * @Date 2020/4/7 19:46
 * @Description ViewModel
 *
 */
class PlantListViewModel internal constructor(plantRepository: PlantRepository) : ViewModel() {
    val plants: LiveData<List<Plant>> = plantRepository.plants;
}