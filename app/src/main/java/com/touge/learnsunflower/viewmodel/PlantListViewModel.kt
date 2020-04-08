package com.touge.learnsunflower.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
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

    private val NO_GROW_ZONE = -1
    private val plantRepository: PlantRepository = plantRepository
    private val growZoneNumber: MutableLiveData<Int> = MutableLiveData()

    init {
        growZoneNumber.value = NO_GROW_ZONE
    }

    fun getPlants(): LiveData<List<Plant>> = Transformations.switchMap(growZoneNumber) {
        if (it == NO_GROW_ZONE) {
            plantRepository.getPlants()
        } else {
            plantRepository.getPlantsWithGrowZoneNumber(it)
        }
    }

    fun setGrowZoneNumber(num: Int) {
        growZoneNumber.value = num
    }

    fun clearGrowZoneNumber() {
        growZoneNumber.value = NO_GROW_ZONE
    }

}