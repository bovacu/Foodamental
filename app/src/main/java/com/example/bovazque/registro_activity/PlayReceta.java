package com.example.bovazque.registro_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PlayReceta extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    public ArrayList<String> pasos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.receta_play_activity);
        final int numero = Integer.parseInt(getIntent().getStringExtra("numero"));
        final String nombre = getIntent().getStringExtra("nombreReceta");
        super.onCreate(savedInstanceState);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Recetas");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pasos = new ArrayList<String>();
                DataSnapshot pasosReceta = dataSnapshot.child(getIntent().getStringExtra("nombreReceta")).child("Pasos");
                for (DataSnapshot da : pasosReceta.getChildren()){
                    pasos.add((String) da.getValue());
                }
                TextView tituloText = findViewById(R.id.tituloText);
                TextView pasossReceta = findViewById(R.id.pasosReceta);
                tituloText.setGravity(Gravity.CENTER);
                tituloText.setText(nombre);
                System.out.println("------------------------------------------------------");
                System.out.println(numero);
                System.out.println(pasos);
                System.out.println("------------------------------------------------------");
                pasossReceta.setText(pasos.get(numero));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });



    }

    public void onClickPasosmasmas(View view){
        if(Integer.parseInt(getIntent().getStringExtra("numero"))+1 >= pasos.size()){
            Intent intencion = new Intent(PlayReceta.this, VerReceta_activity.class);
            intencion.putExtra("nombreReceta", getIntent().getStringExtra("nombreReceta"));
            startActivity(intencion);
        }else{
        Intent intencion = new Intent(PlayReceta.this, PlayReceta.class);
        intencion.putExtra("nombreReceta", getIntent().getStringExtra("nombreReceta"));
        intencion.putExtra("numero", String.valueOf(Integer.parseInt(getIntent().getStringExtra("numero"))+1));
        startActivity(intencion);}
    }


}
