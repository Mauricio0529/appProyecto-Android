package com.example.domilaap.Repartidor.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.domilaap.R;
import com.example.domilaap.Repartidor.Login_repartidor;

/**
 * A simple {@link Fragment} subclass.
 */
public class perfil_repartidor_fragment extends Fragment {
    private Button Salir;
    private TextView nombre;
    private String  cedula, nombre_repartidor, apellido_repartidor, correo_repartidor, telefono_repartidor;
    private String nombreCompleto;

    public perfil_repartidor_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil_repartidor_fragment, container, false);
        Salir = view.findViewById(R.id.cerrar_sesion);
        nombre = view.findViewById(R.id.nombre);

        Salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goMainScreen();
            }
        });

        Mostrar();
        mostrarDatosRepartidor();
        return view;
    }

    private void goMainScreen(){
        SharedPreferences preferences =  this.getActivity().getSharedPreferences("preferenciasLR", Context.MODE_PRIVATE);
        preferences.edit().clear().apply(); // CERRAR SESION
        //    System.exit(0);
        Intent intent = new Intent(getActivity(), Login_repartidor.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        //System.exit(0);
        startActivity(intent);
    }

    private void mostrarDatosRepartidor(){  /// MUESTRA LA INFORMACION DEL REPARTIDOR
        //   Guardar();
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("preferenciasLR", Context.MODE_PRIVATE);
        cedula = sharedPreferences.getString("cedula", "NO HAY DATO");
        nombre_repartidor = sharedPreferences.getString("renombre", "NO HAY DATO");
        apellido_repartidor = sharedPreferences.getString("reapellido", "NO HAY DATO");
        correo_repartidor = sharedPreferences.getString("reemail", "NO HAY DATO");
        telefono_repartidor = sharedPreferences.getString("retelefono", "NO HAY DATO");

        nombreCompleto = nombre_repartidor + " " + apellido_repartidor;
        boolean sesion = sharedPreferences.getBoolean("sesion", true);
        if (sesion){
            nombre.setText(nombreCompleto);
        }
    }

    private void Mostrar(){  /// MOSTRAR DATOS DEL PRODUCTO Y PEDIDO
        SharedPreferences sharedPreferences =  this.getActivity().getSharedPreferences("preferenciasRR", Context.MODE_PRIVATE);
        String cedula = sharedPreferences.getString("cedula", "NO HAY DATO");
        String renombre = sharedPreferences.getString("renombre", "NO HAY DATO");
        String reapellido = sharedPreferences.getString("reapellido", "NO HAY DATO");
        String reemail = sharedPreferences.getString("reemail", "NO HAY DATO");
        String repass = sharedPreferences.getString("repass", "NO HAY DATO");
        String retelefono = sharedPreferences.getString("retelefono", "NO HAY DATO");
        boolean sesion = sharedPreferences.getBoolean("sesion", false);
        if (sesion){
     //       nombre.setText(renombre);
            //       txtTotal.setText(reapellido);
        }
    }
}