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
        /**
         * 表明：何时种植植物，用于需要收割植物显示通知
         */
        @ColumnInfo(name = "plant_date")
        val plantDate: Calendar = Calendar.getInstance(),
        /**
         * 表明：上次浇水时间，用于显示下次浇水时间通知
         */
        @ColumnInfo(name = "last_watering_date")
        val lastWateringDate: Calendar = Calendar.getInstance()
)