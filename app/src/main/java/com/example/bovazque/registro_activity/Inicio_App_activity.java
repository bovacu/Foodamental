package com.example.bovazque.registro_activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Inicio_App_activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_aplicacion_activity);

        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                System.out.println("--------------------------------------------------------------------------------------");
            }

            public void onFinish() {
                Intent intent = new Intent(Inicio_App_activity.this, InicioSesion_activity.class);
                //intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);

            }
        }.start();
    }
}
