package com.touge.learnsunflower.data

import android.content.Context
import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.touge.learnsunflower.utilities.DATABASE_NAME
import com.touge.learnsunflower.utilities.runOnIoThread
import java.io.IOException
import java.nio.charset.Charset

/**
 * @Author Touge
 * @Date 2020/4/8 20:50
 * @Description 数据库层 单例
 */
@Database(entities = [GardenPlanting::class, Plant::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gardenPlantingDao(): GardenPlantingDao
    abstract fun plantDao(): PlantDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: deleteAndBuildDatabase(context).also { instance = it }
            }
        }

        private fun deleteAndBuildDatabase(context: Context): AppDatabase {
            context.deleteDatabase(DATABASE_NAME)
            return buildDatabase(context)
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            runOnIoThread { seedDatabase(context) }
                        }
                    })
                    .build()
        }

        private fun seedDatabase(context: Context) {
            val plantType = object : TypeToken<List<Plant>>() {}.type
            val plants: List<Plant> = Gson().fromJson(readJson(context), plantType)
            val database = getInstance(context)
            database.plantDao().insertAll(plants)
        }

        @VisibleForTesting
        private fun readJson(
                context: Context,
                fileName: String = "plants.json"): String {
            return try {
                val inputStream = context.assets.open(fileName)
                val buffer = ByteArray(inputStream.available())
                inputStream.run {
                    read(buffer)
                    close()
                }
                String(buffer, Charset.defaultCharset())
            } catch (e: IOException) {
                Log.e("AppDatabase", "read 'plants.json' error $e")
                ""
            }

        }

    }

}