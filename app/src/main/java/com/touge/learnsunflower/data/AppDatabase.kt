package com.touge.learnsunflower.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.touge.learnsunflower.utilities.DATABASE_NAME
import com.touge.learnsunflower.utilities.runOnIoThread

/**
 * @Author Touge
 * @Date 2020/4/8 20:50
 * @Description 数据库层 单例
 */
@Database(entities = [Plant::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

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
                            runOnIoThread { seedDatabase(getInstance(context)) }
                        }
                    })
                    .build()
        }

        private fun seedDatabase(instance: AppDatabase) {
            instance.plantDao().insertAll(ArrayList<Plant>(4).apply {
                add(Plant("1", "Apple", "A red fruit", 1,
                        "https://upload.wikimedia.org/wikipedia/commons/5/55" +
                                "/Apple_orchard_in_Tasmania.jpg"))
                add(Plant("2", "Beet", "A red root vegetable", 1,
                        "https://static.pexels.com/photos/264101/pexels-photo-264101.jpeg"))
                add(Plant("3", "Celery", "A green vegetable", 2,
                        "https://upload.wikimedia.org/wikipedia/commons/5/51" +
                                "/A_scene_of_Coriander_leaves.JPG"))
                add(Plant("4", "Tomato", "A red vegetable", 3,
                        "https://upload.wikimedia.org/wikipedia/commons/1/17" +
                                "/Cherry_tomatoes_red_and_green_2009_16x9.jpg"))
            })
        }

    }

}