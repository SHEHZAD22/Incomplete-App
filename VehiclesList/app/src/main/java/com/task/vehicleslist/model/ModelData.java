package com.task.vehicleslist.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelData {

    @SerializedName("Count")
    private int count;

    @SerializedName("Message")
    private String message;

    @SerializedName("SearchCriteria")
    private String  searchCriteria;

    @SerializedName("Results")
    private List<IdResult> idResults;

    public ModelData(int count, String message, String searchCriteria, List<IdResult> idResults) {
        this.count = count;
        this.message = message;
        this.searchCriteria = searchCriteria;
        this.idResults = idResults;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public List<IdResult> getIdResults() {
        return idResults;
    }

    public void setIdResults(List<IdResult> idResults) {
        this.idResults = idResults;
    }

}
