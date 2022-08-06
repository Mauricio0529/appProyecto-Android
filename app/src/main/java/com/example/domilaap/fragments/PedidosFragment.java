package com.example.domilaap.fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.domilaap.MainActivity;
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
public class PedidosFragment extends Fragment {

  //  TextView mensaje;
  //  TextView txt_cpejemplo;
    //  TextView tvnoproducto;
  //  String nombreproducto, precioproducto;
 //   Button btnSalir;
 //   String email;
 //   String pass;
 //   int imagen;
    private RecyclerView recyclerViewPedido;
    private PedidosAdapter pedidosAdapter;
    List<PedidosModelo> pedidosModelos;
    private String nombre_usu;
    private String URL;
    //private static String URL = "http://192.168.1.11/domilapp/menu/pedidos/listaPedidos.php";
    boolean pasar = false;
    AlertDialog alertDialog;
    public PedidosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pedidos, container, false);

        //mensaje = (TextView) view.findViewById(R.id.mensajeO); // este se borra
    //    txt_cpejemplo = (TextView) view.findViewById(R.id.txt_cpejemplo); // este va hacer la LISTA se BORRA
       // tvnoproducto = (TextView) view.findViewById(R.id.tvnoproducto); // este SE DEJA es el que dice que no hay producto en camino

        recyclerViewPedido = (RecyclerView) view.findViewById(R.id.reciclePedidos);
        recyclerViewPedido.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewPedido.setHasFixedSize(true);

        recyclerViewPedido.setAdapter(pedidosAdapter);
        pedidosModelos = new ArrayList<>();

        alertDialog = new SpotsDialog.Builder()
                .setContext(getContext())
                .setMessage("Espere un momento")
                .setCancelable(false)
                .build();

      /*  if (pedidosModelos.isEmpty()){
            tvnoproducto.setVisibility(View.GONE); // se coloca GONE para que no se visualize en el pedido
            recyclerViewPedido.setVisibility(View.VISIBLE);
            Mostrar();
        } else {
            tvnoproducto.setVisibility(View.VISIBLE); // se coloca GONE para que no se visualize en el pedido
            recyclerViewPedido.setVisibility(View.GONE);
        }*/



      /*  if (usuario != null){
      // para validar si hay algun pedido en camino para mostrar la lista
            mostrarDatosUsuario(); // ESTE ES EL USUARIO DEL LOGIN QUE COMPARAMOS
            Mostrar();
        } else {
        // y si no se muestra que no hay productos en camino
            tvnoproducto.setVisibility(View.GONE); // se coloca GONE para que no se visualize en el pedido
            recyclerViewPedido.setVisibility(View.VISIBLE);
        }
        */
        alertDialog.show();
        mostrarDatosUsuario(); // ESTE ES EL USUARIO DEL LOGIN QUE COMPARAMOS
        Mostrar();

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("preferenciasDT", Context.MODE_PRIVATE);
        pasar = sharedPreferences.getBoolean("dat", true);

       // boolean sesion = sharedPreferences.getBoolean("sesion", false);



        /// ESTE ES QUE COMENTE NUEVO
  /*      if (pasar == true){
            tvnoproducto.setVisibility(View.VISIBLE); // se coloca GONE para que no se visualize en el pedido
            recyclerViewPedido.setVisibility(View.GONE);
        } else {
            tvnoproducto.setVisibility(View.GONE); // se coloca GONE para que no se visualize en el pedido
            recyclerViewPedido.setVisibility(View.VISIBLE);
            // mensaje.setVisibility(View.VISIBLE);
            // txt_cpejemplo.setVisibility(View.VISIBLE);
            // precioproducto = precioproducto + ", ";
            // txt_cpejemplo.setText("Nombre Producto: "+email + "\nPrecio: "+pass);
            Mostrar();
        }*/




