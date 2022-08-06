package com.example.domilaap.fragments;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.domilaap.Historial_Usuario.Historial_usuario;
import com.example.domilaap.MainActivity;
import com.example.domilaap.Menu;
import com.example.domilaap.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

//import .android.synthetic.main.profilefragment.view.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment{
   /* private ProfilePictureView foto_perfil;
    private TextView nombre;
    // public  String informacion = getIntent().getStringExtra("dato");
    private Button cerrar_sesion;
    private Button salir;*/

   Activity activity;
   View view;
   Button cerrar_sesion, popup_window_button;

   private TextView nombre, txtcorreo, cedula, txt_NumTelefono;
   private LinearLayout tvPedidosLinea, historial;
   String precioArroz = "event profile";
   String nombre_usu, apellido_usu, correo_usu, celular_usu, cedula_usu, id_Usuario;
   String nombreCompleto;
   Dialog dialog;
 //  ProfilePictureView foto_perfil;
//   ImageView foto_perfil;
   CircleImageView foto_perfil;

   String email_usuario;
    //private onFragmentInteractionListener onFragmentInteractionListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_profile, container, false);
       // email_usuario = view.getIntent().getStringExtra("dato"); // CORREO
        dialog = new Dialog(getContext());
        nombre = (TextView) view.findViewById(R.id.nombre);
        txtcorreo = (TextView) view.findViewById(R.id.correo);
        cedula = (TextView) view.findViewById(R.id.nombreUsuario);
        cerrar_sesion =(Button) view.findViewById(R.id.cerrar_sesion);
        txt_NumTelefono = (TextView) view.findViewById(R.id.txt_NumTelefono);
//        foto_perfil = (ProfilePictureView) view.findViewById(R.id.foto_perfil);
        foto_perfil = (CircleImageView) view.findViewById(R.id.foto_perfil);

        tvPedidosLinea = view.findViewById(R.id.tvPedidosLinea);
        historial = view.findViewById(R.id.historial);
        mostrarDatosUsuario();

        tvPedidosLinea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               IrPedidos();
            }
        });
        historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Historial_usuario.class);
                view.getContext().startActivity(intent);
                //Toast.makeText(getContext(), "Mostrar historial", Toast.LENGTH_SHORT).show();
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

//        if (AccessToken.getCurrentAccessToken() == null){
//            goMainScreen();
//        } else {
//            Profile profile = Profile.getCurrentProfile();
//            nombre.setText(profile.getName());
    //           foto_perfil.setProfileId(profile.getId());
//            String photoUrl = user.getPhotoUrl().toString();
//            photoUrl = photoUrl + "?type=large";
//            Picasso.get().load(photoUrl).into(foto_perfil); */


            /*    <com.facebook.login.widget.ProfilePictureView
        android:id="@+id/foto_perfil"
        android:layout_width="177dp"
        android:layout_height="192dp"
        android:layout_centerHorizontal="true"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="100dp"
        android:layout_marginRight="112dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.53"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

    </com.facebook.login.widget.ProfilePictureView>*/
 //       }

      //  MostrarArguments();
        cerrar_sesion.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
   //              FirebaseAuth.getInstance().signOut();
    //             LoginManager.getInstance().logOut()
                 cerrarSesionMysql("https://domilapp.000webhostapp.com/cerrarSesion.php");
             }
         });

         //CerrarSesion(view);
         return view;
    }

  /*  private void goMainScreen(){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }*/

    private void cerrarSesionMysql(String URL){   // BASE DE DATOS
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean sessionCuenta = jsonObject.getBoolean("sessionCuenta");
                    if (sessionCuenta == false){
                        goMainScreen();
                    } else  {
                        Toast.makeText(getContext(), "Hubo un error", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(getActivity(), "error try", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // aqui es donde me muestra que el servidor esta apagado
                verificarAccesoInternet();
                // ventanaDialog();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("cedula", cedula_usu);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

  private void mostrarDatosUsuario(){  /// MUESTRA LA INFORMACION DEl USUARIO
      //   Guardar();
      SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("preferenciasL", Context.MODE_PRIVATE);
      id_Usuario = sharedPreferences.getString("id_Usuario", "NO HAY DATO");
      cedula_usu = sharedPreferences.getString("usu_cedula", "NO HAY DATO");
      nombre_usu = sharedPreferences.getString("usu_nombre", "NO HAY DATO");
      apellido_usu = sharedPreferences.getString("usu_apellido", "NO HAY DATO");
      celular_usu = sharedPreferences.getString("usu_celular", "NO HAY DATO");
      correo_usu = sharedPreferences.getString("usu_email", "NO HAY DATO");


      nombreCompleto = nombre_usu + " " + apellido_usu;
      boolean sesion = sharedPreferences.getBoolean("sesion", true);
      if (sesion){
          nombre.setText(nombreCompleto); // nombre usuarip
          txtcorreo.setText(correo_usu); // correo usuario
          cedula.setText(cedula_usu); // la cedula del usuario
          txt_NumTelefono.setText(celular_usu); // numero de celular
      }
  }


  private void goMainScreen(){
      SharedPreferences preferences =  this.getActivity().getSharedPreferences("preferenciasL", Context.MODE_PRIVATE);
      preferences.edit().clear().apply(); // CERRAR SESION
      //    System.exit(0);
      Intent intent = new Intent(getActivity(), MainActivity.class);
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(intent);
  }

    private void IrPedidos(){
        Intent intent = new Intent(getActivity(), Menu.class);
       // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("ir", precioArroz);
        startActivity(intent);
    }

    private void verificarAccesoInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // Si hay conexión a Internet en este momento
            goMainScreen();
            //  Toast.makeText(MainActivity.this, "Si hay conexión a Internet en este momento", Toast.LENGTH_SHORT).show();
        } else {
            // No hay conexión a Internet en este momento
            //Ventana();
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

    public interface OnFragmentInterationListener {
    }

    /*private void MostrarArguments(){
        if (getArguments() != null){
            email_usuario = getArguments().getString("dato");
            Toast.makeText(getContext(), "El Correo es: " + email_usuario, Toast.LENGTH_LONG).show();
        }
    }*/

    /*public void CerrarSesion(View view){
     //   view.cerrar_sesion.setOnClickListener();
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        goMainScreen();
    }*/
}