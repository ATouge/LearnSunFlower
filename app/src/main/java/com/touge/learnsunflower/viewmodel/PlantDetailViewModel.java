package com.touge.learnsunflower.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.touge.learnsunflower.data.GardenPlantingRepository;
import com.touge.learnsunflower.data.Plant;
import com.touge.learnsunflower.data.PlantRepository;

/**
 * @Author Touge
 * @Date 2020/4/6 15:52
 * @Description
 */
public class PlantDetailViewModel extends ViewModel {

    private GardenPlantingRepository mGardenPlantingRepository;
    private LiveData<Boolean> isPlanted;
    private LiveData<Plant> mPlant;
    private String mPlantId;

    public PlantDetailViewModel(PlantRepository repository,
                                GardenPlantingRepository gardenPlantingRepository,
                                String plantId) {
        mPlant = repository.getPlant(plantId);
        mGardenPlantingRepository = gardenPlantingRepository;
        mPlantId = plantId;
        isPlanted = Transformations.map(gardenPlantingRepository.getGardenPlantingForPlant(plantId),
                result -> (result != null));
    }

    public LiveData<Plant> getPlant() {
        return mPlant;
    }

    public LiveData<Boolean> isPlanted() {
        return isPlanted;
    }

    public void addPlantToGarden() {
        mGardenPlantingRepository.createGardenPlanting(mPlantId);
    }
}
