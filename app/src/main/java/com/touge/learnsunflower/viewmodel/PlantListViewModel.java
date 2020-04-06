package com.touge.learnsunflower.viewmodel;

import com.touge.learnsunflower.data.Plant;
import com.touge.learnsunflower.data.PlantRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @Author Touge
 * @Date 2020/4/6 15:25
 * @Description
 */
public class PlantListViewModel extends ViewModel {

    private MutableLiveData<List<Plant>> mPlants;

    public PlantListViewModel() {
        if (mPlants == null) {
            mPlants = new MutableLiveData<>();
            mPlants.setValue(PlantRepository.getInstance().getPlants());
        }
    }

    public LiveData<List<Plant>> getPlants() {
        return mPlants;
    }
}
