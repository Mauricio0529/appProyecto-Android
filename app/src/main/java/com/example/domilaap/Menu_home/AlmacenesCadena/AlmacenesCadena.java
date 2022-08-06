package com.example.domilaap.Menu_home.AlmacenesCadena;

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

public class AlmacenesCadena extends AppCompatActivity {

    private RecyclerView recyclerViewAlmacen;
    private RecyclerViewAdaptadorA adaptadorAlmacen;
    List<AlmacenModelo> almacenModelo;

   // private static String URL = "https://domilapp.000webhostapp.com/almacencadena.php"; ALMACENES REGISTRADAS
   private static String URL = "https://domilapp.000webhostapp.com/farmaciasCategoria.php";

    //private static String URL = "http://192.168.1.11/domilapp/menu/almacencadena/almacencadena.php";
    private TextView titulo;
    Dialog dialog;
    LinearLayout lyLoading;
    private Button popup_window_button;
    private ProgressBar progress_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almacenes_cadena);

        // LO CONECTAMOS CON EL RECYCLERVIEW DEL LAYOUT
        recyclerViewAlmacen = (RecyclerView) findViewById(R.id.recicleAlmacenes);
        recyclerViewAlmacen.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAlmacen.setHasFixedSize(true);
        dialog = new Dialog(this);
        progress_bar = findViewById(R.id.progress_bar);
        lyLoading = findViewById(R.id.lyLoading);
        titulo = findViewById(R.id.ts);
  //      adaptadorAlmacen = new RecyclerViewAdaptadorA(obtenerAlmacen());
        recyclerViewAlmacen.setAdapter(adaptadorAlmacen);

        titulo.setText("Almacenes");

        almacenModelo = new ArrayList<>();
        // progress_bar.setVisibility(View.VISIBLE);
        lyLoading.setVisibility(View.VISIBLE);
        Mostrar();
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
                                almacenModelo.add(new AlmacenModelo(
                                        player.getString("nombre_fc"), // nombre_ac
                                        player.getString("direccion_fc"), // direccion_ac
                                        player.getString("img_fc") // img_ac
                                        // canasta_familiar

                                ));
                            }
                            // progress_bar.setVisibility(View.GONE);
                            lyLoading.setVisibility(View.GONE);
                            adaptadorAlmacen = new RecyclerViewAdaptadorA(almacenModelo);
                            recyclerViewAlmacen.setAdapter(adaptadorAlmacen);
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
                        //Toast.makeText(getApplication(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

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
* almacenes_cadena
* id_ac
* nombre_ac
* direccion_ac
* img_ac
* */