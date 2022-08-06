package com.example.domilaap.Repartidor.Menu_repartidor;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.domilaap.R;
import com.example.domilaap.Repartidor.Fragments.home_repartidor_fragment;
import com.example.domilaap.Repartidor.Fragments.perfil_repartidor_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Repartidor_menu extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repartidor_menu);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        showSelectedFragment(new home_repartidor_fragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.notificacion){
                    showSelectedFragment(new home_repartidor_fragment());
                }
                if (menuItem.getItemId() == R.id.menu_profile) {
                    showSelectedFragment(new perfil_repartidor_fragment());
                }
                return true;
            }
        });


    }

    private void showSelectedFragment(Fragment fragment){
  //      Bundle args = new Bundle();
  //      args.putString("nombre", nombreproducto);  // guardar el dato como bundle NO como String
  //      args.putString("precio", precioproducto);

  //      fragment.setArguments(args);
        // el container es donde se muestra el contenido de los botones del menu
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)// ME FALTA A COMODAR EL ADD PARA QUE FUNCIONES BIEN
                .commit();
     /*   FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();*/
    }



}
