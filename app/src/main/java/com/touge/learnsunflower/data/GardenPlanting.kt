package com.touge.learnsunflower.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

/**
 * @Author Touge
 * @Date 2020/4/20 21:20
 * @Description
 */
@Entity(tableName = "garden_plantings", foreignKeys = [ForeignKey(entity = Plant::class
        , parentColumns = ["id"], childColumns = ["plant_id"])])
data class GardenPlanting(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val gardenPlantingId: String,
        @ColumnInfo(name = "plant_id")
        val plantId: String,
        val plantDate: Calendar = Calendar.getInstance(),
        val lastWateringDate: Calendar = Calendar.getInstance()
)