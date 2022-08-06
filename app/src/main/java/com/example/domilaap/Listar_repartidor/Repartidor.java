package com.example.domilaap.Listar_repartidor;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.domilaap.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Repartidor extends AppCompatActivity {

    private RecyclerView recyclerRepartidor;
    private Button popup_window_button;
    private TextView titulo;
    private RecyclerViewAdaptadorRepartidor adaptadorRepartidor;
    private static String URL = "https://domilapp.000webhostapp.com/listar.php";
    private ProgressBar progress_bar;
    //private static String URL = "http://192.168.1.11/domilapp/domiciliarios/listar.php";
    List<RepartidorModelo> repartidorModelos;
    Dialog dialog;
    LinearLayout lyLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repartidor2);
        dialog = new Dialog(this);
        progress_bar = findViewById(R.id.progress_bar);
        lyLoading = findViewById(R.id.lyLoading);
        titulo = findViewById(R.id.ts);
        recyclerRepartidor = (RecyclerView) findViewById(R.id.recicleRepartidor);
        recyclerRepartidor.setHasFixedSize(true);
        recyclerRepartidor.setLayoutManager(new LinearLayoutManager(this));

        repartidorModelos = new ArrayList<>();
        titulo.setText("Seleccione un Repartidor");

        // progress_bar.setVisibility(View.VISIBLE);
        lyLoading.setVisibility(View.VISIBLE);
        verificarAccesoInternet();
        Mostrar();
       // adaptadorRepartidor = new RecyclerViewAdaptadorRepartidor();
     //   recyclerRepartidor.setAdapter(adaptadorRepartidor);

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
                                repartidorModelos.add(new RepartidorModelo(
                                        player.getString("renombre"),
                                        player.getString("retelefono")
                                ));
                            }
                            // progress_bar.setVisibility(View.GONE);
                            lyLoading.setVisibility(View.GONE);
                            adaptadorRepartidor = new RecyclerViewAdaptadorRepartidor(repartidorModelos);
                            recyclerRepartidor.setAdapter(adaptadorRepartidor);
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
                        ventanaDialog();
                        //Toast.makeText(getApplication(), "ERROR",Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void verificarAccesoInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            lyLoading.setVisibility(View.GONE);
            // progress_bar.setVisibility(View.GONE);
            // Si hay conexión a Internet en este momento
            //  Toast.makeText(MainActivity.this, "Si hay conexión a Internet en este momento", Toast.LENGTH_SHORT).show();
        } else {
            // No hay conexión a Internet en este momento
            // Ventana();
            ventanaDialog();
            //Toast.makeText(MainActivity.this, "No hay conexión a Internet en este momento", Toast.LENGTH_SHORT).show();
        }
    }

    private void ventanaDialog() { // este es la nueva ventana (ES LA DE INTERNET)
        // progress_bar.setVisibility(View.GONE);
        lyLoading.setVisibility(View.GONE);

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
}