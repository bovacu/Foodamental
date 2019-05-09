package com.example.bovazque.registro_activity;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class RecetasEncontradas_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetas_encontradas_activity);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Recetas");

        final Context context = this;

        final LinearLayout ll = findViewById(R.id.listaRecetas);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String tipoBusqueda = getIntent().getStringExtra("busqueda");

                    if(tipoBusqueda.equals("ingredientes")){
                        List<String> ingredientesABuscar = new ArrayList<>();
                        for(String ingr : (List<String>) getIntent().getSerializableExtra("listaIngredientes")){
                            ingredientesABuscar.add(ingr.toLowerCase().replaceAll("\\s+", ""));
                        }

                        buscarPorIngredientes(ll, ingredientesABuscar, dataSnapshot, context);

                    } else if(tipoBusqueda.equals("categoria")){
                        buscarPorCategoria(ll, getIntent().getStringExtra("categoria").toLowerCase().replaceAll("\\s+", ""), dataSnapshot, context);
                    } else {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        private void buscarPorCategoria(LinearLayout ll, String categoria, DataSnapshot dataSnapshot, Context context){

            categoria = categoria.toLowerCase().replaceAll("\\s+", "");
            for (DataSnapshot receta : dataSnapshot.getChildren()) {
                DataSnapshot cat = receta.child("Categoria");

                String[] categorias = ((String)cat.getValue()).split(",");

                for(String c : categorias){
                    System.out.println(c + " ----> " + categoria);
                    if(c.toLowerCase().replaceAll("\\s+", "").equals(categoria)) {
                        if(!yaEnLosResultados(ll, receta.getKey())){
                            Button b = new Button(context);
                            b.setText(receta.getKey());
                            ll.addView(b);
                            break;
                        }
                    }
                }
            }
        }

        private void buscarPorIngredientes(LinearLayout ll, List<String> ingredientesABuscar, DataSnapshot dataSnapshot, Context context){
            for (DataSnapshot receta : dataSnapshot.getChildren()) {
                DataSnapshot ingredientes = receta.child("Ingredientes");

                List<String> ingredientesReceta = new ArrayList<>();

                for (DataSnapshot ingrediente : ingredientes.getChildren())
                    ingredientesReceta.add(ingrediente.getKey().toLowerCase().replaceAll("\\s+", ""));

                if (ingredientesReceta.containsAll(ingredientesABuscar) ) {
                    if(!yaEnLosResultados(ll, receta.getKey())){
                        Button b = new Button(context);
                        b.setText(receta.getKey());
                        ll.addView(b);
                    }
                }
            }
        }

        public boolean yaEnLosResultados(LinearLayout ll, String receta){
            for(int i = 0; i < ll.getChildCount(); i++){
                if(ll.getChildAt(i) instanceof Button) {
                    if (((Button)ll.getChildAt(i)).getText().equals(receta))
                        return true;
                }
            }
            return false;
        }
    }
