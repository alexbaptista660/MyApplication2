package com.example.myapplication;
import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private ImageView imagen1;
    private EditText et1;

    int permiso;
    int permiso2;
    int permiso3;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagen1=(ImageView)findViewById(R.id.imageView);
        et1=(EditText)findViewById(R.id.editText);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(
                    new String[]{
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    },43
            );
        }

        permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        permiso2 = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permiso3 = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA);

        String [] permisos = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permisos,765757);
        }
        Button b1 = (Button)findViewById(R.id.button);
        Button b2 = (Button)findViewById(R.id.button2);
        Button b3 = (Button)findViewById(R.id.button3);
        bordecolorboton(b1, Color.parseColor(getResources().getString(R.string.color1)),10);
        bordecolorboton(b2,Color.parseColor(getResources().getString(R.string.color1)),10);
        bordecolorboton(b3,Color.parseColor(getResources().getString(R.string.color1)),10);

    }
    public void tomarFoto(View v) {
        Intent intento1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File foto = new File(getExternalFilesDir(null), et1.getText().toString()+".jpg");
        Uri imageUri = FileProvider.getUriForFile(
                MainActivity.this,
                "com.example.camera.provider", //(use your app signature + ".provider" )
                foto);
        intento1.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivity(intento1);
    }

    public void recuperarFoto(View v) {
        Bitmap bitmap1 = BitmapFactory.decodeFile(getExternalFilesDir(null)+"/"+et1.getText().toString()+".jpg");
        imagen1.setImageBitmap(bitmap1);
    }

    public void ver(View v) {
        Intent intento1=new Intent(this,Actividad2.class);
        startActivity(intento1);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void bordecolorboton(Button b, int color, int radio){
        if (b!=null){
            GradientDrawable gd = new GradientDrawable();
            if (color!=0){
                gd.setColor(color);
            }
            gd.setCornerRadius(radio);
            gd.setStroke(10, 0xFF000000);
            b.setBackground(gd);
        }
    }
}
