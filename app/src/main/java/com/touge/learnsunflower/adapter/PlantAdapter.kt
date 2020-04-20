package com.touge.learnsunflower.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.touge.learnsunflower.PlantDetailActivity
import com.touge.learnsunflower.PlantDetailFragment
import com.touge.learnsunflower.PlantListActivity
import com.touge.learnsunflower.R
import com.touge.learnsunflower.data.Plant

/**
 * @Author Touge
 * @Date 2020/4/8 21:31
 * @Description
 */
class PlantAdapter(plantListActivity: PlantListActivity,
                   isTwoPone: Boolean) : RecyclerView.Adapter<PlantAdapter.ViewHolder>() {

    var values: List<Plant> = ArrayList<Plant>(0)
        set(item) {
            field = item
            notifyDataSetChanged()
        }

    private var onClickListener = View.OnClickListener { view ->
        val item = view.tag as Plant
        if (isTwoPone) {
            val fragment = PlantDetailFragment.newInstance(item.plantId)
            plantListActivity.supportFragmentManager.beginTransaction()
                    .replace(R.id.plant_detail_container, fragment)
                    .commit()
        } else {
            val intent = Intent(view.context, PlantDetailActivity::class.java).apply {
                putExtra(PlantDetailFragment.ARG_ITEM_ID, item.plantId)
            }
            view.context.startActivity(intent)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_plant, parent, false
        ))
    }

    override fun getItemCount(): Int {
        return values.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            idView.text = values[position].plantId
            contentView.text = values[position].name
            with(itemView) {
                tag = values[position]
                setOnClickListener(onClickListener)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idView: TextView = itemView.findViewById(R.id.id_text)
        val contentView: TextView = itemView.findViewById(R.id.content)
    }

}