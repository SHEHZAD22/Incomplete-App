package com.task.vehicleslist.data;

import com.task.vehicleslist.model.IdResult;
import com.task.vehicleslist.model.MakeData;
import com.task.vehicleslist.model.ModelData;
import com.task.vehicleslist.model.Results;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiMethods {

    @GET("api/vehicles/getallmakes?format=json")
    Call<MakeData> getAllMakes();

    @GET("api/vehicles/GetModelsForMakeId/{MakeId}?format=json")
    Call<ModelData> getAllModels(@Path("MakeId") int makeId);

//    @GET("api/vehicles/getallmakes?format=json")
//    Call<List<Results>> getAllMakes();
//
//    @GET("api/vehicles/GetModelsForMakeId/{MakeId}?format=json")
//    Call<List<IdResult>> getAllModels(@Path("MakeId") int makeId);

}
