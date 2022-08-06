package com.example.domilaap.Menu_home.Restaurantes;
 /// MAIN RESTAURANTE

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

public class Restaurantes extends AppCompatActivity {

    private RecyclerView recyclerViewProducto;
    private RecyclerViewAdaptador adaptadorProducto;

    //private static String URL = "https://domilapp.000webhostapp.com/restaurantes.php"; // RESTAURANTES REGISTRADOS
    private static String URL = "https://domilapp.000webhostapp.com/farmaciasCategoria.php";

    //private static String URL = "http://192.168.1.11/domilapp/menu/restaurante/restaurantes.php";
    List<ProductoModelo> productoModelo;
    private TextView titulo;
    Dialog dialog;
    private Button popup_window_button;
    private ProgressBar progress_bar;
    LinearLayout lyLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantes);
        dialog = new Dialog(this);
        progress_bar = findViewById(R.id.progress_bar);
        lyLoading = findViewById(R.id.lyLoading);
        titulo = findViewById(R.id.ts);
        // LO CONECTAMOS CON EL RECYCLERVIEW DEL LAYOUT
        recyclerViewProducto = (RecyclerView) findViewById(R.id.recicleProductos);
        recyclerViewProducto.setHasFixedSize(true);
        recyclerViewProducto.setLayoutManager(new LinearLayoutManager(this));

  //      adaptadorProducto = new RecyclerViewAdaptador(obtenerProducto());
        //recyclerViewProducto.setAdapter(adaptadorProducto);

        productoModelo = new ArrayList<>();

        titulo.setText("Restaurantes");
        // progress_bar.setVisibility(View.VISIBLE);
        lyLoading.setVisibility(View.VISIBLE);
        Mostrar();
    }

 /*   public List<ProductoModelo> obtenerProducto(){
        List<ProductoModelo> producto = new ArrayList<>();
        producto.add(new ProductoModelo("Mandarina y Limon", "4.4 estrellas", R.drawable.photo));
        producto.add(new ProductoModelo("Restaurante La Carbonera", "4.5 estrellas", R.drawable.photo));
        producto.add(new ProductoModelo("A Posto ", "4.0 estrellas", R.drawable.photo));
        producto.add(new ProductoModelo("Restaurante Macondo ", "5 estrellas", R.drawable.photo));
        producto.add(new ProductoModelo("Lola Costillas Infernales", "5 estrellas", R.drawable.photo));
        producto.add(new ProductoModelo("Las Delicias, Comidas Tipicas", "3.5 estrellas", R.drawable.delicias));
        producto.add(new ProductoModelo("El Huacal Parrilla - Bar", "5 estrellas", R.drawable.photo));
        producto.add(new ProductoModelo("Santa Carne espinal", "4.3 estrellas", R.drawable.photo));
        producto.add(new ProductoModelo("Texas Burgers BBQ", "3.9 estrellas", R.drawable.texas)); //COMIDA RAPIDA

        return producto;
    }*/


    private void Mostrar(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject player = array.getJSONObject(i);
                                productoModelo.add(new ProductoModelo(
                                        player.getString("nombre_fc"), // nombre_r
                                        player.getString("direccion_fc"), // direccion_r
                                        player.getString("img_fc") // img_r
                                        // canasta_familiar

                                ));
                            }
                            // progress_bar.setVisibility(View.GONE);
                            lyLoading.setVisibility(View.GONE);
                            adaptadorProducto = new RecyclerViewAdaptador(productoModelo);
                            recyclerViewProducto.setAdapter(adaptadorProducto);
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
* restaurantes
* id_r
* nombre_r
* direccion_r
* img_r
*
* */