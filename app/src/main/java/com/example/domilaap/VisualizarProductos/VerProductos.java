package com.example.domilaap.VisualizarProductos;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.domilaap.ListarCarroCompra.VerCarrito;
import com.example.domilaap.Listar_repartidor.Repartidor;
import com.example.domilaap.Menu;
import com.example.domilaap.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerProductos extends AppCompatActivity {

  //  ImageView ivProducto;
    private ImageButton btn_atras;
    private ImageView cerrarPositivo;
    private Button btnAceptar, mostrarCarrito;
    private ImageButton addCarrito;
    private EditText txtDireccion;
    private TextView tvNombre, tvPrecioProducto, tvnn, titulo, txtContador, total, txtValidar;
    private Button btn_verproductos, btnAñadirProducto, btnMas, btnMenos;
    private String nombre = "", irMenu = "llega";
    String id_Usuario;
    private int precio=0;
    private String precioTotal, cantidadProducto, direccion;
    private List<carritoProducto> nombreProductos = new ArrayList<>();
    //public ArrayList<String> precioProductos = new ArrayList<>();
    //public ArrayList<String> imgProductos = new ArrayList<>();
    int imagen;
    int cantidad = 1;
    // int precioCantidad = 0;
    int resultado = 0;
    String img2 = "";
    SliderView sliderView;
    // int precio_arroz = 1650;
    String precioArroz = "1650";
    // TextView vernombre;q
    int precioP = 0;
    int ic = 0, ic2 = 0;
    Dialog dialog;
//    Notificaciones notificaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_productos);

        btn_verproductos = findViewById(R.id.btn_continuar);
//titulo = findViewById(R.id.ts);
        dialog = new Dialog(this);

        sliderView = findViewById(R.id.slider2);
//        ivProducto = findViewById(R.id.ivProducto); // este es la IMAGEN DEL PRODUCTO


        tvNombre = findViewById(R.id.tvNombre);
        btn_atras = findViewById(R.id.imageButton);
        tvPrecioProducto = findViewById(R.id.tvPrecioProducto);
 //       tvnn = findViewById(R.id.tvnn);
        btnAñadirProducto = findViewById(R.id.btnAñadirProducto);
        btnMas = findViewById(R.id.btnMas);
        btnMenos = findViewById(R.id.btnMenos);
        txtContador = findViewById(R.id.txtContador);
        total = findViewById(R.id.total);
        addCarrito = findViewById(R.id.addCarrito);
        mostrarCarrito =  findViewById(R.id.mostrarCarrito);


//titulo.setText("Detalle Producto");

//        notificaciones = new Notificaciones(findViewById(R.id.bell));
//        notificationCounter = new NotificationCounter(findViewById(R.id));
        //Intent intent = getIntent();
        //productoLista = getProductoLista();
       // Intent intent = getIntent();
        //Bundle b = intent.getExtras();

        // OBTENEMOS LOS DATOS DE LOS PRODUCTOS de aceite, ListaProducto
        nombre = getIntent().getStringExtra("resul");
        precio = getIntent().getIntExtra("precio", 0);
        imagen = getIntent().getIntExtra("imagen", 0);

        total.setText(Integer.toString(precio)); // precio INICIAL PARA EL PRECIO POR CANTIDAD

        if (nombre != null){

            tvNombre.setText(nombre);

           // ivProducto.setImageResource(imagen);
            int[] imagenes = {imagen, imagen};
            SliderAdapter sliderAdapter = new SliderAdapter(imagenes);

            sliderView.setSliderAdapter(sliderAdapter);
            sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
            sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
            sliderView.startAutoCycle();

            tvPrecioProducto.setText(Integer.toString(precio)); // precio PRODUCTO UNITARIOS
            txtContador.setText(Integer.toString(cantidad)); // CANTIDAD DEL PRODUCTO; SE INICIA CON 1
           // tvPrecioProducto.setText(informacion1);
            //ivProducto.setImageResource(ProductoModelo);
        }

        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cantidad++; // cantidad es el numero total de productos
               // precioCantidad = Short.parseShort(String.valueOf(cantidad));
                if (cantidad > 9){
                    cantidad = 9;
                    txtContador.setText(Integer.toString(cantidad));
                } else { // precioCantidad
                    resultado = precio * cantidad; // el (RESULTADO) lleva precio por cantidad
                    total.setText(Integer.toString(resultado));
                    txtContador.setText(Integer.toString(cantidad));// 6 800 *4; 5 100 *3
                }
            }
        });

        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cantidad--;
// precioCantidad=Integer.valueOf(precio); resultado=precioCantidad*cantidad; total.setText(resultado);
                if (cantidad <= 1){
                    cantidad = 1;
                    total.setText(Integer.toString(precio)); // precio
                    txtContador.setText(Integer.toString(cantidad));
                } else {
                    resultado -= precio;
                    total.setText(Integer.toString(resultado));
                    txtContador.setText(Integer.toString(cantidad));
                }
            }
        });

        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
// ENVIAR LOS DATOS DEL PRODUCTO A LA ACTIVITY REPARTTIDOR

        // boton ONCLIC
        btn_verproductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ventanaDialogDireccion();
                               // Toast.makeText(getApplicationContext(), "Compra exitosa!", Toast.LENGTH_LONG).show();
            }
        }); // fin ONCLIC

        btnAñadirProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Esta opcion no esta lista en este Momento.", Toast.LENGTH_SHORT).show();
            }
        });

        addCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarIdUsuario();
                addCarritoCompra("https://domilapp.000webhostapp.com/carritoCompraTemp.php");
              //  addCarritoCompra();
              //  guardarContador();
              //  mostrarCarrito();

                Intent intent = new Intent(getApplicationContext(), Menu.class);
                intent.putExtra("addCarrito", irMenu); // Se envia esta variable para ir al fragment HOME (addCarrito)
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Agrega mas productos al carrito.", Toast.LENGTH_SHORT).show();
            }
        });

        mostrarCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarCarrito();
            }
        });
    }


