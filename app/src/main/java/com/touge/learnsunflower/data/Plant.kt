package com.touge.learnsunflower.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * @Author Touge
 * @Date 2020/4/6 16:30
 * @Description 数据库模型 植物
 *
 */
@Entity(tableName = "plants")
data class Plant(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val plantId: String,
        val name: String,
        val description: String,
        val growZoneNumber: Int,
        // 浇水间隔，单位：天 默认是7天
        val wateringInterval: Int = 7,
        val imageUrl: String
) {

    /**
     * 确定植物是否需要浇水，
     * 如果 当前时间 > 上次浇水时间 + 浇水周期{@link waterInterval} 返回 true
     * 否则 返回 false
     */
    fun shouldBeWatered(lastWateringDate: Calendar) =
            Calendar.getInstance() > lastWateringDate.apply { add(Calendar.DAY_OF_YEAR, wateringInterval) }

    override fun toString() = name
}
