package com.touge.learnsunflower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.touge.learnsunflower.adapter.GardenPlantingAdapter
import com.touge.learnsunflower.utilities.InjectorUtils
import com.touge.learnsunflower.viewmodel.GardenPlantingListViewModel

/**
 * @Author Touge
 * @Date 2020/4/29 21:36
 * @Description
 */
class GardenFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_garden,
                container, false
        )
        val adapter = GardenPlantingAdapter(view.context)
        view.findViewById<RecyclerView>(R.id.garden_list).adapter = adapter
        subscribeUi(adapter)

        return view
    }

    private fun subscribeUi(adapter: GardenPlantingAdapter) {
        val factory = InjectorUtils.provideGardenPlantingListViewModelFactory(requireContext())
        val viewModel = ViewModelProvider(this, factory)
                .get(GardenPlantingListViewModel::class.java)
        viewModel.getGardenPlantings().observe(this, Observer { plantings ->
            if (plantings != null && plantings.isNotEmpty()) {
                activity?.run {
                    findViewById<RecyclerView>(R.id.garden_list).run { visibility = View.VISIBLE }
                    findViewById<TextView>(R.id.empty_garden).run { visibility = View.GONE }
                }
            } else {
                activity?.run {
                    findViewById<RecyclerView>(R.id.garden_list).run { visibility = View.GONE }
                    findViewById<TextView>(R.id.empty_garden).run { visibility = View.VISIBLE }
                }
            }
        })
        viewModel.getPlantAndGardenPlantings().observe(this, Observer { result ->
            if (result != null && result.isNotEmpty()) {
                adapter.values = result.filter { it.gardenPlantings.isNotEmpty() }
            }
        })
    }

}