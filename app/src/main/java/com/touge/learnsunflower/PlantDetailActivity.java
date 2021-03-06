package com.touge.learnsunflower;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.touge.learnsunflower.databinding.ActivityPlantDetailBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

/**
 * @Author Touge
 * @Date 2020/4/5 9:36
 * @Description
 */
public class PlantDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPlantDetailBinding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_plant_detail);

        setSupportActionBar(binding.detailToolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "TODO: Add plant to my garden", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            String plantId = getIntent().getStringExtra(PlantDetailFragment.ARG_ITEM_ID);
            PlantDetailFragment fragment =PlantDetailFragment.newInstance(plantId);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.plant_detail_container, fragment)
                    .commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            navigateUpTo(new Intent(this, PlantListActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
