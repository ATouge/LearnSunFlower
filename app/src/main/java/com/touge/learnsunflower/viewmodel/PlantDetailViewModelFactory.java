package com.touge.learnsunflower.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.touge.learnsunflower.data.GardenPlantingRepository;
import com.touge.learnsunflower.data.PlantRepository;

/**
 * @Author Touge
 * @Date 2020/4/7 19:58
 * @Description
 */
public class PlantDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final PlantRepository mPlantRepository;

    @NonNull
    private final GardenPlantingRepository mGardenPlantingRepository;

    @NonNull
    private final String plantId;

    public PlantDetailViewModelFactory(@NonNull PlantRepository plantRepository,
                                       @NonNull GardenPlantingRepository gardenPlantingRepository, @NonNull String plantId) {
        mGardenPlantingRepository = gardenPlantingRepository;
        mPlantRepository = plantRepository;
        this.plantId = plantId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PlantDetailViewModel(mPlantRepository, mGardenPlantingRepository, plantId);
    }
}
