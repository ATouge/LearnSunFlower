package com.touge.learnsunflower

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.touge.learnsunflower.adapter.PlantAdapter
import com.touge.learnsunflower.databinding.ActivityPlantListBinding
import com.touge.learnsunflower.utilities.InjectorUtils
import com.touge.learnsunflower.viewmodel.PlantListViewModel

/**
 * @Author Touge
 * @Date 2020/4/8 21:19
 * @Description
 */
class PlantListActivity : AppCompatActivity() {

    private var isTwoPone: Boolean = false

    private lateinit var binding: ActivityPlantListBinding

    private lateinit var adapter: PlantAdapter

    private lateinit var viewModel: PlantListViewModel

    private var arePlantsFiltered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_plant_list
        )
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.run {
            toolbar.title = title
        }

        if (binding.plantListFrame.plantDetailContainer != null) {

            isTwoPone = true
        }

        adapter = PlantAdapter(this, isTwoPone)
        binding.plantListFrame?.plantList?.adapter = adapter

        val factory = InjectorUtils.providePlantListViewModelFactory(application)
        viewModel = ViewModelProvider(this, factory).get(PlantListViewModel::class.java)
        subscribeUi()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_plant_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.filter_zone -> {
            updateData()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun updateData() {
        arePlantsFiltered = if (arePlantsFiltered) {
            viewModel.clearGrowZoneNumber()
            false
        } else {
            viewModel.setGrowZoneNumber(1)
            true
        }
    }

    private fun subscribeUi() {
        viewModel.getPlants().observe(this, Observer { plants ->
            if (plants != null) {
                adapter.values = plants
            }
        })
    }

}