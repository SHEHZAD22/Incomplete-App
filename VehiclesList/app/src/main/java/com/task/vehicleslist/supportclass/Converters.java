package com.task.vehicleslist.supportclass;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;

public class Converters {

    @TypeConverter
    public static String convertBitmapToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray,Base64.DEFAULT);
    }

    @TypeConverter
    public static Bitmap convertStringToBitmap(String encode){
        byte[] decode = Base64.decode(encode,Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decode,0, decode.length);
    }

    @TypeConverter
    public static String convertUriToString(Uri uri){
        return uri.toString();
    }

    @TypeConverter
    public static Uri convertStringToUri(String string){
        return Uri.parse(string);
    }
}
