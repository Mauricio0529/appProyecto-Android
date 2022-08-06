package com.example.domilaap;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Opcion_Login extends AppCompatActivity {

    private CallbackManager callbackManager;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private LoginButton loginButton;
    private static final String TAG = "Facebook Autentication"; // NO SE PA QUE SIRVE // MOSTRAR MENSAJE

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcion__login);
        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);

        progressBar = findViewById(R.id.progress_bar);

        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);
       loginButton.setReadPermissions("email", "public_profile"); // dar permisos al usuario de la informacion
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken()); // esto permite ir a la panalla del usuario, cuando salgamos de la app y volvemos a entrar nos mandara al usuario ya registrado
                goMenuScreen();
            }
            @Override
            public void onCancel() {
                Log.d(TAG, "onCancel");
            }
            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "onError" + error);
            }
        });

        // **********************************
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    goMenuScreen();
                }
            }
        };

        //*****************************
    } // fin del Oncreate

    // ir a la pantalla del usuario cuando salgamos de la app
    private void handleFacebookAccessToken(AccessToken accessToken){
        progressBar.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.GONE);

        Log.d(TAG, "handleFacebookToken" + accessToken);
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(Opcion_Login.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
                loginButton.setVisibility(View.VISIBLE);
            }
        });
    }
    ////

    private void goMenuScreen(){
        Intent intent = new Intent(this, Menu.class);
        //intent.putExtra("dato1", user.getDisplayName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (AccessToken.getCurrentAccessToken() != null) {
            firebaseAuth.addAuthStateListener(firebaseAuthListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
    }

    public  void  Atras(View view){
        //Toast.makeText(getApplicationContext(), "No se encuentra disponible.", Toast.LENGTH_SHORT).show();
        //onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void  Ventana_Registro(View view){
        Intent intent = new Intent(this, Registro.class);
        //intent.putExtra("dato1", user.getDisplayName());
       // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}

/*  parametros.put("usu_nombre", nombres);
                parametros.put("usu_apellido", apellidos);
                parametros.put("usu_email", email);
                parametros.put("usu_password", pass);*/