package com.lpc.bolsedetreballiesjaumeiieljust;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lpc.bolsedetreballiesjaumeiieljust.Entitat.OfertesTreball;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends MenuActivity {
    //    TextView text;
    private OfertesTreball ot;
    private SQLiteHelper sqLiteHelper;
    private boolean Notificacio = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        String conf = "";
        sqLiteHelper = new SQLiteHelper(getApplicationContext());
        if (getIntent().getExtras() != null) {
            String Nom = null;
            String Email = null;
            String Poblacio = null;
            String Telefono = null;
            String Cicle = null;
            String Data = null;
            String Descripcio = null;
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                if (key.equals("Nom")) {
//                         Log.d("Jack", "Key: " + key + " Value: " + value);
                    Nom = (String) value;
                }
                if (key.equals("Email")) {
//                    Log.d("Jack", "Key: " + key + " Value: " + value);
                    Email = (String) value;
                }
                if (key.equals("Poblacio")) {
//                    Log.d("Jack", "Key: " + key + " Value: " + value);
                    Poblacio = (String) value;
                }
                if (key.equals("Telefono")) {
//                    Log.d("Jack", "Key: " + key + " Value: " + value);
                    Telefono = (String) value;
                }
                if (key.equals("Cicle") || key.equals("Curs")) {
//                    Log.d("Jack", "Key: " + key + " Value: " + value);
                    Cicle = (String) value;
                }
                if (key.equals("Dia") || key.equals("Data")) {
//                    Log.d("Jack", "Key: " + key + " Value: " + value);
                    Data = (String) value;
                }
                if (key.equals("Descripcio")) {
//                    Log.d("Jack", "Key: " + key + " Value: " + value);
                    Descripcio = (String) value;
                }

            }
            if (Nom != null) {
                if (Data == null) {
                    Date date = Calendar.getInstance().getTime();
                    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Data = formatter.format(date);
                }
                ot = new OfertesTreball(Nom, Poblacio, Email, Cicle, Data, Descripcio, Telefono);
//                Log.d("Jack",ot.getNom()+ot.getDescripcio());
            }
        }
        if (ot != null) {
            sqLiteHelper.Insertar(ot);
            startActivity(new Intent(MainActivity.this, LlistaOfertesActivity.class));
        }
    }
}



