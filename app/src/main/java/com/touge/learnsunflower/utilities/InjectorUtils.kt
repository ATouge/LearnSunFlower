package com.touge.learnsunflower.utilities

import android.app.Application
import com.touge.learnsunflower.data.AppDatabase
import com.touge.learnsunflower.data.PlantRepository
import com.touge.learnsunflower.viewmodel.PlantDetailViewModelFactory
import com.touge.learnsunflower.viewmodel.PlantListViewModelFactory

/**
 * @Author Touge
 * @Date 2020/4/7 20:10
 * @Description 静态类：提供 ViewModelFactory
 */
object InjectorUtils {
    private fun provideRepository(application: Application):PlantRepository {
        return PlantRepository.getInstance(AppDatabase.getInstance(application).plantDao())
    }

    @JvmStatic
    fun providePlantListViewModelFactory(application: Application): PlantListViewModelFactory {
        val repository = provideRepository(application)
        return PlantListViewModelFactory(repository)
    }

    @JvmStatic
    fun providePlantDetailViewModelFactory(application: Application, plantId: String): PlantDetailViewModelFactory {
        val repository = provideRepository(application)
        return PlantDetailViewModelFactory(repository, plantId)
    }

}