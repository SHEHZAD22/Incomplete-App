package com.task.vehicleslist.model;

import android.graphics.Bitmap;
import android.net.Uri;

public class Data {
    Uri uri;
    Bitmap bitmap;

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
