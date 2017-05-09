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
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                    Log.d("Jack", "Key: " + key + " Value: " + value);
                    Nom = (String) value;
                }
                if (key.equals("Email")) {
                    Log.d("Jack", "Key: " + key + " Value: " + value);
                    Email = (String) value;
                }
                if (key.equals("Poblacio")) {
                    Log.d("Jack", "Key: " + key + " Value: " + value);
                    Poblacio = (String) value;
                }
                if (key.equals("Telefono")) {
                    Log.d("Jack", "Key: " + key + " Value: " + value);
                    Telefono = (String) value;
                }
                if (key.equals("Cicle")) {
                    Log.d("Jack", "Key: " + key + " Value: " + value);
                    Cicle = (String) value;
                }
                if (key.equals("Dia") || key.equals("Data")) {
                    Log.d("Jack", "Key: " + key + " Value: " + value);
                    Data = (String) value;
                }
                if (key.equals("Descripcio")) {
                    Log.d("Jack", "Key: " + key + " Value: " + value);
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
            }
        }
        if (ot != null) {
            sqLiteHelper.Insertar(ot);
            Intent intent = new Intent(MainActivity.this, LlistaOfertesActivity.class);
            startActivity(intent);
        }


    }



   /* @Override
    public void run() {
        Thread miHilo = Thread.currentThread();
        while (miHilo == thread) {
            try {
                Thread.sleep(1000);
                Log.d("Jack","Durmiendo 1s");
                Toast.makeText(getApplicationContext(),"Durmiendo 1s",Toast.LENGTH_SHORT).show();
            } catch (InterruptedException e) {
            }

        }

    }*/
}



