package com.task.vehicleslist.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MakeData {

   @SerializedName("Count")
    private int count;

   @SerializedName("Message")
    private String message;

   @SerializedName("SearchCriteria")
    private String  searchCriteria;

   @SerializedName("Results")
    private List<Results> results;

    public MakeData(int count, String message, String searchCriteria, List<Results> results) {
        this.count = count;
        this.message = message;
        this.searchCriteria = searchCriteria;
        this.results = results;
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

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }
}
