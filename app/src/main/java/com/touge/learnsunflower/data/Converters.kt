package com.touge.learnsunflower.data

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Author Touge
 * @Date 2020/4/20 20:01
 * @Description 日期转换
 */
class Converters {
    companion object {
        private val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.CHINA)
        @TypeConverter
        fun calendarToDateStamp(calendar: Calendar): String =
                dateFormat.format(calendar.time)

        @TypeConverter
        fun dateStampToCalendar(value: String): Calendar =
                Calendar.getInstance().apply { time = dateFormat.parse(value) }
    }
}