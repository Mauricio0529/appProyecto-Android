package com.example.domilaap.Menu_home.Farmacias;

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

public class Farmacias extends AppCompatActivity {
    private RecyclerView recyclerViewFarmacia;
    private RecyclerViewAdaptorF adaptadorFarmacia;
    // private static String URL = "https://domilapp.000webhostapp.com/farmacia.php"; ESTA ES LAS QUE ESTAN TODAS LAS DROGUERIAS
    private static String URL = "https://domilapp.000webhostapp.com/farmaciasCategoria.php";
    //private static String URL = "http://192.168.1.11/domilapp/menu/farmacia/farmacia.php";
    List<FarmaciaModelo> farmaciaModelo;
    private TextView titulo;
    Dialog dialog;
    LinearLayout lyLoading;
    private Button popup_window_button;
    private ProgressBar progress_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmacias);
        titulo = findViewById(R.id.ts);
        dialog = new Dialog(this);
        progress_bar = findViewById(R.id.progress_bar);
        lyLoading = findViewById(R.id.lyLoading);
        recyclerViewFarmacia = (RecyclerView) findViewById(R.id.recicleFarmacia);
        recyclerViewFarmacia.setHasFixedSize(true);
        recyclerViewFarmacia.setLayoutManager(new LinearLayoutManager(this));

        farmaciaModelo = new ArrayList<>();

        titulo.setText("Farmacias");
        // progress_bar.setVisibility(View.VISIBLE);
        lyLoading.setVisibility(View.VISIBLE);
        Mostrar();
      //  adaptadorFarmacia = new RecyclerViewAdaptorF(obtenerFarmacia());
  //      recyclerViewFarmacia.setAdapter(adaptadorFarmacia);
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
                                farmaciaModelo.add(new FarmaciaModelo(
                                        player.getString("nombre_fc"), //cnombre_f VARIABLE DE LA TABLA farmacia; php farmacia.php
                                        player.getString("direccion_fc"),//direccion_f
                                        player.getString("img_fc") // img_f
                                        // canasta_familiar farmacia
                                ));
                            }
                            // progress_bar.setVisibility(View.GONE);
                            lyLoading.setVisibility(View.GONE);
                            adaptadorFarmacia = new RecyclerViewAdaptorF(farmaciaModelo);
                            recyclerViewFarmacia.setAdapter(adaptadorFarmacia);
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

  /*  public List<FarmaciaModelo> obtenerFarmacia(){
        List<FarmaciaModelo> producto = new ArrayList<>();
        producto.add(new FarmaciaModelo("Drogas La Rebaja", "Espinal-Tolima", R.drawable.larebaja));
        producto.add(new FarmaciaModelo("Droguería Andina", "Espinal-Tolima", R.drawable.dandina));
        producto.add(new FarmaciaModelo("Drogas Copifam", "Espinal-Tolima", R.drawable.copifam));
        producto.add(new FarmaciaModelo("Copservir Ltda", "Espinal-Tolima", R.drawable.observer));
        producto.add(new FarmaciaModelo("Droguería Copidrogas", "Espinal-Tolima", R.drawable.dcopi));
        producto.add(new FarmaciaModelo("Drogueria Familiar D.A", "Espinal-Tolima", R.drawable.dfamiliar));
    //    producto.add(new FarmaciaModelo("drogueria Santa Fe", "Espinal-Tolima", R.drawable.ic_launcher_background));
        producto.add(new FarmaciaModelo("Super Baratas", "Espinal-Tolima", R.drawable.sbaratas));
        producto.add(new FarmaciaModelo("Drogas La Economia ", "Espinal-Tolima", R.drawable.deconomia));

        return producto;
    }
*/
}