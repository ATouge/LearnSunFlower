package com.touge.learnsunflower;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.touge.learnsunflower.adapter.PlantAdapter;
import com.touge.learnsunflower.data.PlantContent;
import com.touge.learnsunflower.databinding.ActivityPlantListBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author Touge
 * @Date 2020/4/4 22:15
 * @Description
 */
public class PlantListActivity extends AppCompatActivity {

    private boolean isTwoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPlantListBinding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_plant_list);
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitle(getTitle());

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"TODO: Add new plant to plant list",Snackbar.LENGTH_SHORT)
                        .setAction("Action",null).show();
            }
        });

//        if (binding.plantListFrame.plantDetailContainer != null) {
//
//        }

        setupRecyclerView(binding.plantListFrame.plantList);
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setAdapter(new PlantAdapter(this,
                PlantContent.ITEMS, isTwoPane));
    }
}
