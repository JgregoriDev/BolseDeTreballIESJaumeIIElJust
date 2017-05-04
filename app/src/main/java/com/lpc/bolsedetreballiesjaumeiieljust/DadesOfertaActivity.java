package com.lpc.bolsedetreballiesjaumeiieljust;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DadesOfertaActivity extends MenuActivity {
    TextView tv_nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        tv_nom = (TextView) findViewById(R.id.tv_nom);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String Activity = bundle.getString("Activity");
            if (Activity.equals("MainActivity.class")) {
                String bundleString = bundle.getString("Nom");
                String []pba=bundleString.split("_");

                for (String pb:pba){
                    tv_nom.append("\n"+pb);
                }

            } else if(Activity.equals("LlistaOfertesActivity.class")) {

                String bundleString = bundle.getString("Nom");
                String[] parts = bundleString.split(" ");

                String codi = parts[0];
                String nom = parts[1];
                String Email = parts[2];
                String Telefono = parts[3];
                String Poblacio = parts[4];
                String Cicle = parts[5];
                String Descripcio = parts[6];
                String Data = parts[7];

                tv_nom.setText(codi + " " + nom + " " + " " + Email + " " + Telefono + " " + Poblacio + " " + Cicle + " " + Descripcio + " " + Data);
            }else{
                tv_nom.setText("No hi ha informació");
            }
        } else {
            Toast.makeText(this, "No hay información", Toast.LENGTH_SHORT).show();
        }
//
    }


}
