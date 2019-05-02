package com.example.bovazque.registro_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Principal_activity extends AppCompatActivity  implements View.OnClickListener{

    private Button listabtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_activity);
        listabtn = (Button) findViewById(R.id.listabtn);
        listabtn.setOnClickListener(this);
    }


    public void onClick(View view) {
        Intent intencion = new Intent(this, Lista_activity.class);
        startActivity(intencion);
    }
}
