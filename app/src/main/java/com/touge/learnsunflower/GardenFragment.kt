package com.touge.learnsunflower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.touge.learnsunflower.databinding.FragmentGardenBinding

/**
 * @Author Touge
 * @Date 2020/4/29 21:36
 * @Description
 */
class GardenFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentGardenBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_garden, container, false
        )

        return binding.root
    }

}