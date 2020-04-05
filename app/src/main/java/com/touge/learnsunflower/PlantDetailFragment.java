package com.touge.learnsunflower;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.touge.learnsunflower.data.Plant;
import com.touge.learnsunflower.data.PlantContent;
import com.touge.learnsunflower.databinding.FragmentPlantDetailBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = PlantContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            CollapsingToolbarLayout toolbarLayout = getActivity().findViewById(R.id.toolbar_layout);
            if (toolbarLayout != null) {
                toolbarLayout.setTitle(mItem.name);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_plant_detail, container, false);

        if (mItem != null) {
            mBinding.plantDetail.setText(mItem.details);
        }

        return mBinding.getRoot();
    }
}
