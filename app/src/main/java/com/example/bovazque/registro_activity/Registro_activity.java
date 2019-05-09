package com.example.bovazque.registro_activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registro_activity extends AppCompatActivity implements View.OnClickListener {

    //defining view objects
    private EditText TextEmail;
    private EditText TextName;
    private EditText TextApellido;
    private Spinner TextPaís;
    private EditText TextTelefono;
    private EditText TextPassword;
    private Button btnRegistrar;
    private ProgressDialog progressDialog;


    //Declaramos un objeto firebaseAuth
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
// ...



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_activity);

        mDatabase = FirebaseDatabase.getInstance().getReference("Usuario");
        //inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //Referenciamos los views
        TextEmail = (EditText) findViewById(R.id.editTextEmail);
        TextPassword = (EditText) findViewById(R.id.editText);
        //BD---------------------------------------------------
        TextName =(EditText) findViewById(R.id.editTextName);
        TextApellido =(EditText) findViewById(R.id.editTextLastName);
        TextPaís =(Spinner) findViewById(R.id.spinCountry);
        TextTelefono =(EditText) findViewById(R.id.editTextPhone);
                //-----------------------------------------------------
        btnRegistrar = (Button) findViewById(R.id.register_btn);

        progressDialog = new ProgressDialog(this);

        //attaching listener to button
        btnRegistrar.setOnClickListener(this);
    }

    private void registrarUsuario(){
        //Obtenemos el email y la contraseña desde las cajas de texto
        String email = TextEmail.getText().toString().trim();
        String password  = TextPassword.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe ingresar un email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Falta ingresar la contraseña",Toast.LENGTH_LONG).show();
            return;
        }
        boolean correcto = this.registrarClase(email);
        if(correcto){
            progressDialog.setMessage("Realizando registro en linea...");
            progressDialog.show();

            //creating a new user
            Task<AuthResult>  authResultTask = firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                            if (task.isSuccessful()) {
                              Toast.makeText(Registro_activity.this, "Se ha registrado el usuario con el email: " + TextEmail.getText(), Toast.LENGTH_LONG).show();
                            } else {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                                    Toast.makeText(Registro_activity.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                                    inicioSesion();
                                } else {
                                    Toast.makeText(Registro_activity.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                                }
                            }
                            progressDialog.dismiss();
                        }
                });}else{
            Toast.makeText(this,"Debes rellenar todos los campos", Toast.LENGTH_LONG).show();
        }

    }
    public boolean registrarClase(String email){
        boolean campos = false;
        if(!TextUtils.isEmpty(email)){
            String[] s = email.split("@");
            Alimento a  = new Alimento("aji amarillo", 1);
            mDatabase.child("Usuarios").child(s[0]).child("ListaCompra").child("falsi").setValue("-1");
            campos = true;
        }
        return campos;
    }
    public void onClick(View view) {
        //Invocamos al método:
        registrarUsuario();
    }
    private void inicioSesion(){
        Intent intent = new Intent(this, InicioSesion_activity.class);
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}