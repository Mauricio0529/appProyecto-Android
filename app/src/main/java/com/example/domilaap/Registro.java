package com.example.domilaap;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity{

    private EditText name, txt_cedula, txt_celular;
    private EditText apellido;
    private EditText correo;
    private EditText contraseña;
    Button btn_crear;
    private Button popup_window_button;
    Dialog dialog;
    String cc, nombres, celular;
    String apellidos;
    String email;
    String pass;
    String controlCuelta = "false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        dialog = new Dialog(this);
        txt_cedula = (EditText) findViewById(R.id.txt_cedula);
        name = (EditText) findViewById(R.id.txt_nombre);
        apellido = (EditText) findViewById(R.id.txt_apellido);
        txt_celular = (EditText) findViewById(R.id.txt_celular);
        correo = (EditText) findViewById(R.id.txt_email);
        contraseña = (EditText) findViewById(R.id.txt_contraseña); // 6 editext
        btn_crear = (Button) findViewById(R.id.btn_crearu);
    }
    public  void  Atras(View view){
        //Toast.makeText(getApplicationContext(), "No se encuentra disponible.", Toast.LENGTH_SHORT).show();
        //onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("dato1", user.getDisplayName());
   //     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void Guardar(View view){
        cc = txt_cedula.getText().toString();
        nombres = name.getText().toString();
        apellidos = apellido.getText().toString();
        celular = txt_celular.getText().toString();
        email = correo.getText().toString();
        pass = contraseña.getText().toString();

//
        if (!cc.isEmpty() && !nombres.isEmpty() && !apellidos.isEmpty() && !celular.isEmpty() && !email.isEmpty() && !pass.isEmpty()) {
        //CrearUsuario("http://192.168.1.11/domilapp/formulario.php");
        CrearUsuario("https://domilapp.000webhostapp.com/formulario.php");
        } else {
            Ventana();
           // Toast.makeText(Registro.this, "No se permiten campos vacios.", Toast.LENGTH_SHORT).show();
        }


   //     if (!nombres.equals("") && !apellidos.equals("") && !email.equals("") && !pass.equals("")) {
     //       Registro_usuario();

   //     }

      /*  if (!nombres.equals("") && !apellidos.equals("") && !email.equals("") && !pass.equals("")){
            Registro_usuario();
        } else if (!nombres.equals("") && apellidos.equals("") && email.equals("") && pass.equals("")){
            apellido.setError("Debes de ingressar datos en los campos de texto vacios.");
            correo.setError("Debes de ingressar datos en los campos de texto vacios.");
            contraseña.setError("Digita una contraseña.");
            //Toast.makeText(this, "Debes de ingressar datos en los campos de texto vacios.", Toast.LENGTH_LONG).show();

            apellido.requestFocus();
            InputMethodManager inm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            assert inm != null; // NO SE PARA QUE SIRVE, LO COLOQUE PARA QUE NO ME DIERA EL ERROR AMARILLO
            inm.showSoftInput(apellido, InputMethodManager.SHOW_IMPLICIT);

        } else if (!nombres.equals("") && !apellidos.equals("") && email.equals("") && pass.equals("")){
            correo.setError("Debes de ingressar datos en los campos de texto vacios.");
            contraseña.setError("Digita una contraseña.");
            //Toast.makeText(this, "Debes de ingressar datos en los campos de texto vacios.", Toast.LENGTH_LONG).show();

            correo.requestFocus();
            InputMethodManager inm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            assert inm != null;
            inm.showSoftInput(correo, InputMethodManager.SHOW_IMPLICIT);

        } else if (!nombres.equals("") && !apellidos.equals("") && !email.equals("") && pass.equals("")){
            contraseña.setError("Digita una contraseña.");
            //Toast.makeText(this, "Digita una contraseña.", Toast.LENGTH_LONG).show();

            contraseña.requestFocus();
            InputMethodManager inm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            assert inm != null;
            inm.showSoftInput(contraseña, InputMethodManager.SHOW_IMPLICIT);

        } else if (nombres.equals("") && !apellidos.equals("") && !email.equals("") && !pass.equals("")){
            name.setError("Ingresa tu nombre.");
            //Toast.makeText(this, "Ingresa tu nombre.", Toast.LENGTH_LONG).show();

            name.requestFocus();
            InputMethodManager inm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            assert inm != null;
            inm.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT);

        } else if (!nombres.equals("") && apellidos.equals("") && !email.equals("") && !pass.equals("")){
            apellido.setError("Debes de ingressar datos en los campos de texto vacios.");
            //Toast.makeText(this, "Ingresa tu apellido.", Toast.LENGTH_LONG).show();

            apellido.requestFocus();
            InputMethodManager inm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            assert inm != null;
            inm.showSoftInput(apellido, InputMethodManager.SHOW_IMPLICIT);

        } else if (!nombres.equals("") && !apellidos.equals("") && email.equals("") && !pass.equals("")){
            correo.setError("Debes de ingressar datos en los campos de texto vacios.");
            //Toast.makeText(this, "Ingresa un correo.", Toast.LENGTH_LONG).show();

            correo.requestFocus();
            InputMethodManager inm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            assert inm != null;
            inm.showSoftInput(correo, InputMethodManager.SHOW_IMPLICIT);

        } else if (nombres.equals("") && apellidos.equals("") && !email.equals("") && !pass.equals("")){
            name.setError("Debes de ingressar datos en los campos de texto vacios.");
            apellido.setError("Debes de ingressar datos en los campos de texto vacios.");
            //Toast.makeText(this, "Debes de ingressar datos en los campos de texto vacios.", Toast.LENGTH_LONG).show();

            name.requestFocus();
            InputMethodManager inm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            assert inm != null;
            inm.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT);

        } else if (nombres.equals("") && apellidos.equals("") && email.equals("") && !pass.equals("")){
            name.setError("Debes de ingressar datos en los campos de texto vacios.");
            apellido.setError("Debes de ingressar datos en los campos de texto vacios.");
            correo.setError("Debes de ingressar datos en los campos de texto vacios.");
            //Toast.makeText(this, "Debes de ingressar datos en los campos de texto vacios.", Toast.LENGTH_LONG).show();

            name.requestFocus();
            InputMethodManager inm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            assert inm != null;
            inm.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT);

        } else if (!nombres.equals("") && apellidos.equals("") && email.equals("") && !pass.equals("")){
            apellido.setError("Debes de ingressar datos en los campos de texto vacios.");
            correo.setError("Debes de ingressar datos en los campos de texto vacios.");
            //Toast.makeText(this, "Debes de ingressar datos en los campos de texto vacios.", Toast.LENGTH_LONG).show();

            apellido.requestFocus();
            InputMethodManager inm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            assert inm != null;
            inm.showSoftInput(apellido, InputMethodManager.SHOW_IMPLICIT);

        } else if (!nombres.equals("") && apellidos.equals("") && !email.equals("") && pass.equals("")){
            apellido.setError("Debes de ingressar datos en los campos de texto vacios.");
            contraseña.setError("Debes de ingressar datos en los campos de texto vacios.");
            //Toast.makeText(this, "Debes de ingressar datos en los campos de texto vacios.", Toast.LENGTH_LONG).show();

            apellido.requestFocus();
            InputMethodManager inm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            assert inm != null;
            inm.showSoftInput(apellido, InputMethodManager.SHOW_IMPLICIT);

        } else if (nombres.equals("") && !apellidos.equals("") && !email.equals("") && pass.equals("")){
            name.setError("Debes de ingressar datos en los campos de texto vacios.");
            contraseña.setError("Debes de ingressar datos en los campos de texto vacios.");
            //Toast.makeText(this, "Debes de ingressar datos en los campos de texto vacios.", Toast.LENGTH_LONG).show();

            name.requestFocus();
            InputMethodManager inm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            assert inm != null;
            inm.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT);

        } else if (nombres.equals("") && !apellidos.equals("") && email.equals("") && pass.equals("")){
            name.setError("Debes de ingressar datos en los campos de texto vacios.");
            correo.setError("Debes de ingressar datos en los campos de texto vacios.");
            contraseña.setError("Debes de ingressar datos en los campos de texto vacios.");
            //Toast.makeText(this, "Debes de ingressar datos en los campos de texto vacios.", Toast.LENGTH_LONG).show();

            name.requestFocus();
            InputMethodManager inm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            assert inm != null;
            inm.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT);

        } else if (nombres.equals("") && apellidos.equals("") && !email.equals("") && pass.equals("")){
            name.setError("Debes de ingressar datos en los campos de texto vacios.");
            apellido.setError("Debes de ingressar datos en los campos de texto vacios.");
            contraseña.setError("Debes de ingressar datos en los campos de texto vacios.");
            //Toast.makeText(this, "Debes de ingressar datos en los campos de texto vacios.", Toast.LENGTH_LONG).show();

            name.requestFocus();
            InputMethodManager inm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            assert inm != null;
            inm.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT);

        } else if (nombres.equals("") && !apellidos.equals("") && email.equals("") && !pass.equals("")){
            name.setError("Debes de ingressar datos en los campos de texto vacios.");
            correo.setError("Debes de ingressar datos en los campos de texto vacios.");
            //Toast.makeText(this, "Debes de ingressar datos en los campos de texto vacios.", Toast.LENGTH_LONG).show();

            name.requestFocus();
            InputMethodManager inm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            assert inm != null;
            inm.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT);

        } else if (nombres.equals("") && apellidos.equals("") && email.equals("") && pass.equals("")){
            name.setError("Debes de ingressar datos en los campos de texto vacios.");
            apellido.setError("Debes de ingressar datos en los campos de texto vacios.");
            correo.setError("Debes de ingressar datos en los campos de texto vacios.");
            contraseña.setError("Debes de ingressar datos en los campos de texto vacios.");
            //Toast.makeText(getApplicationContext(), "No debes dejar espacios en blanco.", Toast.LENGTH_LONG).show();
            //Toast.makeText(this, "Ingresa tu nombre.", Toast.LENGTH_SHORT).show();
            //name.requestFocus();
            //InputMethodManager inm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            //inm.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT);
        } */
    }
    private void Ventana(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Registro.this);
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

    private void CrearUsuario(String URL){
    /*    nombres = name.getText().toString();
        apellidos = apellido.getText().toString();
        email = correo.getText().toString();
        pass = contraseña.getText().toString();*/

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String ok = jsonObject.getString("status"); // mensaje del PHP
                    String msg = jsonObject.getString("msg");
             //       String hased = jsonObject.getString("hased");
                    if (ok.equals("Ok")){ /// se compara el Ok que esta en el PHP
                        /// SE REGISTRO CORRECTAMENTE
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                        //Toast.makeText(getApplicationContext(), hased, Toast.LENGTH_LONG).show();
                     //   System.out.println(hased);
                    } else {
                        // HAY UN ERROR CON EL REGISTRO
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
                parametros.put("cedula", txt_cedula.getText().toString());
                parametros.put("nombre", name.getText().toString());
                parametros.put("apellido", apellido.getText().toString());
                parametros.put("celular", txt_celular.getText().toString());
                parametros.put("email", correo.getText().toString());
                parametros.put("pass", contraseña.getText().toString());
                parametros.put("cuentaSesion", controlCuelta);

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

    private void MsjCuentaCreada(){
        Toast.makeText(this, "Cuenta Creada", Toast.LENGTH_LONG).show();
        Intent sig = new Intent(this, MainActivity.class);
     //   sig.putExtra("dato", email);
     //   sig.putExtra("dato2", pass);
        startActivity(sig);
        name.setText("");
        apellido.setText("");
        correo.setText("");
        contraseña.setText("");
    }
}