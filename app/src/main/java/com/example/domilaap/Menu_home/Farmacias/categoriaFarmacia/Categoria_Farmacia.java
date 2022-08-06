package com.example.domilaap.Menu_home.Farmacias.categoriaFarmacia;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domilaap.Menu_home.Canasta_Familiar.ListarProductos.ListaProductoModelo;
import com.example.domilaap.Menu_home.Canasta_Familiar.ListarProductos.RecyclerViewAdaptadorLPC;
import com.example.domilaap.R;

import java.util.ArrayList;
import java.util.List;

public class Categoria_Farmacia extends AppCompatActivity {
   //private RecyclerView recyclerViewFarmacia;
//    private RecyclerViewAdapterCategoriaF adaptadorFarmacia;

  //  private static String URL = "https://domilapp.000webhostapp.com/farmaciasCategoria.php";
        //private static String URL = "http://192.168.1.11/domilapp/menu/farmacia/farmacia.php";
  //  List<FarmaciaModelo> farmaciaModelo;
    private TextView titulo, pasta, jarabe;
    Dialog dialog;
    private Button popup_window_button;
 //     private ProgressBar progress_bar;

    private RecyclerView recyclerViewLPC;
    private RecyclerViewAdaptadorLPC adaptadorLPC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmacia_categoria);
        titulo = findViewById(R.id.ts);
        dialog = new Dialog(this);
  //      progress_bar = findViewById(R.id.progress_bar);
        pasta = findViewById(R.id.pastas);
        jarabe = findViewById(R.id.jarabe);
        //recyclerViewFarmacia = (RecyclerView) findViewById(R.id.recicleFarmacia);
        //recyclerViewFarmacia.setHasFixedSize(true);
        //recyclerViewFarmacia.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewLPC = (RecyclerView) findViewById(R.id.recicleFarmacia);
        recyclerViewLPC.setLayoutManager(new GridLayoutManager(this, 2));

        adaptadorLPC = new RecyclerViewAdaptadorLPC(obtenerListaProducto());
        recyclerViewLPC.setAdapter(adaptadorLPC);

     //   farmaciaModelo = new ArrayList<>();

        titulo.setText("Categorias farmacias");
     //   progress_bar.setVisibility(View.VISIBLE);
     //   Mostrar();

        pasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titulo.setText("Pastillas y capsulas");
                adaptadorLPC = new RecyclerViewAdaptadorLPC(obtenerListaProductoPasta());
                recyclerViewLPC.setAdapter(adaptadorLPC);

               // Toast.makeText(getApplication(), "Mostrando Pastillas y Capsulas", Toast.LENGTH_SHORT).show();
            }
        });

        jarabe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titulo.setText("Jarabe");
                adaptadorLPC = new RecyclerViewAdaptadorLPC(obtenerListaProductoJarabe());
                recyclerViewLPC.setAdapter(adaptadorLPC);
                //Toast.makeText(getApplication(), "Mostrando Jarabe para toz, etc", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public List<ListaProductoModelo> obtenerListaProducto(){
        List<ListaProductoModelo> producto = new ArrayList<>();
        producto.add(new ListaProductoModelo("Jarabe 1", 3350, R.drawable.ic_launcher_background));
        producto.add(new ListaProductoModelo("Jarabe 2", 1700, R.drawable.ic_launcher_background));
        producto.add(new ListaProductoModelo("Pasta 1", 18550, R.drawable.ic_launcher_background));
        producto.add(new ListaProductoModelo("Capsula 1", 3900, R.drawable.ic_launcher_background));
        return producto;
    }

    public List<ListaProductoModelo> obtenerListaProductoPasta(){
        List<ListaProductoModelo> producto = new ArrayList<>();
        producto.add(new ListaProductoModelo("Pasta 1", 18550, R.drawable.ic_launcher_background));
        producto.add(new ListaProductoModelo("Capsula 1", 3900, R.drawable.ic_launcher_background));
        return producto;
    }

    public List<ListaProductoModelo> obtenerListaProductoJarabe(){
        List<ListaProductoModelo> producto = new ArrayList<>();
        producto.add(new ListaProductoModelo("Jarabe 1", 3350, R.drawable.ic_launcher_background));
        producto.add(new ListaProductoModelo("Jarabe 2", 1700, R.drawable.ic_launcher_background));
        return producto;
    }

/*
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
                                        player.getString("nombre_fc"),
                                        player.getString("direccion_fc"),
                                        player.getString("img_fc")
                                        // canasta_familiar farmacia
                                ));
                            }
                            progress_bar.setVisibility(View.GONE);
                            adaptadorFarmacia = new RecyclerViewAdapterCategoriaF(farmaciaModelo);
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

*/

    private void ventanaDialog() { // este es la nueva ventana (ES LA ROJA INTERNET)
      //  progress_bar.setVisibility(View.GONE);
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