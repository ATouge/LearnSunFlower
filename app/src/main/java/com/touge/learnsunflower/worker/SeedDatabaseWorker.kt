package com.touge.learnsunflower.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.touge.learnsunflower.data.AppDatabase
import com.touge.learnsunflower.data.Plant
import com.touge.learnsunflower.utilities.PLANT_DATA_FILE_NAME

/**
 * @Author Touge
 * @Date 2020/5/7 12:41
 * @Description
 */
class SeedDatabaseWorker(appContext: Context, workerParameters: WorkerParameters)
    : Worker(appContext, workerParameters) {

    private val TAG = SeedDatabaseWorker::class.java.simpleName

    override fun doWork(): Result {
        val plantType = object : TypeToken<List<Plant>>() {}.type
        var jsonReader: JsonReader? = null
        return try {
            val inputStream = applicationContext.assets.open(PLANT_DATA_FILE_NAME)
            jsonReader = JsonReader(inputStream.reader())
            val plantList: List<Plant> = Gson().fromJson(jsonReader, plantType)
            val database = AppDatabase.getInstance(applicationContext)
            database.plantDao().insertAll(plantList)
            Result.success()
        } catch (ex: Exception) {
            Log.e(TAG, "seeding database error: ", ex)
            Result.failure()
        }
    }
}