package com.task.vehicleslist.db;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "my_cars")
public class Cars {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String make;

    private String model;

    private String  imageUri;

    public Cars(int id,String make, String model) {
        this.make = make;
        this.model = model;
        this.id =id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public  String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
