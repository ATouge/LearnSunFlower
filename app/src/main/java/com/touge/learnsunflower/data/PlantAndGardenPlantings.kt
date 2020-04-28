package com.touge.learnsunflower.data

import androidx.room.Embedded
import androidx.room.Relation

/**
 * @Author Touge
 * @Date 2020/4/28 21:11
 * @Description 这个类是用来对 Plant 和 GardenPlanting 进行转换
 */
class PlantAndGardenPlantings {

    @Embedded
    var plant: Plant? = null

    @Relation(parentColumn = "id",entityColumn = "plant_id")
    var gardenPlantings: List<GardenPlanting> = arrayListOf()

}