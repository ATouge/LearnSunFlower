package com.touge.learnsunflower.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
        val description: String
) {
    override fun toString() = name
}
