package com.touge.learnsunflower;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Author Touge
 * @Date 2020/4/5 9:36
 * @Description
 */
public class PlantDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_detail);

        if (savedInstanceState == null) {
            String plantId = getIntent().getStringExtra(PlantDetailFragment.ARG_ITEM_ID);
            PlantDetailFragment fragment = PlantDetailFragment.newInstance(plantId);
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
