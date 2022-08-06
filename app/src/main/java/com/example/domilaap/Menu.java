package com.example.domilaap;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.domilaap.fragments.HomeFragment;
import com.example.domilaap.fragments.PedidosFragment;
import com.example.domilaap.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Menu extends AppCompatActivity implements HomeFragment.OnFragmentInterationListener,
                                                       PedidosFragment.OnFragmentInterationListener,
                                                       ProfileFragment.OnFragmentInterationListener{

    BottomNavigationView mbottomNavigationView;
   // String A = "1650";
    String irpedido;
   // String B = "1650";
    String nombreproducto,precioproducto, addCarrito;
    FragmentTransaction transaction;

 //   NotificationBadge mBadge;
 //   int contador=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        showSelectedFragment(new HomeFragment());

    //    mBadge = (NotificationBadge) findViewById(R.id.bagde);

      //  precioProducto = getIntent().getStringExtra("nombre"); // CORREO
        nombreproducto = getIntent().getStringExtra("nombre");
        precioproducto = getIntent().getStringExtra("precio");
      //  int imagen = getIntent().getIntExtra("imagen", 0);
        irpedido = getIntent().getStringExtra("ir");

        // Agrega al carrito de compra
        addCarrito = getIntent().getStringExtra("addCarrito");
        // Agrega al carrito de compra

      /*   Bundle bundle = getIntent().getExtras();
         nombreproducto = bundle.getString("nombre");*/


 //       notificationNumberContainer.setVisibility(View.GONE);   CARDVIEW
 //       notificationNumber.setVisibility(View.GONE);  TEXTVIEW

        if (nombreproducto != null && precioproducto != null) {
            //@drawable/ic_location_on_black_24dp
            showSelectedFragment(new PedidosFragment());
            //showSelectedFragment(new PedidosFragment());
            //showFragment(new PedidosFragment());
           /* FragmentManager fm = getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.container, newFrament); //donde fragmentContainer_id es el ID del FrameLayout donde tu Fragment está contenido.
            fragmentTransaction.commit();*/
        }

        if (addCarrito != null){
            showSelectedFragment(new HomeFragment());
        }

        //PedidosFragment pedidosFragment = new PedidosFragment();
            /*Intent miIntent = new Intent(Menu.this, PedidosFragment.class);
              Bundle bundle = new Bundle();
              bundle.putString("precio", A);
              miIntent.putExtras(bundle);
              startActivity(miIntent);*/
              //pedidosFragment.setArguments(bundle);


        if (irpedido != null) { // esto es una variable del fragment PERFIL para poder ir al fragment PEDIDOS
            showSelectedFragment(new PedidosFragment());
        }

        mbottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);

   /*     BadgeDrawable Pedidos = mbottomNavigationView.getOrCreateBadge(R.id.menu_pedidos);
        Pedidos.setBackgroundColor(Color.BLUE);
        Pedidos.setBadgeTextColor(Color.YELLOW);
        Pedidos.setMaxCharacterCount(5);
        Pedidos.setNumber(505);
        Pedidos.setVisible(true);*/

        mbottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

               /*switch (menuItem.getItemId()){
                   case R.id.menu_home:
                       showSelectedFragment(new HomeFragment());
                   break;
                   case R.id.menu_pedidos:
                       showSelectedFragment(new PedidosFragment());
                       break;
                   case R.id.menu_profile:
                       showSelectedFragment(new ProfileFragment());
                       break;
               }*/

                if (menuItem.getItemId() == R.id.menu_home){
                    showSelectedFragment(new HomeFragment());
                }
                if (menuItem.getItemId() == R.id.menu_pedidos){
                    showSelectedFragment(new PedidosFragment());
 //                   notificationNumberContainer.setVisibility(View.GONE);  CARDVIEW
 //                   notificationNumber.setVisibility(View.GONE);   TEXTVIEW
//                    msn.setVisibility(View.GONE);

                //    BadgeDrawable Pedidos = mbottomNavigationView.getOrCreateBadge(R.id.menu_pedidos);
                //    Pedidos.clearNumber();
                //    Pedidos.setVisible(false);

                    //BadgeDrawable badgeDrawable = (BadgeDrawable) mbottomNavigationView.getOrCreateBadge(menuItem.getItemId());

                  /*  badgeDrawable.setVisible(true);
                    BadgeDrawable badgeDrawable = (BadgeDrawable) mbottomNavigationView.getOrCreateBadge(R.id.menu_pedidos);
                    badgeDrawable.setBackgroundColor(
                            ContextCompat.getColor(getApplicationContext(), R.color.colorAccent)
                    );
                    badgeDrawable.setVisible(true);
                    badgeDrawable.setNumber(50);*/
                }
                if (menuItem.getItemId() == R.id.menu_profile){
                    showSelectedFragment(new ProfileFragment());
                }
                return false;
            }
        });
    } // ONCREATE
    /*
     * METODO QUE PERMITE ELEJIR EL FRAGMENT
     */
    private void showSelectedFragment(Fragment fragment){
        Bundle args = new Bundle();
        args.putString("nombre", nombreproducto);  // guardar el dato como bundle NO como String
        args.putString("precio", precioproducto);

        fragment.setArguments(args);
        // el container es donde se muestra el contenido de los botones del menu
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)// ME FALTA A COMODAR EL ADD PARA QUE FUNCIONES BIEN
                .commit();
     /*   FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();*/
    }

    private void showFragment(Fragment fragment){

        /*FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();*/
        /*Intent intent = new Intent(Menu.this,  PedidosFragment.class);
        //getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment);
        startActivity(intent);*/
    }
}