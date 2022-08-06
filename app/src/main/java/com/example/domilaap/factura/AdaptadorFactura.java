package com.example.domilaap.factura;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domilaap.R;

import java.util.List;

public class AdaptadorFactura extends RecyclerView.Adapter<AdaptadorFactura.ViewHolder> {
    private Context mCtx;
    private List<FacturaModelo> facturaModelo;
   // int contProducto = 0;

    public AdaptadorFactura(Context mCtx, List<FacturaModelo> facturaModelo) {
        this.mCtx = mCtx;
        this.facturaModelo = facturaModelo;
/*
        for (int i = 0; i < facturaModelo.size(); i++) {
          contProducto++;
        }*/

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombreProducto;
        private TextView precio;
        private TextView txtcontador;
        private ImageView fotoProducto;

        //   AQUI TOCA PASAR LOS DATOS DE LOS PRODUCTOS
        public ViewHolder(@NonNull final View itemView) {   // se envia la info del repartidor
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    Toast.makeText(view.getContext(), "Producto", Toast.LENGTH_SHORT).show();
                }
            });
           // txtcontador = (TextView) itemView.findViewById(R.id.tvIdContador);
            nombreProducto = (TextView) itemView.findViewById(R.id.txtNombrePro);
            precio = (TextView) itemView.findViewById(R.id.txtprecioCantidad);
            fotoProducto = (ImageView) itemView.findViewById(R.id.imgProducto);
        }
    }


    // public List<FacturaModelo> facturaModelo2;

    public AdaptadorFactura(List<FacturaModelo> facturaModelo) {
        this.facturaModelo = facturaModelo;
    }

    @NonNull
    @Override
    public AdaptadorFactura.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto_factura, parent, false);
        AdaptadorFactura.ViewHolder viewHolder = new AdaptadorFactura.ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull AdaptadorFactura.ViewHolder holder, int position) {

        // holder.txtcontador.setText(""+contProducto);
        /* Picasso.get().load(facturaModelo.get(position).getFotoProducto())
                .into(holder.fotoProducto); */
        holder.nombreProducto.setText(facturaModelo.get(position).getNombreProducto());
        holder.precio.setText(""+facturaModelo.get(position).getPrecio());
    }

    @Override
    public int getItemCount() {
        return facturaModelo.size();
    }
}