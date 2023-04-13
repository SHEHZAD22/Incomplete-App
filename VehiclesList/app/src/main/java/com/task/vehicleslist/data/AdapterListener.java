package com.task.vehicleslist.data;

import com.task.vehicleslist.model.Data;

public interface AdapterListener {
    void OnDelete(int id, int pos);

//    void onItemClick(Data data);

    void updateById(int id, int pos);
}
