package com.task.vehicleslist.model;

import com.google.gson.annotations.SerializedName;

public class Results {
    @SerializedName("Make_ID")
    private Integer makeId;

    @SerializedName("Make_Name")
    private String makeName;

    public Results(Integer makeId, String makeName) {
        this.makeId = makeId;
        this.makeName = makeName;
    }

    public Integer getMakeId() {
        return makeId;
    }

    public void setMakeId(Integer makeId) {
        this.makeId = makeId;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }
}
