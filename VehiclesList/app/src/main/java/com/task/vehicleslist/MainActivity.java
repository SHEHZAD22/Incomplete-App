package com.task.vehicleslist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.task.vehicleslist.data.AdapterListener;
import com.task.vehicleslist.data.CarAdapter;
import com.task.vehicleslist.db.Cars;
import com.task.vehicleslist.db.CarsDao;
import com.task.vehicleslist.db.MyDatabase;
import com.task.vehicleslist.model.IdResult;
import com.task.vehicleslist.model.MakeData;
import com.task.vehicleslist.data.Retro;
import com.task.vehicleslist.model.ModelData;
import com.task.vehicleslist.model.Results;
import com.task.vehicleslist.supportclass.Converters;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, AdapterListener {

    private final int IMG_REQ_CODE = 1;

    Spinner makeSpinner, modelSpinner;
    Button logOut, addCar;
    ProgressDialog dialog;

    RecyclerView recyclerView;
    CarAdapter carAdapter;

    ArrayList<String> makeNames;
    ArrayList<Integer> makeIds;
    ArrayList<String> modelNames;

    ArrayAdapter<String> spinnerAdapter;

    MyDatabase myDatabase;
    CarsDao carsDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabase = MyDatabase.getDbInstance(this);
        carsDao = myDatabase.carsDao();

        recyclerView = findViewById(R.id.recyclerView);

        makeSpinner = findViewById(R.id.makeSpinner);
        modelSpinner = findViewById(R.id.modelSpinner);
        logOut = findViewById(R.id.logOut);
        addCar = findViewById(R.id.addCar);

        carAdapter = new CarAdapter(this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(carAdapter);

        fetchData();

        //adding car
        addCar.setOnClickListener(this);

        //progress bar
        dialog = new ProgressDialog(this);
        dialog.setMessage("Wait...");

        fetchAllMakes();

        makeSpinner.setOnItemSelectedListener(this);


    }

    private void fetchAllMakes() {
        dialog.show();
        dialog.setCancelable(false);

        Call<MakeData> call = Retro.getInstance().getApi().getAllMakes();
        call.enqueue(new Callback<MakeData>() {
            @Override
            public void onResponse(Call<MakeData> call, Response<MakeData> response) {
                dialog.hide();
                if (response.isSuccessful() && response.body() != null) {

                    List<Results> results = response.body().getResults();

                    makeNames = new ArrayList<>();
                    makeNames.add(0, "Select make");
                    makeIds = new ArrayList<>();
                    makeIds.add(0, 0);

                    for (Results make : results) {
                        Log.d("MakeNames", "onResponse: " + make.getMakeName());
                        Log.d("MakeId", "onResponse: " + make.getMakeId());
                        makeNames.add(make.getMakeName());
                        makeIds.add(make.getMakeId());
                    }

                    //populating the makeSpinner
                    attachAdapter(makeSpinner, makeNames);
                } else
                    showToast("No data found");
            }

            @Override
            public void onFailure(Call<MakeData> call, Throwable t) {
                dialog.hide();
                showToast(t.getMessage());
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        dialog.show();
        dialog.setCancelable(false);

        int selectedMakeId = makeIds.get(position);
        Log.d("ModelId", "onItemSelected: " + selectedMakeId);

        //calling api to get list of models
        Call<ModelData> call = Retro.getInstance().getApi().getAllModels(selectedMakeId);
        call.enqueue(new Callback<ModelData>() {
            @Override
            public void onResponse(Call<ModelData> call, Response<ModelData> response) {
                dialog.hide();
                if (response.isSuccessful() && response.body() != null) {
                    //getting list of models
                    List<IdResult> idResults = response.body().getIdResults();
                    modelNames = new ArrayList<>();
                    modelNames.add(0, "Select model");

                    for (IdResult model : idResults) {
                        modelNames.add(model.getModelName());
                    }
                    Log.d("ModelList", "onResponse: " + modelNames);
                    attachAdapter(modelSpinner, modelNames);
                } else
                    showToast("No data found");
            }

            @Override
            public void onFailure(Call<ModelData> call, Throwable t) {
                dialog.hide();
                showToast(t.getMessage());

            }
        });
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void attachAdapter(Spinner spinner, ArrayList<String> list) {
        spinnerAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }
        };
        spinner.setAdapter(spinnerAdapter);
    }

    @Override
    public void onClick(View v) {
        String text1 = makeSpinner.getSelectedItem().toString();
        String text2 = modelSpinner.getSelectedItem().toString();

        if (!text1.equals("Select make") && !text2.equals("Select model")) {
            Cars cars = new Cars(0,text1, text2);

            Boolean check = carsDao.isExist(text1, text2);
            if (!check) {
                carAdapter.addCar(cars);
                carsDao.insert(cars);
                showToast("Inserted successfully");
            } else showToast("Already Existed");
/*
            //creating my thread for background work
//            ExecutorService service = Executors.newSingleThreadExecutor();
//            service.execute(new Runnable() {
//                @Override
//                public void run() {
//
//                    runOnUiThread(() -> {
//                        dialog.show();
//                        dialog.setCancelable(false);
//                    });
//
//                    MyDatabase db = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "cars_db").build();
//                    CarsDao carsDao = db.carsDao();
//                    int count = carsDao.count(text1, text2);
//                    if(count == 0){
//                        carsDao.insert(new Cars(text1,text2));
//                    }
//+
//
////                    runOnUiThread(() -> {
////                        dialog.hide();
////                        Toast.makeText(getApplicationContext(), "Inserted Successfuly", Toast.LENGTH_SHORT).show();
////                    });
//
//                    carList = carsDao.getAll();
//
//                    runOnUiThread(() -> {
//                        carAdapter = new CarAdapter(getApplicationContext(), carList);
//                        listView.setAdapter(carAdapter);
//                    });
//
//                }
//            });
//        } else
//            Toast.makeText(getApplicationContext(), "Select model and make", Toast.LENGTH_SHORT).show();
//    }
*/
        } else showToast("Please Select model and make");
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

//        startActivityForResult(new Intent(),2,null);
//        registerForActivityResult()
    }

    private void fetchData() {
        List<Cars> carsList = carsDao.getAll();
        for (int i = 0; i < carsList.size(); i++) {
            Cars cars = carsList.get(i);
            carAdapter.addCar(cars);
        }
    }

    @Override
    public void OnDelete(int id, int pos) {
        carsDao.delete(id);
        carAdapter.removeCar(pos);
        showToast("Deleted");
    }

    @Override
    public void updateById(int id, int pos) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra("id", id);
        intent.putExtra("pos", pos);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), IMG_REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == IMG_REQ_CODE) {
                Uri imageUri = data.getData();
                int id = data.getIntExtra("id", -1);
                int pos = data.getIntExtra("pos", -1);
                carsDao.updateById(id, Converters.convertUriToString(imageUri));
                Log.d("image", "onActivityResult: " + Converters.convertUriToString(imageUri));
                fetchImageById(id);
            }
        }
    }

    private void fetchImageById(int id) {
        List<Cars> carsList =carsDao.getImageById(id);
        for (int i = 0; i < carsList.size(); i++) {
            Cars cars = carsList.get(i);
            carAdapter.addCar(cars);
        }
    }
}
