package com.touge.learnsunflower.viewmodel;

import com.touge.learnsunflower.data.Plant;
import com.touge.learnsunflower.data.PlantRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * @Author Touge
 * @Date 2020/4/6 15:52
 * @Description
 */
public class PlantDetailViewModel extends ViewModel {

    private MutableLiveData<Plant> mPlant;

    private PlantDetailViewModel(String plantId) {
        if (mPlant == null) {
            mPlant = new MutableLiveData<>();
            mPlant.setValue(PlantRepository.getInstance().getPlant(plantId));
        }
    }

    public LiveData<Plant> getPlant() {
        return mPlant;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        private final String plantId;

        public Factory(@NonNull String plantId) {
            this.plantId = plantId;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new PlantDetailViewModel(plantId);
        }
    }


}
