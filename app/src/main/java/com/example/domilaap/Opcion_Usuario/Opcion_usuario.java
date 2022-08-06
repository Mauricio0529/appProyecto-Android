package com.example.domilaap.Opcion_Usuario;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.domilaap.MainActivity;
import com.example.domilaap.Menu;
import com.example.domilaap.R;
import com.example.domilaap.Repartidor.Login_repartidor;
import com.example.domilaap.Repartidor.Menu_repartidor.Repartidor_menu;
import com.facebook.AccessToken;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Opcion_usuario extends AppCompatActivity {

    private Button btnUsuario, btnRepartidor;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcion_usuario);
        btnUsuario = findViewById(R.id.btnUsuario);
        btnRepartidor = findViewById(R.id.btnRepartidor);

        InicarAutomaticamente();
        InicarAutomaticamenteRepartidor();
        firebaseAuth = FirebaseAuth.getInstance();    //FACEBOOK
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    goMenuScreen();
                }
            }
        };
        btnUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Opcion_usuario.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnRepartidor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Opcion_usuario.this, Login_repartidor.class);
                startActivity(intent);
            }
        });
    }

    private void InicarAutomaticamente(){ // SIN BASE DE DATOS SE MANEJA CON LAS PREFERENCIAS
        SharedPreferences preferences = getSharedPreferences("preferenciasL", Context.MODE_PRIVATE);
        boolean sesion = preferences.getBoolean("sesion", false);
        if (sesion){
            goMenuScreen();
        }/* else{
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }*/
    }
    ////////

    private void InicarAutomaticamenteRepartidor(){ // SIN BASE DE DATOS SE MANEJA CON LAS PREFERENCIAS
        SharedPreferences preferences = getSharedPreferences("preferenciasLR", Context.MODE_PRIVATE);
        boolean sesion = preferences.getBoolean("sesion", false);
        if (sesion){
            goMenuRepartidorScreen();
        }/* else{
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }*/
    }


    private void goMenuScreen(){
        //ProfileFragment profileFragment = new ProfileFragment();
        Toast.makeText(getApplicationContext(), "Bienvenido!!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Menu.class);
        //intent.putExtra("dato1", user.getDisplayName());
        //intent.putExtra("dato", informacion1);

        //  Bundle bundle = new Bundle();
        //  bundle.getString("dato", informacion1);
        //  profileFragment.setArguments(bundle);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void goMenuRepartidorScreen(){
        Toast.makeText(getApplicationContext(), "Bienvenido!!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Repartidor_menu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override   // FACEBOOK
    protected void onStart() {
        super.onStart();
        if (AccessToken.getCurrentAccessToken() != null) {
            firebaseAuth.addAuthStateListener(firebaseAuthListener);
        }
    }
}
