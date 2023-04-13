package com.task.vehicleslist.data;


import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.task.vehicleslist.R;
import com.task.vehicleslist.db.Cars;
import com.task.vehicleslist.model.Data;
import com.task.vehicleslist.supportclass.Converters;

import java.util.ArrayList;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder> {

    List<Cars> carsList;
    Context context;
    AdapterListener adapterListener;

    public CarAdapter(Context context, AdapterListener adapterListener) {
        this.context = context;
        carsList = new ArrayList<>();
        this.adapterListener = adapterListener;
    }

    public void addCar(Cars cars) {
        carsList.add(cars);
        notifyDataSetChanged();
    }

    public void removeCar(int position) {
        carsList.remove(position);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public CarAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recyclerview_items, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.MyViewHolder holder, int position) {
        Cars model = carsList.get(position);

        holder.carMake.setText(model.getMake());
        holder.carModel.setText(model.getModel());

        holder.deleteCar.setOnClickListener(v -> {
            adapterListener.OnDelete(model.getId(), position);
        });

        holder.addCarImage.setOnClickListener( v -> {
            adapterListener.updateById(model.getId(), position);
            Log.d("image", "onBindViewHolder: " + model.getImageUri());
            if (model.getImageUri() != null) holder.carImage.setImageURI(Converters.convertStringToUri(model.getImageUri()));
        });
/*
//        holder.addCarImage.setOnClickListener(v -> {
//            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//            builder.setTitle("Choose Image")
//                    .setItems(new CharSequence[]{"Gallery", "Camera"}, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            if (which == 0) {
//                                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                                intent.setType("image/*");
//                                launcher.launch(intent);
//                            }
//                            else if(which == 1){
//                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                                launcher.launch(intent);
//                            }
//                        }
//                    });
//            builder.show();
//        });

//        ActivityResultCallback<ActivityResult> callback = result -> {
//            if (result.getResultCode() == Activity.RESULT_OK){
//                Intent data = result.getData();
//                if (data != null && data.getData() != null){
//                    Uri uri = data.getData();
//                    holder.carImage.setImageURI(uri);
//                } else if(data != null && data.getExtras() != null){
//                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//                    holder.carImage.setImageBitmap(bitmap);
//                }
//            }
//        };
        */

    }

    @Override
    public int getItemCount() {
        if (carsList != null) return carsList.size();
        else return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView carImage;
        TextView carMake, carModel;
        Button addCarImage, deleteCar;

        public MyViewHolder(@NonNull View view) {
            super(view);

            carMake = view.findViewById(R.id.carMakeText);
            carModel = view.findViewById(R.id.carModelText);
//            onImageText = view.findViewById(R.id.onImageText);
            addCarImage = view.findViewById(R.id.addCarImage);
            deleteCar = view.findViewById(R.id.deleteCar);
            carImage = view.findViewById(R.id.carImage);
        }

    }


}
