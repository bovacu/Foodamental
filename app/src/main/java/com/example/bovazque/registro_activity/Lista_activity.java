package com.example.bovazque.registro_activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.CheckBox;

public class Lista_activity extends AppCompatActivity implements AnadirAlimentoDialog.AnadirAlimentoDialogListener {

    private static ListaCompra lista;
    private ScrollView panel_alimentos;
    private LinearLayout linearLayout;
    private Button anadir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_activity);

        this.panel_alimentos = (ScrollView) findViewById(R.id.panel_alimentos);
        this.linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        this.anadir = (Button) findViewById(R.id.anadir_button);
        this.anadir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        this.lista = new ListaCompra();
        this.lista.anadirElemento("Patatas", 2);
        this.lista.anadirElemento("Arroz", 3);
        mostrarElementos();

    }

    public void mostrarElementos(){
        this.linearLayout.removeAllViews();
        for(Object key : this.lista.getElementos().keySet()){
            LinearLayout myLinearLayout = new LinearLayout(this);
            myLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            String nombre = (String) key;
            Button button = new Button(this);
            //Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/helveticaneuelight.otf");
            LinearLayout.LayoutParams eqparams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            GradientDrawable border = new GradientDrawable();

            border.setColor(0xFFFFFFFF); //white background
            border.setStroke(1, 0xFF000000); //black border with full opacity
            button.setLayoutParams(eqparams);
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                myLinearLayout.setBackgroundDrawable(border);
            } else {
                myLinearLayout.setBackground(border);
            }

            button.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    ViewGroup parentButton = (ViewGroup) v.getParent();
                    ViewGroup parentLinearLayout = (ViewGroup) parentButton.getParent();
                    Button b = (Button) v;
                    lista.eliminarElemento(b.getText().toString());
                    parentLinearLayout.removeView(parentButton);

                    //parentView.removeView(v);
                    //System.out.println(b.getText().toString());

                    return true;
                }
            });
            button.setText(nombre);
            button.setBackgroundColor(Color.parseColor("#8CDBF2FF"));
            //button.setTypeface(typeface);
            CheckBox checkBox = new CheckBox(this);
            checkBox.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox checkBox1 = (CheckBox) v;
                    ViewGroup parentButton = (ViewGroup) v.getParent();
                    Button button = null;
                    for (int itemPos = 0; itemPos < parentButton.getChildCount(); itemPos++) {
                        View view = parentButton.getChildAt(itemPos);
                        if (view instanceof Button) {
                            button = (Button) view;
                            System.out.println(button.getBackground().toString());
                            if(!checkBox1.isChecked()){
                                button.setBackgroundColor(Color.parseColor("#8CDBF2FF"));
                            }else{

                                button.setBackgroundColor(Color.parseColor("#4DDBF2FF"));
                            }

                        }
                    }
                }
            });
            myLinearLayout.addView(checkBox);
            myLinearLayout.addView(button);
            linearLayout.addView(myLinearLayout);
        }
    }


    public void openDialog(){
        AnadirAlimentoDialog dialog = new AnadirAlimentoDialog();
        dialog.show(getSupportFragmentManager(), "");


    }


    @Override
    public void applyTexts(String nombreAlimento, int cantidad) {
        this.lista.anadirElemento(nombreAlimento, cantidad);
        mostrarElementos();
    }
}