package com.task.vehicleslist.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.task.vehicleslist.supportclass.Converters;

@Database(entities = {Cars.class},version = 7)
@TypeConverters({Converters.class}) //bitmap to string
public abstract class MyDatabase extends RoomDatabase {
    public abstract CarsDao carsDao();

    public static MyDatabase dbInstance;

    public static MyDatabase getDbInstance(Context context){
        if (dbInstance == null){
            dbInstance = Room.databaseBuilder(context, MyDatabase.class, "car_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return dbInstance;
    }

}
