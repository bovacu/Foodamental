package com.example.bovazque.registro_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.HashMap;
import java.util.Map;
public class Lista_activity extends AppCompatActivity {

    private ListaCompra lista;
    private ScrollView panel_alimentos;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_activity);

        panel_alimentos = (ScrollView) findViewById(R.id.panel_alimentos);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        this.lista = new ListaCompra();
        lista.anadirElemento("Patatas", 2);
        lista.anadirElemento("Arroz", 3);
        System.out.println("LLEGOOOOOOOOOOOOOOOOO");
        mostrarElementos();

    }

    public void mostrarElementos(){
            for(Object key : this.lista.getElementos().keySet()){
                String nombre = (String) key;
                Button button = new Button(this);
                button.setText(nombre);
                linearLayout.addView(button);
            }

    }

    public void Onclick(){

    }




}