// ADD CARRITO
   private void addCarritoCompra(String URL){
        // FALTA AGREGAR POR MEDIO DE BASE DE DATOS
      // nombreProductos.add(new carritoProducto(nombre, imagen, precio)); // ACTIVITY MODELO; CAMBIO DE STRING A INT
          // guardarContador();

       StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               try {
                   JSONObject jsonObject = new JSONObject(response);
                   String ok = jsonObject.getString("status"); // mensaje del PHP
                   String msg = jsonObject.getString("msg");
                   //       String hased = jsonObject.getString("hased");
                   if (ok.equals("Ok")){ /// se compara el Ok que esta en el PHP
                       /// SE REGISTRO CORRECTAMENTE
                       Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                       //Toast.makeText(getApplicationContext(), hased, Toast.LENGTH_LONG).show();
                       //   System.out.println(hased);
                   } else {
                       // HAY UN ERROR CON EL REGISTRO
                       Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                   }

               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               //ventanaDialog();
               Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
           }
       }){
           @Override
           protected Map<String, String> getParams() throws AuthFailureError {
               Map<String, String> parametros = new HashMap<String, String>();
               parametros.put("id_usuarioCarrito", id_Usuario);
               parametros.put("nombreProducto", nombre);
               parametros.put("precio_unidad", String.valueOf(precio));
               parametros.put("precioCarritoTotal", "5");
               parametros.put("cantidadProducto", String.valueOf(cantidad));
               parametros.put("img", String.valueOf(imagen)); //

               return parametros;
           }
       };
       RequestQueue requestQueue = Volley.newRequestQueue(this);
       requestQueue.add(stringRequest);

   }


   private void mostrarCarrito(){
       // FALTA MOSTRAR POR MEDIO DE BASE DE DATOS
      // guardarContador(); mostrarContador();
       Intent intent = new Intent(VerProductos.this, VerCarrito.class);
      // intent.putExtra("carroCompras", (Serializable) nombreProductos); // se envia el array
       //Guardar();
       startActivity(intent);
   }

    private void guardarContador(){ // GUARDAR LOS DATOS DEL PRODUCTO
        SharedPreferences preferences = getSharedPreferences("preferenciasCarritoCompra", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("contador", ic); // el ic es el tamaño del lISTaRRAY
        // editor.putString("add", nombreProductos.get(ic));
        editor.putBoolean("sesion", true);
        editor.commit();
    }

    private void mostrarContador(){
        //   Guardar();
        SharedPreferences sharedPreferences = this.getSharedPreferences("preferenciasContador", Context.MODE_PRIVATE);
        // String ic3 = sharedPreferences.getString("contador", "No hay dato");
        // ic2 = Integer.parseInt(ic3);
        boolean sesion = sharedPreferences.getBoolean("sesion", true);
        if (sesion){
            int siz = nombreProductos.size();
          //  Toast.makeText(getApplicationContext(), nombreProductos.get(ic), Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), siz, Toast.LENGTH_SHORT).show();
        }
    }
// ADD CARRITO


    private void ventanaDialogDireccion() { // este es la nueva ventana (ES LA VERDE)
        dialog.setContentView(R.layout.popup_direccion); // se muestra todos los elementos de la ventana VERDE
        cerrarPositivo = (ImageView) dialog.findViewById(R.id.cerrarPositivo);
        btnAceptar = (Button) dialog.findViewById(R.id.btnAceptar);
        txtDireccion = (EditText) dialog.findViewById(R.id.txtDireccion);
        txtValidar = (TextView) dialog.findViewById(R.id.txtValidar);

        cerrarPositivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direccion = txtDireccion.getText().toString();
                if (!direccion.isEmpty()) {
                    Intent intent = new Intent(VerProductos.this, Repartidor.class);
                    intent.putExtra("direccion", direccion); // se envia la direccion del usuario
                    Guardar();
                    startActivity(intent);
                    dialog.dismiss();
                } else {
                    txtValidar.setVisibility(View.VISIBLE);
                }
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void Guardar(){ // GUARDAR LOS DATOS DEL PRODUCTO
        precioTotal = Integer.toString(resultado);
    //    cantidadProducto = Integer.toString(cantidad);

        SharedPreferences preferences = getSharedPreferences("preferenciasP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("resul", nombre);   // 	NOMBRE PRODUCTO
        editor.putInt("precio", precio);  // precio UNIDAD DEL PRODUCTO
        editor.putString("direccion", direccion);  // direccion del usuario del pedido
        editor.putString("precioCantidad", precioTotal);  // PRECIO A CANTIDAD
        editor.putInt("cantidadProducto", cantidad);  // CANTIDAD DE PRODUCTOS
        editor.putInt("imagen", imagen);  // usu_password
        editor.putBoolean("sesion", true);
        editor.commit();
    }

    private void mostrarIdUsuario(){  /// MUESTRA LA INFORMACION DEl USUARIO
        //   Guardar();
        SharedPreferences sharedPreferences = this.getSharedPreferences("preferenciasL", Context.MODE_PRIVATE);
        id_Usuario = sharedPreferences.getString("id_Usuario", "NO HAY DATO");
        boolean sesion = sharedPreferences.getBoolean("sesion", true);
    }

}