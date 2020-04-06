package com.touge.learnsunflower.viewmodel;

import com.touge.learnsunflower.data.Plant;
import com.touge.learnsunflower.data.PlantRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

/**
 * @Author Touge
 * @Date 2020/4/6 15:52
 * @Description
 */
public class PlantDetailViewModel extends ViewModel {

    private LiveData<Plant> mPlant;

    public PlantDetailViewModel(PlantRepository repository, String plantId) {
            mPlant = repository.getPlant(plantId);
    }

    public LiveData<Plant> getPlant() {
        return mPlant;
    }
}
