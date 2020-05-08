package com.touge.learnsunflower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.touge.learnsunflower.adapter.PlantAdapter
import com.touge.learnsunflower.utilities.InjectorUtils
import com.touge.learnsunflower.viewmodel.PlantListViewModel

/**
 * @Author Touge
 * @Date 2020/4/8 21:19
 * @Description
 */
class PlantListFragment : Fragment() {


    private lateinit var viewModel: PlantListViewModel

    private var arePlantsFiltered = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_plant_list,
                container, false
        )
        val context = context ?: return view

        val factory = InjectorUtils.providePlantListViewModelFactory(context)
        viewModel = ViewModelProvider(this, factory).get(PlantListViewModel::class.java)
        val adapter = PlantAdapter()
        view.findViewById<RecyclerView>(R.id.plant_list).adapter = adapter
        subscribeUi(adapter)
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.menu_plant_list, menu)
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
            viewModel.setGrowZoneNumber(9)
            true
        }
    }

    private fun subscribeUi(adapter: PlantAdapter) {
        viewModel.getPlants().observe(this, Observer { plants ->
            if (plants != null) {
                adapter.values = plants
            }
        })
    }

}