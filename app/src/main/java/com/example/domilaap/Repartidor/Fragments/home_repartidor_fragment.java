package com.example.domilaap.Repartidor.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.domilaap.Detalle_pedido.DetallePedido;
import com.example.domilaap.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class home_repartidor_fragment extends Fragment {

    TextView mensaje;
    TextView txt_cpejemplo, txt_cont;
    ImageView Img_espera;

    public home_repartidor_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_repartidor_fragment, container, false);
        mensaje = (TextView) view.findViewById(R.id.mensajeO);
        txt_cpejemplo = (TextView) view.findViewById(R.id.txt_cpejemplo);
        txt_cont = view.findViewById(R.id.txt_cont);
        Img_espera = view.findViewById(R.id.img_cont);

        txt_cont.setVisibility(View.VISIBLE);
        Img_espera.setVisibility(View.VISIBLE);

        mensaje.setVisibility(View.GONE);
        txt_cpejemplo.setVisibility(View.GONE);

        Mostrar();

        txt_cpejemplo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DetallePedido.class);
                startActivity(intent);
       //         Toast.makeText(getContext(), "MOSTRANDO DETALLE PEDIDO...", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    private void Mostrar(){  /// PRODUCTO
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("preferenciasP", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("resul", "NO HAY DATO");
        String pass = sharedPreferences.getString("precio", "NO HAY DATO");
        int imagen = sharedPreferences.getInt("imagen", 0);
        boolean sesion = sharedPreferences.getBoolean("sesion", false);
        if (sesion){
            txt_cont.setVisibility(View.GONE);
            Img_espera.setVisibility(View.GONE);

            mensaje.setVisibility(View.VISIBLE);
            txt_cpejemplo.setVisibility(View.VISIBLE);
            //precioproducto = precioproducto + ", ";
            txt_cpejemplo.setText("Nombre Producto: "+email + "\nPrecio: "+pass);
        } else {
            Toast.makeText(getContext(), "NO HAY DATOS ENVIADOS", Toast.LENGTH_LONG).show();
        }
    }

    private void cancelarPedido(){ //
        SharedPreferences preferences =  this.getActivity().getSharedPreferences("cancelarPedido", Context.MODE_PRIVATE);
        preferences.edit().clear().apply(); // CERRAR SESION
        txt_cpejemplo.setText("Has cancelado tu domicilio");
        //    System.exit(0);
       /* Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);*/
    }




}
