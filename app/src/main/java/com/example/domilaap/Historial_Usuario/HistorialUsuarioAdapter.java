package com.example.domilaap.Historial_Usuario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domilaap.R;
import com.example.domilaap.fragments.PedidosModelo;

import java.util.List;

public class HistorialUsuarioAdapter extends RecyclerView.Adapter<HistorialUsuarioAdapter.ViewHolder> {
    private Context mCtx;
    private List<PedidosModelo> pedidosModelo;

    public HistorialUsuarioAdapter(Context mCtx, List<PedidosModelo> pedidosModelo) {
        this.mCtx = mCtx;
        this.pedidosModelo = pedidosModelo;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        // private TextView tvnombreRepartidor;
        private TextView nombreProducto;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  /*  Intent intent = new Intent(view.getContext(), DetallePedido.class);

                    intent.putExtra("imagen", productoLista.get(getAdapterPosition()).getFotoProducto()); // ESTE DA ERROR EN OCACIONES

                    intent.putExtra("productos", productoLista.get(getAdapterPosition()).getProducto()); // NOMBRE DEL REPARTIDOR
                    intent.putExtra("nombre", productoLista.get(getAdapterPosition()).getNombre()); // NOMBRE DEL REPARTIDOR
                    intent.putExtra("telefono_repartidor", productoLista.get(getAdapterPosition()).getTelefono()); // NOMBRE DEL REPARTIDOR

                    intent.putExtra("direccion", productoLista.get(getAdapterPosition()).getDireccion()); //DIRECCION DEL PEDIDO

                    intent.putExtra("cantidad", productoLista.get(getAdapterPosition()).getCantidad()); // NOMBRE DEL REPARTIDOR
                    intent.putExtra("precio", productoLista.get(getAdapterPosition()).getPrecio()); // NOMBRE DEL REPARTIDOR
                    intent.putExtra("total", productoLista.get(getAdapterPosition()).getTotal()); // NOMBRE DEL REPARTIDOR

                    view.getContext().startActivity(intent);*/

                }
            });
            //  tvnombreRepartidor = (TextView) itemView.findViewById(R.id.tvnombreRepartidor);
             // nombreProducto = (TextView) itemView.findViewById(R.id.tvMunicipio);
        }
    }

    public List<PedidosModelo> productoLista;

    public HistorialUsuarioAdapter(List<PedidosModelo> productoLista) {
        this.productoLista = productoLista;
    }

    @NonNull
    @Override
    public HistorialUsuarioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historial, parent, false);
        HistorialUsuarioAdapter.ViewHolder viewHolder = new HistorialUsuarioAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistorialUsuarioAdapter.ViewHolder holder, int position) {
        // MOSTRAR DATOS DEL PEDIDO EN EL RECYCLERVIEW
   //     holder.nombreProducto.setText(productoLista.get(position).getNombre());
        //holder.tvnombreRepartidor.setText(productoLista.get(position).getTelefono());
    }

    @Override
    public int getItemCount() { // LA CANTIDAD DE ELEMENTOS QUE SE PROCESARA
        return productoLista.size();
    }
}