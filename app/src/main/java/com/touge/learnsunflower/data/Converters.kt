package com.touge.learnsunflower.data

import androidx.room.TypeConverter
import java.util.*

/**
 * @Author Touge
 * @Date 2020/4/20 20:01
 * @Description 日期转换
 */
class Converters {
    @TypeConverter
    fun calendarToDateStamp(calendar: Calendar) = calendar.timeInMillis

    @TypeConverter
    fun dateStampToCalendar(value: Long) =
            Calendar.getInstance().apply { timeInMillis = value }
}