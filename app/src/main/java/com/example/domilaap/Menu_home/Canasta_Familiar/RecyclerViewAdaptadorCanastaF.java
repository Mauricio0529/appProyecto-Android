package com.example.domilaap.Menu_home.Canasta_Familiar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domilaap.Menu_home.Canasta_Familiar.ListarProductos.Aceites.Aceites;
import com.example.domilaap.Menu_home.Canasta_Familiar.ListarProductos.ListaProducto;
import com.example.domilaap.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class RecyclerViewAdaptadorCanastaF extends RecyclerView.Adapter<RecyclerViewAdaptadorCanastaF.ViewHolder> {
    private Context mCtx;
    private List<CanastaFamiliarModelo> canastaFamiliarModelo;

    public RecyclerViewAdaptadorCanastaF(Context mCtx, List<CanastaFamiliarModelo> canastaFamiliarModelo) {
        this.mCtx = mCtx;
        this.canastaFamiliarModelo = canastaFamiliarModelo;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombreProducto;
        private  TextView precio;
        ImageView fotoProducto;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (getAdapterPosition() == 0){ // ARROZ, LENTEJAS, FRIJOLES
                        Intent intent = new Intent(view.getContext(), ListaProducto.class);
                        //intent.putExtra("idalmacen", productoLista.get(getAdapterPosition()).getIdAlmacen());
 //                       intent.putExtra("nombre", productoLista.get(getAdapterPosition()).getNombreProducto());
                        view.getContext().startActivity(intent);
                    }

                    if (getAdapterPosition() == 1){ // Aceites
                        Intent intent = new Intent(view.getContext(), Aceites.class);
                        //intent.putExtra("idalmacen", productoLista.get(getAdapterPosition()).getIdAlmacen());
//                        intent.putExtra("nombre", productoLista.get(getAdapterPosition()).getNombreProducto());
                        view.getContext().startActivity(intent);
                        Toast.makeText(getApplicationContext(), "ITEM ACEITE", Toast.LENGTH_SHORT).show();
                    }

                    if (getAdapterPosition() == 2){ // Harinas
                        Toast.makeText(getApplicationContext(), "ITEM HARINAS", Toast.LENGTH_SHORT).show();
                    }

                    if (getAdapterPosition() == 3){ // BEBIDAS
                        Toast.makeText(getApplicationContext(), "ITEM BABIDAS", Toast.LENGTH_SHORT).show();
                    }

                    if (getAdapterPosition() == 4){ // FRUTAS Y VERDURAS
                        Toast.makeText(getApplicationContext(), "ITEM FRUTAS Y VERDURAS", Toast.LENGTH_SHORT).show();
                    }

                    if (getAdapterPosition() == 5){ // POLLO, CARNE, PESCADO
                        Toast.makeText(getApplicationContext(), "ITEM POLLO, CARNE Y PESCADO", Toast.LENGTH_SHORT).show();
                    }

                    if (getAdapterPosition() == 6){ // LACTEOS
                        Toast.makeText(getApplicationContext(), "ITEM LACTEOS", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            nombreProducto = itemView.findViewById(R.id.tvnombreCanastaFamiliarProducto);
            precio = itemView.findViewById(R.id.tvMunicipio);
            fotoProducto = itemView.findViewById(R.id.imgCanastaFamiliar);
        }
    }

    public List<CanastaFamiliarModelo> productoLista;

    public RecyclerViewAdaptadorCanastaF(List<CanastaFamiliarModelo> productoLista) {
        this.productoLista = productoLista;
    }

    @NonNull
    @Override
    public RecyclerViewAdaptadorCanastaF.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    //    LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_canastafamiliar, parent, false);
        RecyclerViewAdaptadorCanastaF.ViewHolder viewHolder = new RecyclerViewAdaptadorCanastaF.ViewHolder(view);
        return viewHolder;

     /*    LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_canastafamiliar, null);
        //RecyclerViewAdaptadorCanastaF.ViewHolder viewHolder = new RecyclerViewAdaptadorCanastaF.ViewHolder(view);
        return new RecyclerViewAdaptadorCanastaF(view);*/
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdaptadorCanastaF.ViewHolder holder, final int position) {
      //  final CanastaFamiliarModelo item = productoLista.get(position);
       // CanastaFamiliarModelo repartidorModelo = productoLista.get(position);
       /* Glide.with(mCtx)
                .load(productoLista.get(position).getFotoProducto())
                .into(holder.fotoProducto);*/

   //     Picasso.get().load(productoLista+item.getFotoProducto());
        Picasso.get().load(productoLista.get(position).getFotoProducto())
                //.resize(60, 60)
                .into(holder.fotoProducto);

        holder.nombreProducto.setText(productoLista.get(position).getNombreProducto());
        holder.precio.setText(productoLista.get(position).getPrecio());
      //  holder.fotoProducto.setImageResource(productoLista.get(position).getFotoProducto());
    }

    @Override
    public int getItemCount() { // LA CANTIDAD DE ELEMENTOS QUE SE PROCESARA
        return productoLista.size();
    }
}