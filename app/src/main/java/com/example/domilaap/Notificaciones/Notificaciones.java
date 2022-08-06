package com.example.domilaap.Notificaciones;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.domilaap.R;

public class Notificaciones {

    TextView notificationNumber;
    private final int Maximo_Numero = 99;
    int notificacion_numero_counter = 1;
    private ConstraintLayout msn;
    private CardView notificationNumberContainer;

    public  Notificaciones(View view){
        notificationNumber = view.findViewById(R.id.notificationNumber); // TEXTVIEW
        notificationNumberContainer = view.findViewById(R.id.notificationNumberContainer); // CardView
//        msn = view.findViewById(R.id.msn); //Layout esta en gone
    }

    public void Notificacion(){
     //   notificationNumberContainer.setVisibility(View.VISIBLE); //CARDVIEW
    //    notificationNumber.setVisibility(View.VISIBLE); //  TEXTVIEW
        msn.setVisibility(View.VISIBLE);
        notificacion_numero_counter++;
        if (notificacion_numero_counter > Maximo_Numero){
            Log.d("Counter", "Maximum Number Reached!");
        } else{
            notificationNumber.setText(String.valueOf(notificacion_numero_counter));
            //   notificationNumber.setText(notificacion_numero_counter++);
        }
    }


}
