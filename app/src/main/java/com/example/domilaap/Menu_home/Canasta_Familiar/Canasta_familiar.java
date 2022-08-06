package com.example.domilaap.Menu_home.Canasta_Familiar;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

public class Canasta_familiar extends AppCompatActivity {

    private RecyclerView recyclerViewCanastaF;
    private TextView titulo;
    private RecyclerViewAdaptadorCanastaF adaptadorCanastaFamiliar;
    private static String URL = "https://domilapp.000webhostapp.com/arroces.php";

    Dialog dialog;
    LinearLayout lyLoading;
    private Button popup_window_button;
    private ProgressBar progress_bar;
    //private static String URL = "http://192.168.1.11/domilapp/canastafamiliar/listaCategoria/arroces.php";
    List<CanastaFamiliarModelo> canastaFamiliarModelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canasta_familiar);
        dialog = new Dialog(this);
        // progress_bar = findViewById(R.id.progress_bar);
        lyLoading = findViewById(R.id.lyLoading);

        titulo = findViewById(R.id.ts);

        // LO CONECTAMOS CON EL RECYCLERVIEW DEL LAYOUT
        recyclerViewCanastaF = (RecyclerView) findViewById(R.id.recicleCanastaFamiliar);

     //   adaptadorCanastaFamiliar = new RecyclerViewAdaptadorCanastaF(obtenerAlmacen());
        recyclerViewCanastaF.setHasFixedSize(true);
        recyclerViewCanastaF.setLayoutManager(new LinearLayoutManager(this));

        canastaFamiliarModelo = new ArrayList<>();

        titulo.setText("Canasta Familiar");

        // progress_bar.setVisibility(View.VISIBLE);
        lyLoading.setVisibility(View.VISIBLE);
        Mostrar();
        //recyclerViewCanastaF.setAdapter(adaptadorCanastaFamiliar);
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
                                canastaFamiliarModelo.add(new CanastaFamiliarModelo(
                                        player.getString("nombre_cf"),
                                        player.getString("direccion_cf"),
                                        player.getString("img_cf")
                                        // canasta_familiar
                                ));
                            }
                            // progress_bar.setVisibility(View.GONE);
                            lyLoading.setVisibility(View.GONE);
                            adaptadorCanastaFamiliar = new RecyclerViewAdaptadorCanastaF(canastaFamiliarModelo);
                            recyclerViewCanastaF.setAdapter(adaptadorCanastaFamiliar);
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
                       // Toast.makeText(getApplication(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }
   /* private void SeleccionProducto(){
        startActivity(new Intent(Canasta_familiar.this, VerProductos.class));
    }*/

    private void ventanaDialog() { // este es la nueva ventana (ES LA ROJA INTERNET)
        // progress_bar.setVisibility(View.GONE);
        lyLoading.setVisibility(View.GONE);
        dialog.setContentView(R.layout.pop_internet); // se muestra todos los elementos de la ventana de INTERNET

        popup_window_button = (Button) dialog.findViewById(R.id.popup_window_button);
        //   popup_window_title = (TextView) dialog.findViewById(R.id.popup_window_title);
        //   popup_window_text = (TextView) dialog.findViewById(R.id.popup_window_text);

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
/*
TOCA CAMBIAR LAS ULTIMAS 3 IMAGENES EN LA CATEGORIA CASTA FAMILIAR:
FRUTAS, CARNES Y LACTEOS

* canasta_familiar == base de datos
* nombre_cf
* direccion_cf
* img_cf
* */

/*
* Arroz: https://domilapp.000webhostapp.com/granos.jpg
* Carne: https://domilapp.000webhostapp.com/carnes.jpg
* lacteos: https://domilapp.000webhostapp.com/lacteos.jpg
* https://domilapp.000webhostapp.com/frutasverduras.jpg*/