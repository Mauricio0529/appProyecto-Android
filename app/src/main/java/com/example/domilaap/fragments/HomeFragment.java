package com.example.domilaap.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.domilaap.ListarCarroCompra.VerCarrito;
import com.example.domilaap.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

   // LinearLayout id_cfamiliar;
   // LinearLayout id_almacenCad;
  // LinearLayout id_restaurante;
   // LinearLayout id_farmacia;
    private RecyclerView recyclerViewMenu;
    private MenuAdapter adaptadorMenu;
    // mostramos la informacion del menu
    private static String URL = "https://domilapp.000webhostapp.com/menuprincipal.php";
    //private static String URL = "http://192.168.1.11/domilapp/menu/menuprincipal/menuprincipal.php";
    List<MenuModelo> menuModelo;
    private Button popup_window_button;
    private ImageButton carritoCompra;


    AlertDialog alertDialog;
    // LinearLayout lyLoading;

    Dialog dialog;


  //  private ProgressBar progress_bar;


    CardView id_cfamiliar;
    CardView id_almacenCad;
    CardView id_restaurante;
    CardView id_farmacia;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        dialog = new Dialog(getContext());

        recyclerViewMenu = (RecyclerView) view.findViewById(R.id.recicleMenu);
        recyclerViewMenu.setHasFixedSize(true);
        recyclerViewMenu.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        progress_bar = view.findViewById(R.id.progress_bar);
        carritoCompra = view.findViewById(R.id.carritoCompra);
        // lyLoading = view.findViewById(R.id.lyLoading);

        menuModelo = new ArrayList<>();

        alertDialog = new SpotsDialog.Builder()
                .setContext(getContext())
                .setMessage("Espere un momento")
                .setCancelable(false)
                .build();

        //adaptadorLPC = new RecyclerViewAdaptadorLPC(obtenerListaProducto());
   //     recyclerViewMenu.setAdapter(adaptadorLPC);

       /* id_cfamiliar = view.findViewById(R.id.id_cfamiliar);
        id_almacenCad = view.findViewById(R.id.id_almacenCad);
        id_restaurante = view.findViewById(R.id.id_restaurante);
        id_farmacia = view.findViewById(R.id.id_farmacia);*/

//       id_cfamiliar = view.findViewById(R.id.id_cfamiliar);
 //      id_almacenCad = view.findViewById(R.id.id_almacenCad);
 //      id_restaurante = view.findViewById(R.id.id_restaurante);
 //      id_farmacia = view.findViewById(R.id.id_farmacia);

        // verificarAccesoInternet();

        //  progress_bar.setVisibility(View.VISIBLE);
        // alertDialog.show();
        // lyLoading.setVisibility(View.VISIBLE);
        verificarAccesoInternet();
        Mostrar(); // esto va en verificarInternet

        carritoCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), VerCarrito.class);
                // intent.putExtra("direccion", direccion); // se envia la direccion del usuario
                startActivity(intent);
            }
        });


        return view;
    }

    private void Mostrar(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject player = array.getJSONObject(i);
                                menuModelo.add(new MenuModelo(
                                        player.getString("nombre_menu"),
                                        player.getString("img_menu")
                                        // canasta_familiar

                                ));
                            }
                            // alertDialog.dismiss();
                            //progress_bar.setVisibility(View.GONE);
                            // lyLoading.setVisibility(View.GONE);

                            adaptadorMenu = new MenuAdapter(menuModelo);
                            recyclerViewMenu.setAdapter(adaptadorMenu);

                            //    RecyclerViewAdaptadorRepartidor recyclerViewAdaptadorRepartidor = new RecyclerViewAdaptadorRepartidor(Repartidor.this, repartidorModelos);
                            //      recyclerRepartidor.setAdapter(recyclerViewAdaptadorRepartidor);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
                        // alertDialog.dismiss();
                        // lyLoading.setVisibility(View.GONE);
                        //progress_bar.setVisibility(View.GONE);
                        ventanaDialog();
                    }
                });
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }

    private void verificarAccesoInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // Si hay conexión a Internet en este momento
            //recyclerViewMenu.setVisibility(View.VISIBLE);
            //Mostrar(); // colocar este aqui
            //  Toast.makeText(MainActivity.this, "Si hay conexión a Internet en este momento", Toast.LENGTH_SHORT).show();
        } else {
            // No hay conexión a Internet en este momento
            // Ventana();

            // alertDialog.show();
           // lyLoading.setVisibility(View.VISIBLE);
            //progress_bar.setVisibility(View.VISIBLE);
            ventanaDialog();

           // recyclerViewMenu.setVisibility(View.GONE);
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

                // alertDialog.dismiss();
                // lyLoading.setVisibility(View.GONE);
                //progress_bar.setVisibility(View.GONE);
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //      dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        verificarAccesoInternet();
    }

    public interface OnFragmentInterationListener {
    }
}

/*
* Almacen cadena:https://domilapp.000webhostapp.com/imgcadena.jpg
* Canasta familiar: https://domilapp.000webhostapp.com/imgcanasta.jpg
* Restaurante; https://domilapp.000webhostapp.com/imgrestaurant.jpg
* Medicina: https://domilapp.000webhostapp.com/imgmedicina.jpg
* */