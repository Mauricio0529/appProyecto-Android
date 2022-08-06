package com.example.domilaap.Listar_repartidor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domilaap.MetodoPago.MetodoPago;
import com.example.domilaap.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class RecyclerViewAdaptadorRepartidor<nombre2> extends RecyclerView.Adapter<RecyclerViewAdaptadorRepartidor.ViewHolder> {
    private Context mCtx;
    private List<RepartidorModelo> repartidorModelo;
//    String nombre2;
 ///   String precio2;
    //int imagen2;

    public RecyclerViewAdaptadorRepartidor(Context mCtx, List<RepartidorModelo> repartidorModelo) {
        this.mCtx = mCtx;
        this.repartidorModelo = repartidorModelo;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombreProducto;
        private TextView precio;
        private TextView tituloRepartidor, telefonoRepartidor;
        //ImageView fotoProducto;

//   AQUI TOCA PASAR LOS DATOS DE LOS REPARTIDORES
        public ViewHolder(@NonNull final View itemView) {   // se envia la info del repartidor
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {

//////
                    final BottomSheetDialog bottomSheetDialog2 = new BottomSheetDialog(
                            view.getContext(), R.style.BottomSheetDialogTheme
                    );

                    View bottomSheetView = LayoutInflater.from(getApplicationContext())
                            .inflate(
                                    R.layout.layout_boton_sheet,
                                    (LinearLayout) view.findViewById(R.id.botonSheetContainer)
                            );


                    tituloRepartidor = bottomSheetView.findViewById(R.id.tituloRepartidor);
                    telefonoRepartidor = bottomSheetView.findViewById(R.id.telefonoRepartidor);

                    tituloRepartidor.setText(productoLista.get(getAdapterPosition()).getNombreProducto());
                    telefonoRepartidor.setText("Telefono: "+productoLista.get(getAdapterPosition()).getPrecio());

                    bottomSheetView.findViewById(R.id.botonRepartidor5).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v2) { // este es el boton de la ventana
                      //      Toast.makeText(v2.getContext(), "REPARTIDOR SELECCIONADO", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(view.getContext(), MetodoPago.class); // Factura.class
                            //intent.putExtra("idalmacen", productoLista.get(getAdapterPosition()).getIdAlmacen());
                            intent.putExtra("nombre", productoLista.get(getAdapterPosition()).getNombreProducto()); // NOMBRE DEL REPARTIDOR
                            intent.putExtra("precio", productoLista.get(getAdapterPosition()).getPrecio()); // TELEFONO DEL REPARTIDOR
                            //  intent.putExtra("nombre", nombre);
                            //   intent.putExtra("precio", precio); // HACER SHARED PREFERENCIA PARA ENVIAR LA INFO DEL PRODUCTO
                            view.getContext().startActivity(intent);
                            bottomSheetDialog2.dismiss();
                        }
                    });

                    bottomSheetDialog2.setContentView(bottomSheetView);
                    bottomSheetDialog2.show();

///////////////

                }

            });
            nombreProducto = (TextView) itemView.findViewById(R.id.tvnombreRepartidor);
            precio = (TextView) itemView.findViewById(R.id.tvMunicipio);

        }

    }


    public List<RepartidorModelo> productoLista;

    public RecyclerViewAdaptadorRepartidor(List<RepartidorModelo> productoLista) {
        this.productoLista = productoLista;
    }

    @NonNull
    @Override
    public RecyclerViewAdaptadorRepartidor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repartidor, parent, false);
        RecyclerViewAdaptadorRepartidor.ViewHolder viewHolder = new RecyclerViewAdaptadorRepartidor.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdaptadorRepartidor.ViewHolder holder, int position) {
        //RepartidorModelo  repartidorModelo2 = repartidorModelo.get(position);
        holder.nombreProducto.setText(productoLista.get(position).getNombreProducto());
        holder.precio.setText(productoLista.get(position).getPrecio());
       // holder.fotoProducto.setImageResource(productoLista.get(position).getFotoProducto());

        /*  holder.nombre2 = holder.getIntent().getStringExtra("resul2");
        precio2 = holder.getIntent().getStringExtra("precio2");
        imagen2 = getIntent().getIntExtra("imagen2", 0);*/
    }

    @Override
    public int getItemCount() { // LA CANTIDAD DE ELEMENTOS QUE SE PROCESARA
        return productoLista.size();
    }


    private void ventanaPoupp() {
    /*    dialog.setContentView(R.layout.epic_popup_positive); // se muestra todos los elementos de la ventana VERDE
        cerrarPositivo = (ImageView) dialog.findViewById(R.id.cerrarPositivo);
        btnAceptar = (Button) dialog.findViewById(R.id.btnAceptar);
        tituloPositivo = (TextView) dialog.findViewById(R.id.tituloPositivo);
        textoPositivo = (TextView) dialog.findViewById(R.id.textoPositivo);
        totalVentana = (TextView) dialog.findViewById(R.id.totalVentana);

        SharedPreferences sharedPreferences = getSharedPreferences("preferenciasP", Context.MODE_PRIVATE);
        String pass = sharedPreferences.getString("precio", "NO HAY DATO");
        boolean sesion = sharedPreferences.getBoolean("sesion", false);
        if (sesion){
            totalVentana.setText("Total pedido: $ " + pass);
        }*/
    }
}
