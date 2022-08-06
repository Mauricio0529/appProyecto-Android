package com.example.domilaap.Repartidor;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.domilaap.R;

import java.util.HashMap;
import java.util.Map;

public class Registro_repartidor extends AppCompatActivity {
    private ImageButton btn_back;
    private EditText name;
    private EditText apellido;
    private EditText correo;
    private EditText contraseña;
    private EditText txt_contraseña2, txt_telefono;
    private Button popup_window_button;
    Dialog dialog;

    private String cedula, nombre, apellidos, email, pass, telefono;

    Button btn_crear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_repartidor);
        btn_back = findViewById(R.id.btn_back);
        dialog = new Dialog(this);
        name = (EditText) findViewById(R.id.txt_nombre);   // CEDULA
        apellido = (EditText) findViewById(R.id.txt_apellido);  // NOMBRE
        correo = (EditText) findViewById(R.id.txt_email); //APELLIDO
        contraseña = (EditText) findViewById(R.id.txt_contraseña); ///  EMAIL

        txt_contraseña2 = findViewById(R.id.txt_contraseña2); /// CONTRASEÑA
        txt_telefono = findViewById(R.id.txt_telefono);  // TELEFONO

        btn_crear = (Button) findViewById(R.id.btn_crearu);


        btn_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cedula = name.getText().toString();
                nombre = apellido.getText().toString();
                apellidos =  correo.getText().toString();
                email = contraseña.getText().toString();
                pass = txt_contraseña2.getText().toString();
                telefono = txt_telefono.getText().toString();
                CrearUsuario("https://domilapp.000webhostapp.com/dformulario.php");
                //CrearUsuario("http://192.168.1.11/domilapp/domiciliarios/dformulario.php");
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "No se encuentra disponible.", Toast.LENGTH_SHORT).show();
                //onBackPressed();
                Intent intent = new Intent(Registro_repartidor.this, Login_repartidor.class);
                //intent.putExtra("dato1", user.getDisplayName());
                //     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void CrearUsuario(String URL){
    /*    nombres = name.getText().toString();
        apellidos = apellido.getText().toString();
        email = correo.getText().toString();
        pass = contraseña.getText().toString();*/

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GuardarPreferenciasRepartidor();
                Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ventanaDialog();
                //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("cedula", cedula);
                parametros.put("renombre", nombre);
                parametros.put("reapellido", apellidos);
                parametros.put("reemail", email);
                parametros.put("repass", pass);
                parametros.put("retelefono", telefono);

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
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


    private void GuardarPreferenciasRepartidor(){ //SHAREDPREFERENCIAS SE UTILIZA ESTO SIN MANEJO DE LA BASE DE DATOS
        SharedPreferences preferences = getSharedPreferences("preferenciasRR", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("cedula", cedula);
        editor.putString("renombre", nombre);
        editor.putString("reapellido", apellidos);
        editor.putString("reemail", email);
        editor.putString("repass", pass);
        editor.putString("retelefono", telefono);
        editor.putBoolean("sesion", true);
        editor.commit();
    }
}
