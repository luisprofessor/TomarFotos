package com.example.tomarfotos;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;

public class MainViewModel extends ViewModel {

private MutableLiveData<Bitmap> foto;

public LiveData<Bitmap> getFoto(){
    if(foto==null){
        foto=new MutableLiveData<>();
    }
    return foto;
}

public void respuetaDeCamara(int requestCode, int resultCode, @Nullable Intent data,int REQUEST_IMAGE_CAPTURE){
    Log.d("salida",requestCode+"");
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
        //Recupero los datos provenientes de la camara.
        Bundle extras = data.getExtras();
        //Casteo a bitmap lo obtenido de la camara.
        Bitmap imageBitmap = (Bitmap) extras.get("data");

        //Rutina para optimizar la foto,
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        foto.setValue(imageBitmap);


        //Rutina para convertir a un arreglo de byte los datos de la imagen
        byte [] b=baos.toByteArray();
        Log.d("salida",b.length+"");

        //Aquí podría ir la rutina para llamar al servicio que recibe los bytes.
    }
}
}
