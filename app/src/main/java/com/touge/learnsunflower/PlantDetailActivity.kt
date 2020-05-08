package com.touge.learnsunflower

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

/**
 * @Author Touge
 * @Date 2020/4/26 21:12
 * @Description
 */
class PlantDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_detail)

        if (savedInstanceState == null) {
            val plantId = intent.getStringExtra(PlantDetailFragment.ARG_ITEM_ID)
            val fragment = PlantDetailFragment.newInstance(plantId)
            supportFragmentManager.beginTransaction()
                    .add(R.id.plant_detail_container, fragment)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            navigateUpTo(Intent(this, GardenActivity::class.java))
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}