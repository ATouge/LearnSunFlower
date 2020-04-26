package com.touge.learnsunflower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.touge.learnsunflower.databinding.FragmentPlantDetailBinding
import com.touge.learnsunflower.utilities.InjectorUtils
import com.touge.learnsunflower.viewmodel.PlantDetailViewModel

/**
 * @Author Touge
 * @Date 2020/4/26 21:21
 * @Description
 */
class PlantDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val plantId = requireNotNull(arguments).getString(ARG_ITEM_ID, "")
        val factory = InjectorUtils
                .providePlantDetailViewModelFactory(requireActivity(), plantId)
        val plantDetailViewModel = ViewModelProvider(this, factory)
                .get(PlantDetailViewModel::class.java)
        val binding = DataBindingUtil.inflate<FragmentPlantDetailBinding>(
                inflater, R.layout.fragment_plant_detail, container, false
        ).apply {
            viewModel = plantDetailViewModel
            lifecycleOwner = this@PlantDetailFragment
            fab.setOnClickListener { view ->
                plantDetailViewModel.addPlantToGarden()
                Snackbar.make(view, R.string.added_plant_to_garden, Snackbar.LENGTH_SHORT).show()
            }
        }

        val appCompatActivity = requireActivity() as AppCompatActivity
        appCompatActivity.setSupportActionBar(binding.detailToolbar)

        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        return binding.root
    }

    companion object {
        const val ARG_ITEM_ID = "item_id"

        fun newInstance(plantId: String): PlantDetailFragment {
            val bundle = Bundle().apply { putString(ARG_ITEM_ID, plantId) }
            return PlantDetailFragment().apply { arguments = bundle }
        }
    }
}