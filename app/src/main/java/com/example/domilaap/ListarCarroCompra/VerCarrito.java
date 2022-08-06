package com.example.domilaap.ListarCarroCompra;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.domilaap.Listar_repartidor.Repartidor;
import com.example.domilaap.R;
import com.example.domilaap.VisualizarProductos.AdaptadorCarroCompras;
import com.example.domilaap.VisualizarProductos.carritoProducto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VerCarrito extends AppCompatActivity {

    List<carritoProducto> nombreProductos;
    private AdaptadorCarroCompras adaptadorCarroCompras;
    private RecyclerView recyclerView;
    TextView valorTotal; // esta va en la clase NO en el ITEM del recycler
    Button btnSiguiente;
    String ic3 = "";
    EditText txtDireccion;
    TextView txtValidar;
    private static String URL = "https://domilapp.000webhostapp.com/listaCarritoCompraTemp.php";
  //  int ic2;
    Dialog dialog;
    LinearLayout lyLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_carrito);
        //getSupportActionBar().hide();

    //    nombreProductos = (List<carritoProducto>) getIntent().getSerializableExtra("carroCompras"); // llega la LISTA
        dialog = new Dialog(this);
        nombreProductos = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(VerCarrito.this, 1));
        valorTotal = findViewById(R.id.txtValorTotalProductos);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        lyLoading = findViewById(R.id.lyLoading);

      //  adaptadorCarroCompras = new AdaptadorCarroCompras(VerCarrito.this, nombreProductos, valorTotal);
      //  recyclerView.setAdapter(adaptadorCarroCompras);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ventanaDialogDireccion();
               // Toast.makeText(getApplicationContext(), "IR A REPARTIDOR", Toast.LENGTH_SHORT).show();
            }
        });

        lyLoading.setVisibility(View.VISIBLE);
        Mostrar();
    }

    private void ventanaDialogDireccion() { // este es la nueva ventana (ES LA VERDE)
        dialog.setContentView(R.layout.popup_direccion); // se muestra todos los elementos de la ventana VERDE
        ImageView cerrarPositivo = (ImageView) dialog.findViewById(R.id.cerrarPositivo);
        Button btnAceptar = (Button) dialog.findViewById(R.id.btnAceptar);
        txtDireccion = (EditText) dialog.findViewById(R.id.txtDireccion);
        txtValidar = (TextView) dialog.findViewById(R.id.txtValidar);

        cerrarPositivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String direccion = txtDireccion.getText().toString();
                if (!direccion.isEmpty()) {
                    Intent intent = new Intent(VerCarrito.this, Repartidor.class);
                 //   intent.putExtra("direccion", direccion); // se envia la direccion del usuario
                 //   Guardar();
                    startActivity(intent);
                    dialog.dismiss();
                } else {
                    txtValidar.setVisibility(View.VISIBLE);
                }
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }


  /*  private void Guardar(){ // GUARDAR LOS DATOS DEL PRODUCTO
        precioTotal = Integer.toString(resultado);
        //    cantidadProducto = Integer.toString(cantidad);

        SharedPreferences preferences = getSharedPreferences("preferenciasP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("resul", nombre);   // 	NOMBRE PRODUCTO
        editor.putInt("precio", precio);  // precio UNIDAD DEL PRODUCTO
        editor.putString("direccion", direccion);  // direccion del usuario del pedido
        editor.putString("precioCantidad", precioTotal);  // PRECIO A CANTIDAD
        editor.putInt("cantidadProducto", cantidad);  // CANTIDAD DE PRODUCTOS
        editor.putInt("imagen", imagen);  // usu_password
        editor.putBoolean("sesion", true);
        editor.commit();
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
                                nombreProductos.add(new carritoProducto(
                                        player.getString("nombreProducto"),
                                        player.getInt("precio_unidad"),
                                        player.getString("img")
                                ));
                            }
                            lyLoading.setVisibility(View.GONE);
                         //   progress_bar.setVisibility(View.GONE);
                            //adaptadorCarroCompras = new AdaptadorCarroCompras(nombreProductos);

                            adaptadorCarroCompras = new AdaptadorCarroCompras(VerCarrito.this, nombreProductos, valorTotal);

                            recyclerView.setAdapter(adaptadorCarroCompras);
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
                        //ventanaDialog();
                        Toast.makeText(getApplication(), "ERROR",Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }


    private void mostrarContador(){
        //   Guardar();

        SharedPreferences sharedPreferences = this.getSharedPreferences("preferenciasContador", Context.MODE_PRIVATE);
        ic3 = sharedPreferences.getString("add", "No hay dato");
        int ic2 = sharedPreferences.getInt("contador", 0);
         // ic2 = Integer.parseInt(ic3);
       // String arreglo[] = {ic3};
      //  nombreProductos.add(ic3);
        boolean sesion = sharedPreferences.getBoolean("sesion", true);
        if (sesion){

            for (int i = 0; i < ic2; i++){
                // me muestra los numeros
                //Toast.makeText(getApplicationContext(), nombreProductos.get(i), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Tamaño: "+ ic3, Toast.LENGTH_SHORT).show();
            }

            for (int i2 = 0; i2 < nombreProductos.size(); i2++){
                // me muestra los numeros
                //Toast.makeText(getApplicationContext(), nombreProductos.get(i), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Size: "+ nombreProductos.get(i2), Toast.LENGTH_SHORT).show();
            }


        }
    }
}