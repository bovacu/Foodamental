package com.example.bovazque.registro_activity;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MotorBusqueda {

    public static List<String> nombresRecetasPorAlimentos(final String alimento){
        final List<String> recetasValidas = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Recetas");
        final CountDownLatch done = new CountDownLatch(1);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot receta : dataSnapshot.getChildren()) {
                    DataSnapshot ingredientes = receta.child("Ingredientes");

                    for (DataSnapshot ingrediente : ingredientes.getChildren()) {
                        System.out.println("ingrediente: " + ingrediente.getKey().toLowerCase().replaceAll("\\s+", ""));
                        if (alimento.toLowerCase().replaceAll("\\s+", "").equals(ingrediente.getKey().toLowerCase().replaceAll("\\s+", ""))) {
                            recetasValidas.add(receta.getKey());
                            break;
                        }
                    }
                }

                done.countDown();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        try {
            done.await(); //it will wait till the response is received from firebase.
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        return recetasValidas;
    }
}
