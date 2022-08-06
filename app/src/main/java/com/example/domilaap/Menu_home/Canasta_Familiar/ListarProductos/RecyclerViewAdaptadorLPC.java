package com.example.domilaap.Menu_home.Canasta_Familiar.ListarProductos;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domilaap.R;
import com.example.domilaap.VisualizarProductos.VerProductos;

import java.util.List;

public class RecyclerViewAdaptadorLPC extends RecyclerView.Adapter<RecyclerViewAdaptadorLPC.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombreProducto;
        private  TextView precio;
        ImageView fotoProducto;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), VerProductos.class);
                    //intent.putExtra("idalmacen", productoLista.get(getAdapterPosition()).getIdAlmacen());
                    intent.putExtra("resul",  productoLista.get(getAdapterPosition()).getNombreProductoCanasta());
                    intent.putExtra("imagen", productoLista.get(getAdapterPosition()).getFotoProducto());
                    intent.putExtra("precio", productoLista.get(getAdapterPosition()).getPrecioPC());
                    view.getContext().startActivity(intent);
                }
            });
            nombreProducto = (TextView) itemView.findViewById(R.id.tvnombreProductoCanasta);
            precio = (TextView) itemView.findViewById(R.id.tvMunicipio);
            fotoProducto = (ImageView) itemView.findViewById(R.id.imgProductosCanasta);

         /*   nombreProducto = (TextView) itemView.findViewById(R.id.tvnombreAceite);
            precio = (TextView) itemView.findViewById(R.id.tvMunicipio);
            fotoProducto = (ImageView) itemView.findViewById(R.id.imgAceite);*/
        }
    }

    public List<ListaProductoModelo> productoLista;

    public RecyclerViewAdaptadorLPC(List<ListaProductoModelo> productoLista) {
        this.productoLista = productoLista;
    }

    @NonNull//item_listaproducto_canasta
    @Override
    public RecyclerViewAdaptadorLPC.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listaproducto_canasta, parent, false);
        RecyclerViewAdaptadorLPC.ViewHolder viewHolder = new RecyclerViewAdaptadorLPC.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdaptadorLPC.ViewHolder holder, int position) {
        holder.nombreProducto.setText(productoLista.get(position).getNombreProductoCanasta());
        holder.precio.setText(Integer.toString(productoLista.get(position).getPrecioPC()));
        holder.fotoProducto.setImageResource(productoLista.get(position).getFotoProducto());
    }

    @Override
    public int getItemCount() { // LA CANTIDAD DE ELEMENTOS QUE SE PROCESARA
        return productoLista.size();
    }

    private void Guardardatos(){

    }
}