package com.example.bovazque.registro_activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecetasPorIngredientes extends AppCompatActivity {

    private List<String> ingredientesEnLaLista;
    private ArrayAdapter<String> adapter;
    private ListView lista_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetas_por_ingredientes);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        this.lista_lv = findViewById(R.id.lista_lv);
        this.ingredientesEnLaLista = new ArrayList<>();
        this.adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, this.ingredientesEnLaLista);
        this.lista_lv.setAdapter(adapter);

        findViewById(R.id.anadir_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadirIngredienteALaLista();
            }
        });

        final Context context = this;
        lista_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, adapter.getItem(position) + " eliminado del filtro",Toast.LENGTH_LONG).show();
                adapter.remove(adapter.getItem(position));
                adapter.notifyDataSetChanged();
            }
        });

    }

    public void goToRecetasEncontradas(View view) {
        Intent intencion = new Intent(getApplication(), RecetasEncontradas_activity.class);
        intencion.putExtra("listaIngredientes", (Serializable) this.ingredientesEnLaLista);
        intencion.putExtra("busqueda", "ingredientes");
        startActivity(intencion);
    }

    public void anadirIngredienteALaLista() {
        EditText ingredientes_tb = findViewById(R.id.ingredientes_tb);
        String ingrediente = ingredientes_tb.getText().toString();

        if(!this.ingredientesEnLaLista.contains(ingrediente) && ingrediente.trim().length() > 0) {
            this.ingredientesEnLaLista.add(ingrediente);
            this.adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "El alimento no puede estar vacio", Toast.LENGTH_SHORT).show();
        }
    }
}
