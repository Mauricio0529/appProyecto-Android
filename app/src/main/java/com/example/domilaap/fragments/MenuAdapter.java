package com.example.domilaap.fragments;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domilaap.Menu_home.AlmacenesCadena.AlmacenesCadena;
import com.example.domilaap.Menu_home.Canasta_Familiar.Canasta_familiar;
import com.example.domilaap.Menu_home.Farmacias.Farmacias;
import com.example.domilaap.Menu_home.Restaurantes.Restaurantes;
import com.example.domilaap.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private Context mCtx;
    private List<MenuModelo> menuModelo;

    public MenuAdapter(Context mCtx, List<MenuModelo> menuModelo) {
        this.mCtx = mCtx;
        this.menuModelo = menuModelo;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombreProducto;
        ImageView fotoProducto;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (getAdapterPosition() == 0){
                        Intent intent = new Intent(view.getContext(), Canasta_familiar.class);
                        view.getContext().startActivity(intent);
                    }
                    if (getAdapterPosition() == 1){
                        Intent intent = new Intent(view.getContext(), AlmacenesCadena.class);
                        view.getContext().startActivity(intent);
                    }
                    if (getAdapterPosition() == 2){
                        Intent intent = new Intent(view.getContext(), Restaurantes.class);
                        view.getContext().startActivity(intent);
                    }
                    if (getAdapterPosition() == 3){
                        Intent intent = new Intent(view.getContext(), Farmacias.class);
                        view.getContext().startActivity(intent);
                    }
                }
            });
            nombreProducto = (TextView) itemView.findViewById(R.id.tvnombreCategorias);
            fotoProducto = (ImageView) itemView.findViewById(R.id.imgCategorias);

         /*   nombreProducto = (TextView) itemView.findViewById(R.id.tvnombreAceite);
            precio = (TextView) itemView.findViewById(R.id.tvMunicipio);
            fotoProducto = (ImageView) itemView.findViewById(R.id.imgAceite);*/
        }

    }
    public List<MenuModelo> productoLista;

    public MenuAdapter(List<MenuModelo> productoLista) {
        this.productoLista = productoLista;
    }


    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        MenuAdapter.ViewHolder viewHolder = new MenuAdapter.ViewHolder(view);
        return viewHolder;
        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder holder, int position) {
        Picasso.get().load(productoLista.get(position).getFotoProducto())
                //.resize(60, 60)
                .into(holder.fotoProducto);
        holder.nombreProducto.setText(productoLista.get(position).getNombre());
    //    holder.fotoProducto.setImageResource(productoLista.get(position).getFotoProducto());

    }

    @Override
    public int getItemCount() {
        return productoLista.size();
    }
}