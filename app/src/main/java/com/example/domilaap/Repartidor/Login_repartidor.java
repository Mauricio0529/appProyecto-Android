package com.example.domilaap.Repartidor;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.domilaap.R;
import com.example.domilaap.Repartidor.Menu_repartidor.Repartidor_menu;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login_repartidor extends AppCompatActivity {
    private Button btnLogin, btnRegistro;
    private EditText email, pass;
    String correo; // ALMACENA LO QUE ESCRIBIMOS EN EL TEXTFIELD
    String password;
    String cedula, name, apellido, retelefono;
    private Button popup_window_button;
    Dialog dialog;
    private ProgressBar progress_bar; // nuevo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_repartidor);
        progress_bar = findViewById(R.id.progress_bar);
        dialog = new Dialog(this);

        btnLogin = findViewById(R.id.button);
        btnRegistro = findViewById(R.id.button3);
        email = (EditText) findViewById(R.id.txt_email);
        pass = (EditText) findViewById(R.id.txt_contraseña);

        progress_bar.setVisibility(View.VISIBLE);
        verificarAccesoInternet();
        InicarAutomaticamente();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // goMenuScreen();
                correo = email.getText().toString(); // ALMACENA LO QUE ESCRIBIMOS EN EL TEXTFIELD
                password = pass.getText().toString();
                if (!correo.isEmpty() && !password.isEmpty()){
                    progress_bar.setVisibility(View.VISIBLE);
                    ValidacionUser("https://domilapp.000webhostapp.com/valuedomiciliario.php");
                    //ValidacionUser("http://192.168.1.11/domilapp/domiciliarios/valuedomiciliario.php");
                } else {
                    Ventana();
                    //Toast.makeText(Login_repartidor.this, "No se permiten campos vacios.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_repartidor.this, Registro_repartidor.class);
                startActivity(intent);
            }
        });

    }

    private void ValidacionUser(String URL){   // BASE DE DATOS
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success){
                        cedula = jsonObject.getString("cedula");
                        name = jsonObject.getString("renombre");
                        apellido = jsonObject.getString("reapellido");
                        retelefono = jsonObject.getString("retelefono");
                        GuardarPreferencias();
                        progress_bar.setVisibility(View.GONE);
                        Intent intent = new Intent(Login_repartidor.this, Repartidor_menu.class);
                  //      intent.putExtra("nombre", name);
                  //      intent.putExtra("apellido", apellido);
                  //      intent.putExtra("email", correo);
                  //      intent.putExtra("pass", password);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    } else  {
                        progress_bar.setVisibility(View.GONE);
                        Toast.makeText(Login_repartidor.this, "Hay un error", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            /*    if (!response.isEmpty()){ // AQUI VA TODAS LAS CONDICIONES PARA LA VALIDACION DE DATOS.
                    GuardarPreferencias();
                    goMenuScreen();
                } else {
                    Toast.makeText(MainActivity.this, "Usuario o Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }*/
            }
        },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ventanaDialog();
              //  Toast.makeText(Login_repartidor.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("reemail", correo); //ingresamos los valores a enviar   // 	usu_email
                parametros.put("repass", password); // usu_password
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void verificarAccesoInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // Si hay conexión a Internet en este momento
            progress_bar.setVisibility(View.GONE);
            //  Toast.makeText(MainActivity.this, "Si hay conexión a Internet en este momento", Toast.LENGTH_SHORT).show();
        } else {
            // No hay conexión a Internet en este momento
            // Ventana();
            ventanaDialog();
            //Toast.makeText(MainActivity.this, "No hay conexión a Internet en este momento", Toast.LENGTH_SHORT).show();
        }
    }

    private void ventanaDialog() { // este es la nueva ventana (ES LA ROJA DE INTERNET)
        dialog.setContentView(R.layout.pop_internet); // se muestra todos los elementos de la ventana de INTERNET

        popup_window_button = (Button) dialog.findViewById(R.id.popup_window_button);
        //  popup_window_title = (TextView) dialog.findViewById(R.id.popup_window_title);
        //  popup_window_text = (TextView) dialog.findViewById(R.id.popup_window_text);

        popup_window_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //      dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void Ventana(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Login_repartidor.this);
        alertDialog.setMessage("Por favor, verifique que los campos no estén vacios")
                .setCancelable(true)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog titulo = alertDialog.create();
        titulo.setTitle("Campos vacios");
        titulo.show();
    }

    private void InicarAutomaticamente(){ // SIN BASE DE DATOS SE MANEJA CON LAS PREFERENCIAS
        SharedPreferences preferences = getSharedPreferences("preferenciasLR", Context.MODE_PRIVATE);
        boolean sesion = preferences.getBoolean("sesion", false);
        if (sesion){
            goMenuScreen();
        }/* else{
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }*/
    }
    private void GuardarPreferencias(){ //SHAREDPREFERENCIAS SE UTILIZA ESTO SIN MANEJO DE LA BASE DE DATOS
        SharedPreferences preferences = getSharedPreferences("preferenciasLR", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("cedula", cedula);
        editor.putString("renombre", name);
        editor.putString("reapellido", apellido);
        editor.putString("reemail", correo);
        editor.putString("repass", password);
        editor.putString("retelefono", retelefono);

        editor.putBoolean("sesion", true);
        editor.commit();
    }

    private void goMenuScreen() {
        Toast.makeText(getApplicationContext(), "Bienvenido!!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Repartidor_menu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}