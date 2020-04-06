package com.touge.learnsunflower.viewmodel;

import com.touge.learnsunflower.data.PlantRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * @Author Touge
 * @Date 2020/4/7 19:58
 * @Description
 */
public class PlantDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final PlantRepository mPlantRepository;

    @NonNull
    private final String plantId;

    public PlantDetailViewModelFactory(@NonNull PlantRepository plantRepository, @NonNull String plantId) {
        mPlantRepository = plantRepository;
        this.plantId = plantId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PlantDetailViewModel(mPlantRepository, plantId);
    }
}
