package com.task.vehicleslist.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CarsDao {
    @Insert
    void insert(Cars cars);

    @Query("SELECT * FROM my_cars")
    List<Cars> getAll();

    @Query("SELECT EXISTS(SELECT * FROM my_cars WHERE make = :make AND model = :model)")
    Boolean isExist(String make, String model);

    @Query("DELETE from my_cars WHERE id = :id")
    void delete(int id);

    @Query("UPDATE my_cars SET imageUri = :image WHERE id = :id")
    void updateById(int id, String image);

    @Query("SELECT imageUri FROM my_cars WHERE id = :id")
    List<Cars> getImageById(int id);

}
