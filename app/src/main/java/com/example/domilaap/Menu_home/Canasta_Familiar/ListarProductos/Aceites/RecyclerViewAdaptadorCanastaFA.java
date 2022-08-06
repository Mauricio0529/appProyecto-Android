package com.example.domilaap.Menu_home.Canasta_Familiar.ListarProductos.Aceites;

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

public class RecyclerViewAdaptadorCanastaFA extends RecyclerView.Adapter<RecyclerViewAdaptadorCanastaFA.ViewHolder>  {

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
            nombreProducto = (TextView) itemView.findViewById(R.id.tvnombreProductoCanasta); // tvnombreAceite
            precio = (TextView) itemView.findViewById(R.id.tvMunicipio); // tvMunicipio
            fotoProducto = (ImageView) itemView.findViewById(R.id.imgProductosCanasta); // imgAceite
        }
    }

    public List<AceiteModelo> productoLista;

    public RecyclerViewAdaptadorCanastaFA(List<AceiteModelo> productoLista) {
        this.productoLista = productoLista;
    }

    @NonNull
    @Override // HAY QUE BORRAR EL XML LLAMADO => item.aceite
    public RecyclerViewAdaptadorCanastaFA.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listaproducto_canasta, parent, false);
        RecyclerViewAdaptadorCanastaFA.ViewHolder viewHolder = new RecyclerViewAdaptadorCanastaFA.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdaptadorCanastaFA.ViewHolder holder, int position) {
        holder.nombreProducto.setText(productoLista.get(position).getNombreProductoCanasta());
        holder.precio.setText(Integer.toString(productoLista.get(position).getPrecioPC()));
        holder.fotoProducto.setImageResource(productoLista.get(position).getFotoProducto());
    }

    @Override
    public int getItemCount() { // LA CANTIDAD DE ELEMENTOS QUE SE PROCESARA
        return productoLista.size();
    }
}