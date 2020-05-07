package com.touge.learnsunflower.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.touge.learnsunflower.R
import com.touge.learnsunflower.data.PlantAndGardenPlantings
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * @Author Touge
 * @Date 2020/5/7 21:05
 * @Description
 */
class GardenPlantingAdapter(private val context: Context) : RecyclerView.Adapter<GardenPlantingAdapter.ViewHolder>() {

    var values: List<PlantAndGardenPlantings> = ArrayList(0)
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_garden_planting,
                parent, false))
    }

    override fun getItemCount(): Int {
        return values.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val plant = checkNotNull(values[position].plant)
        val gardenPlanting = values[position].gardenPlantings[0]
        val plantDateString = dateFormat.format(gardenPlanting.plantDate.time)
        val waterDateString = dateFormat.format(gardenPlanting.lastWateringDate.time)
        val wateringPrefix = context.getString(R.string.watering_next_prefix, waterDateString)
        val wateringSuffix = context.resources.getQuantityString(R.plurals.watering_next_suffix,
                plant.wateringInterval, plant.wateringInterval)
        holder.apply {
            Glide.with(context)
                    .load(plant.imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(plantImageView)
            plantDateView.text = context.getString(R.string.plant_date, plant.name, plantDateString)
            waterDateView.text = "$wateringPrefix - $wateringSuffix"
            itemView.tag = values[position]
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val plantImageView: ImageView = itemView.findViewById(R.id.image_view)
        val plantDateView: TextView = itemView.findViewById(R.id.plant_date)
        val waterDateView: TextView = itemView.findViewById(R.id.water_date)
    }
}
