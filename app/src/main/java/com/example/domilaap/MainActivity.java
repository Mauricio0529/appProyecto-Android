package com.example.domilaap;
// android:background="@drawable/boton_atras"
// token github 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
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {

    private EditText  email, pass;
  //  private TextInputEditText email;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
   // String informacion1;
  //  String informacion2;  #E2237D88

    String correo; // ALMACENA LO QUE ESCRIBIMOS EN EL TEXTFIELD
    String password;
    String idUsuario, cedula, celular;
    String name;
    String apellido;
   // String controlCuenta;
    Dialog dialog;

    private ProgressBar progress_bar;
    android.app.AlertDialog alertDialog;

    private Button popup_window_button;
    private TextView popup_window_title, popup_window_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.txt_email);
        pass = findViewById(R.id.txt_contraseña);
        progress_bar = findViewById(R.id.progress_bar);

        dialog = new Dialog(this);

        alertDialog = new SpotsDialog.Builder()
                .setContext(MainActivity.this)
                .setMessage("Espere un momento")
                .setCancelable(false)
                .build();

    //    RecuperarPreferencias();
        // progress_bar.setVisibility(View.VISIBLE);
        verificarAccesoInternet();
        InicarAutomaticamente();
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
    }

    public void Siguiente(View view){ // METODO DE LOGEARSE SIN FACEBOOK
       // verificarAccesoInternet();
        correo = email.getText().toString(); // ALMACENA LO QUE ESCRIBIMOS EN EL TEXTFIELD
        password = pass.getText().toString();
        if (!correo.isEmpty() && !password.isEmpty()){
            // progress_bar.setVisibility(View.VISIBLE);
            alertDialog.show();
            ValidacionUser("https://domilapp.000webhostapp.com/validar_usuario.php");
            //  ValidacionUser("http://192.168.1.11/domilapp/validar_usuario.php");
        } else {
            Ventana();
        }
    }

    private void ValidacionUser(String URL){   // BASE DE DATOS
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    boolean sessionCuenta = jsonObject.getBoolean("sessionCuenta");

                    if (success == true) { // si las contraseñas son correctas

                        if (sessionCuenta == false){ // ya ha iniciado sesion en otro dispositivo
                            // progress_bar.setVisibility(View.GONE);
                            alertDialog.dismiss();

                            Toast.makeText(MainActivity.this, "Ya has iniciado sesion en otro dispositivo", Toast.LENGTH_LONG).show();
                        } else { // no ha iniciado sesion en un dispositivo
                            idUsuario = jsonObject.getString("id_usuario"); // ID DEL USUARIO
                            cedula = jsonObject.getString("cedula"); // cedula del usuario
                            name = jsonObject.getString("nombre"); // nombre del usuario
                            apellido = jsonObject.getString("apellido"); // apellido del usuario
                            celular = jsonObject.getString("celular"); // numero de celular

                            GuardarPreferencias(); // guardamos los datos para mostrar en una activity

                            // progress_bar.setVisibility(View.GONE);
                            alertDialog.dismiss();

                            // Toast.makeText(MainActivity.this, "Ya tienes tu cuenta" + controlCuenta, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, Menu.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }

                    }  else {
                       // progress_bar.setVisibility(View.GONE);
                        alertDialog.dismiss();

                       //Toast.makeText(MainActivity.this, "C y P son incorrectos", Toast.LENGTH_LONG).show();
                        ventanaCamposIncorrectos();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                
            }
        },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // aqui es donde me muestra que el servidor esta apagado
                //Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                //Toast.makeText(MainActivity.this, "El servidor esta en mantenimiento", Toast.LENGTH_LONG).show();
              //  verificarAccesoInternet();
                ventanaDialog();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("email", correo); //ingresamos los valores a enviar   // 	usu_email
                parametros.put("pass", password); // usu_password
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void GuardarPreferencias(){ //SHAREDPREFERENCIAS SE UTILIZA ESTO SIN MANEJO DE LA BASE DE DATOS
        String arreglo[] = {"Hola", "Como esta"};

        SharedPreferences preferences = getSharedPreferences("preferenciasL", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("id_Usuario", idUsuario); // EL ID DEL USUARIO
        editor.putString("usu_cedula", cedula);   // 	CEDULA DEL USUARIO
        editor.putString("usu_nombre", name);   // 	NOMBRE DEL USUARIO
        editor.putString("usu_apellido", apellido);  // APELLIDO DEL USUARIO
        editor.putString("usu_celular", celular);   // 	NUMERO DE TELEFONO DEL USUARIO
        editor.putString("usu_email", correo);   // 	usu_email
        editor.putString("usu_password", password);  // usu_password
        editor.putBoolean("sesion", true);
        editor.commit();
    }

//    private void RecuperarPreferencias(){ // SIN BASE DE DATOS SE MANEJA CON LAS PREFERENCIAS
  //      SharedPreferences preferences = getSharedPreferences("preferenciasL", Context.MODE_PRIVATE);
  //      email.setText(preferences.getString("correo", "micorreo@gamil.com"));
     //   pass.setText(preferences.getString("password", "123456789"));
   // }

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

    private void goMenuScreen(){
        //ProfileFragment profileFragment = new ProfileFragment();
        Toast.makeText(getApplicationContext(), "Menu principal", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Menu.class);
        //intent.putExtra("dato1", user.getDisplayName());
        //intent.putExtra("dato", informacion1);

      //  Bundle bundle = new Bundle();
      //  bundle.getString("dato", informacion1);
      //  profileFragment.setArguments(bundle);

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

    /*public static void changeEstadoCambiar(Context c, boolean b){
        SharedPreferences preferences = c.getSharedPreferences()
    }*/

    public void Registro(View view){ // IR AL REGISTRO
        Intent sig = new Intent(this, Registro.class);
        startActivity(sig);
    }

    private void verificarAccesoInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // progress_bar.setVisibility(View.GONE);
            alertDialog.dismiss();

            // Si hay conexión a Internet en este momento
          //  Toast.makeText(MainActivity.this, "Si hay conexión a Internet en este momento", Toast.LENGTH_SHORT).show();
        } else {
            // No hay conexión a Internet en este momento
           // Ventana();
            alertDialog.dismiss();
            ventanaDialog();
        }
    }

    private void Ventana(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
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

    private void ventanaCamposIncorrectos(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setMessage("El correo y/o Contraseña es incorrecta")
                .setCancelable(true)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog titulo = alertDialog.create();
        titulo.setTitle("Campos Incorrectos");
        titulo.show();
    }

    private void ventanaDialog() { // este es la nueva ventana (ES LA VERDE)
        // progress_bar.setVisibility(View.GONE);
        alertDialog.dismiss();

        dialog.setContentView(R.layout.pop_internet); // se muestra todos los elementos de la ventana de INTERNET

        popup_window_button = (Button) dialog.findViewById(R.id.popup_window_button);
        popup_window_title = (TextView) dialog.findViewById(R.id.popup_window_title);
        popup_window_text = (TextView) dialog.findViewById(R.id.popup_window_text);


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

    @Override
    protected void onRestart() {
        super.onRestart();
        verificarAccesoInternet();
    }
}
// https://johncodeos.com/how-to-create-a-popup-window-in-android-using-kotlin/