//        mensaje.setVisibility(View.GONE); // texto sin productos
//        txt_cpejemplo.setVisibility(View.GONE);
       // tvnoproducto.setVisibility(View.VISIBLE);
  /*      SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("preferenciasP2", Context.MODE_PRIVATE);
      //  email = sharedPreferences.getString("resul2", "NO HAY DATO");
       // pass = sharedPreferences.getString("precio2", "NO HAY DATO");
       // imagen = sharedPreferences.getInt("imagen2", 0);
        boolean sesion = sharedPreferences.getBoolean("sesion", false);
        if (sesion){
            tvnoproducto.setVisibility(View.GONE); // se coloca GONE para que no se visualize en el pedido
            recyclerViewPedido.setVisibility(View.VISIBLE);
           // mensaje.setVisibility(View.VISIBLE);
           // txt_cpejemplo.setVisibility(View.VISIBLE);
           // precioproducto = precioproducto + ", ";
           // txt_cpejemplo.setText("Nombre Producto: "+email + "\nPrecio: "+pass);
            Mostrar();
        } else {
            Toast.makeText(getContext(), "NO HAY DATOS ENVIADOS", Toast.LENGTH_LONG).show();
        }

*/


 //       nombreproducto = getArguments().getString("nombre");  // llega el argumento del MENU
 //       precioproducto = getArguments().getString("precio");


    /*        if (nombreproducto != null && precioproducto != null) {
            tvnoproducto.setVisibility(View.GONE);
            mensaje.setVisibility(View.VISIBLE);
            txt_cpejemplo.setVisibility(View.VISIBLE);
            precioproducto = precioproducto + ", ";
            txt_cpejemplo.setText("Nombre Producto: "+nombreproducto + "\n Precio: "+i);
        } else {
            Toast.makeText(getContext(), "NO HAY DATOS ENVIADOS", Toast.LENGTH_LONG).show();
        }*/

    /*    txt_cpejemplo.setOnClickListener(new View.OnClickListener() { // IR A DETALLE PEDIDO
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetallePedido.class);
                startActivity(intent);
                Toast.makeText(getContext(), "MOSTRANDO PRODUCTO...", Toast.LENGTH_SHORT).show();
            }
        });*/

      /*  if (getArguments() != null){
            precio_producto = getArguments().getInt("precio");
            //Toast.makeText(getContext(), "Precio: " + precio_producto, Toast.LENGTH_LONG).show();
            mensaje.setText("EL PRECIO ES: " + precio_producto);
        } else {
            mensaje.setVisibility(View.GONE); // texto sin productos
        }*/
        return view;
    }
  /* private void Mostrar2(){  /// MOSTRAR LA INFORMACION DEL PRODUCTO QUE GUARDAMOS EN LA FACTURA
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("preferenciasP2", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("resul2", "NO HAY DATO");
        pass = sharedPreferences.getString("precio2", "NO HAY DATO");
        imagen = sharedPreferences.getInt("imagen2", 0);
        boolean sesion = sharedPreferences.getBoolean("sesion", false);
        if (sesion){
            tvnoproducto.setVisibility(View.GONE);
            mensaje.setVisibility(View.VISIBLE);
            txt_cpejemplo.setVisibility(View.VISIBLE);
            precioproducto = precioproducto + ", ";
            txt_cpejemplo.setText("Nombre Producto: "+email + "\nPrecio: "+pass);
        } else {
            Toast.makeText(getContext(), "NO HAY DATOS ENVIADOS", Toast.LENGTH_LONG).show();
        }
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
                                pedidosModelos.add(new PedidosModelo(

                                        player.getString("productos"),
                                        player.getString("nombre_repartidor"), // nombre repartidor
                                        player.getString("nombre_usuario"), // nombre del usuario
                                        player.getString("telefono_repartidor"), // telefono repartidor
                                        player.getInt("cantidad"),// cantidad producto
                                        player.getString("precio_unidad"), //precio del producto
                                        player.getString("total"), // precio por cantidadplayer.getString("total") // precio por cantidad
                                        player.getString("direccion"), //direccion del pedido
                                        player.getString("img_producto")

                                        // canasta_familiar
                                ));
                            }
                            if (pedidosModelos.isEmpty()){
                                alertDialog.dismiss();
                                Toast.makeText(getContext(), "No hay pedidos pendientes", Toast.LENGTH_SHORT).show();
                            } else {
                            alertDialog.dismiss();
                            pedidosAdapter = new PedidosAdapter(pedidosModelos);
                            recyclerViewPedido.setAdapter(pedidosAdapter);
                            //    RecyclerViewAdaptadorRepartidor recyclerViewAdaptadorRepartidor = new RecyclerViewAdaptadorRepartidor(Repartidor.this, repartidorModelos);
                            //      recyclerRepartidor.setAdapter(recyclerViewAdaptadorRepartidor);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Hay un error con el servidor", Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

    private void mostrarDatosUsuario(){  /// MUESTRA LA INFORMACION DEl USUARIO
        //   Guardar();
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("preferenciasL", Context.MODE_PRIVATE);
        nombre_usu = sharedPreferences.getString("usu_nombre", "NO HAY DATO");
       // apellido_usu = sharedPreferences.getString("usu_apellido", "NO HAY DATO");
       // correo_usu = sharedPreferences.getString("usu_email", "NO HAY DATO");

        //nombreCompleto = nombre_usu + " " + apellido_usu;
        boolean sesion = sharedPreferences.getBoolean("sesion", true);
        if (sesion){
          //  nombre.setText(nombreCompleto);
            URL = "https://domilapp.000webhostapp.com/listaPedidos.php?nombre_usuario="+nombre_usu+"";
        }
    }

    private void goMainScreen(){ // NO SIRVE PARA  NADA EN ESTA METODO
        SharedPreferences preferences =  this.getActivity().getSharedPreferences("preferenciasL", Context.MODE_PRIVATE);
        preferences.edit().clear().apply(); // CERRAR SESION
    //    System.exit(0);
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void cancelarPedido(){ // SE GUARDA LA CANCELACION DEL PEDIDO
        SharedPreferences preferences = this.getActivity().getSharedPreferences("cancelarPedido", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
    //    editor.putString("resul2", email);   // 	NOMBRE DEL PRODUCTO
    //    editor.putString("precio2", pass);  // PRECIO DEL PRODUCTO
    //    editor.putInt("imagen2", imagen);  // IMAGEN DEL PRODUCTO
        editor.putBoolean("sesion", true);
        editor.commit();
    }


    public interface OnFragmentInterationListener {
    }
    //  private void MostrarArguments(){
    //}
}