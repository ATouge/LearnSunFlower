package com.touge.learnsunflower;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.touge.learnsunflower.databinding.FragmentPlantDetailBinding;
import com.touge.learnsunflower.utilities.InjectorUtils;
import com.touge.learnsunflower.viewmodel.PlantDetailViewModel;
import com.touge.learnsunflower.viewmodel.PlantDetailViewModelFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String plantId = getArguments().getString(ARG_ITEM_ID);

        PlantDetailViewModelFactory factory = InjectorUtils
                .providePlantDetailViewModelFactory(getActivity(), plantId);
        PlantDetailViewModel viewModel = new ViewModelProvider(this, factory)
                .get(PlantDetailViewModel.class);
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_plant_detail, container, false);
        mBinding.setViewModel(viewModel);
        mBinding.setLifecycleOwner(this);

        AppCompatActivity appCompatActivity = (AppCompatActivity) requireActivity();
        appCompatActivity.setSupportActionBar(mBinding.detailToolbar);
        mBinding.fab.setOnClickListener(view -> {
            viewModel.addPlantToGarden();
            Snackbar.make(view, R.string.added_plant_to_garden, Snackbar.LENGTH_SHORT).show();
        });

        ActionBar actionBar = appCompatActivity.getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        return mBinding.getRoot();
    }
}
