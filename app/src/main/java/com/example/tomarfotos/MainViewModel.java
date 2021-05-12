package com.example.tomarfotos;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class MainViewModel extends AndroidViewModel {

private MutableLiveData<Bitmap> foto;
private Context context;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.context=application.getApplicationContext();

    }

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


        //Aquí podría ir la rutina para llamar al servicio que recibe los bytes.
        File archivo =new File(context.getFilesDir(),"foto1.png");
        if(archivo.exists()){
            archivo.delete();
        }
        try {
            FileOutputStream fo=new FileOutputStream(archivo);
            BufferedOutputStream bo=new BufferedOutputStream(fo);
            bo.write(b);
            bo.flush();
            bo.close();
            Intent segunda=new Intent(context,SegundaActivity.class);
            segunda.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(segunda);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
}
