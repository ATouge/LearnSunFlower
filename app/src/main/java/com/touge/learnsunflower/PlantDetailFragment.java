package com.touge.learnsunflower;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.DrawableTransformation;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.touge.learnsunflower.data.Plant;
import com.touge.learnsunflower.databinding.FragmentPlantDetailBinding;
import com.touge.learnsunflower.utilities.InjectorUtils;
import com.touge.learnsunflower.viewmodel.PlantDetailViewModel;
import com.touge.learnsunflower.viewmodel.PlantDetailViewModelFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

/**
 * @Author Touge
 * @Date 2020/4/5 9:57
 * @Description
 */
public class PlantDetailFragment extends Fragment {


    public static final String ARG_ITEM_ID = "item_id";
    private Plant mItem;

    private FragmentPlantDetailBinding mBinding;


    public PlantDetailFragment() {
    }

    public static PlantDetailFragment newInstance(String plantId) {

        Bundle args = new Bundle();
        args.putString(ARG_ITEM_ID, plantId);
        PlantDetailFragment fragment = new PlantDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String plantId = getArguments().getString(ARG_ITEM_ID);

        PlantDetailViewModelFactory factory = InjectorUtils
                .providePlantDetailViewModelFactory(getActivity().getApplication(), plantId);
        PlantDetailViewModel viewModel = new ViewModelProvider(this, factory)
                .get(PlantDetailViewModel.class);
        subscribeToModel(viewModel);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_plant_detail, container, false);

        return mBinding.getRoot();
    }

    private void subscribeToModel(PlantDetailViewModel viewModel) {
        viewModel.getPlant().observe(this, this::updateUi);
    }

    private void updateUi(Plant plant) {
        mBinding.plantDetail.setText(plant.getDescription());
        CollapsingToolbarLayout toolbarLayout = getActivity().findViewById(R.id.toolbar_layout);
        if (toolbarLayout != null) {
            toolbarLayout.setTitle(plant.getName());
        }

        ImageView imageView = getActivity().findViewById(R.id.detail_image);
        if (imageView != null && !plant.getImageUrl().isEmpty()) {
            Glide.with(this)
                    .load(plant.getImageUrl())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);
        }
    }
}
