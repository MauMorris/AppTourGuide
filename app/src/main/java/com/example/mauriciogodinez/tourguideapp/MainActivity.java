package com.example.mauriciogodinez.tourguideapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final int NEXT_LAYOUT_DELAY_MILLIS = 1500;
    private static final String TAG = "map: ";
    private Handler mShowHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Valores que obtenemos al preguntar si tenemos los permisos para ejecutar la App
        int readGps = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int readCLoc = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        //Aqui preguntamos si tenemos los permisos de ejecución, en caso contrario pregunta en un cuadro de Dialogo
        try{
            //Hasta no obtener los permisos no podemos ejecutar la Activity
            while(readGps != PackageManager.PERMISSION_GRANTED
                    && readCLoc != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

                readGps = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
                readCLoc = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

            }
        }catch (Exception e) {
            Log.v(TAG, e.getMessage());
        }

        mShowHandler = new Handler();
    }

    @Override
    protected void onStart() {
        super.onStart();
        delayedShow(NEXT_LAYOUT_DELAY_MILLIS);
    }

    private void delayedShow(int delay){
        mShowHandler.removeCallbacks(mShowRunnable);
        mShowHandler.postDelayed(mShowRunnable, delay);
    }

    private final Runnable mShowRunnable = new Runnable() {
        @Override
        public void run() {
            Intent next = new Intent(getApplicationContext(), TourActivity.class);
            startActivity(next);
            finish();
        }
    };
}
