package com.touge.learnsunflower.data;

import android.content.Context;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import static com.touge.learnsunflower.utilities.AppExecutors.runOnIoThread;
import static com.touge.learnsunflower.utilities.Constants.DATABASE_NAME;

/**
 * @Author Touge
 * @Date 2020/4/6 16:37
 * @Description
 */
@Database(entities = {Plant.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static final Object LOCK = new Object();
    private static volatile AppDatabase sAppDatabase;

    public static AppDatabase getAppDatabase(Context context) {
        if (sAppDatabase == null) {
            synchronized (LOCK) {
                if (sAppDatabase == null) {
                    context.deleteDatabase(DATABASE_NAME);
                    sAppDatabase = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    runOnIoThread(() -> seedDatabase(getAppDatabase(context)));
                                }
                            })
                            .build();
                }
            }
        }
        return sAppDatabase;
    }

    private static void seedDatabase(AppDatabase appDatabase) {
        ArrayList<Plant> plants = new ArrayList<>(4);
        plants.add(new Plant("1", "Apple", "A red fruit"));
        plants.add(new Plant("2", "Beet", "A red root vegetable"));
        plants.add(new Plant("3", "Celery", "A green vegetable"));
        plants.add(new Plant("4", "Tomato", "A red vegetable"));

        appDatabase.plantDao().insertAll(plants);
    }

    public abstract PlantDao plantDao();
}
