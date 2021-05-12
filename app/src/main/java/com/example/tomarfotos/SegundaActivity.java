package com.example.tomarfotos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

public class SegundaActivity extends AppCompatActivity {
private ImageView fotoLeer;
private SegundaActivityViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(SegundaActivityViewModel.class);
        fotoLeer=findViewById(R.id.fotoLeer);

        vm.getFoto().observe(this, new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                fotoLeer.setImageBitmap(bitmap);
            }
        });
        vm.cargar();
        //Puedo mostrar la foto con el codigo que se encuentra comentado más abajo.

        /*File archivo =new File(getFilesDir(),"foto1.png");
        Glide.with(this)
                .load(archivo)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(fotoLeer);*/



    }
}