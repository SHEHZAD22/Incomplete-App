package com.task.vehicleslist.model;

import com.google.gson.annotations.SerializedName;

public class IdResult {

    @SerializedName("Model_Name")
    private String modelName;

    public IdResult(String modelName) {
        this.modelName = modelName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
