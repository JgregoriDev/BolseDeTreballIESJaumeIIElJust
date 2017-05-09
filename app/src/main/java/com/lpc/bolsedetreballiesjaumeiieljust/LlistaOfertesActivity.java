package com.lpc.bolsedetreballiesjaumeiieljust;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lpc.bolsedetreballiesjaumeiieljust.Entitat.ArrayOfertesTreballs;
import com.lpc.bolsedetreballiesjaumeiieljust.Entitat.OfertesTreball;

import java.io.Serializable;
import java.util.ArrayList;

public class LlistaOfertesActivity extends MenuActivity {
    private ArrayList<OfertesTreball> llistaOfertes;
    private ListView listView;
    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase bd;
    private Button b_clear;
    private CheckBox cb_dam, cb_asix;
    private int num;
    private ArrayOfertesTreballs aot;
    //    private ArrayList<OfertesTreball> llista;
    private ArrayList<String> llista;
    private ArrayAdapter adaptador;
    private String condicio = "";
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llista__ofertes);
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


        aot = new ArrayOfertesTreballs();
        llistaOfertes = aot.getOfertesTreballs();
        sqLiteHelper = new SQLiteHelper(getApplicationContext());
        listView = (ListView) findViewById(R.id.listView);
        cb_dam = (CheckBox) findViewById(R.id.cb_dam);
        cb_asix = (CheckBox) findViewById(R.id.cb_asix);
        b_clear = (Button) findViewById(R.id.b_clear);

        OmplirArrayList();
        b_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb_dam.setChecked(false);
                cb_asix.setChecked(false);
                OmplirArrayList();
            }
        });
        cb_dam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OmplirArrayList();
            }
        });
        cb_asix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OmplirArrayList();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(getApplicationContext(),""+position,Toast.LENGTH_SHORT).show();
                String n = listView.getItemAtPosition(position).toString();
                String[] arrayValorsOt = new String[8];
                arrayValorsOt = n.split("_");

                int codi = Integer.parseInt(arrayValorsOt[0]);
                String nom = arrayValorsOt[1];
                String email = arrayValorsOt[2];
                String telefono = arrayValorsOt[3];
                String poblacio = arrayValorsOt[4];
                String cicle = arrayValorsOt[5];
                String data = arrayValorsOt[6];
                String descripcio = arrayValorsOt[7];
                OfertesTreball ot = new OfertesTreball(nom, poblacio, email, cicle, data, descripcio, telefono);
                ot.setCodi(codi);

                Toast.makeText(getApplicationContext(),"Codi:"+codi+" Nom:"+nom+" Poblacio:"+poblacio+" Telefono:"+telefono+" Curs:"+cicle+" Data:"+data+" Descripcio:"+descripcio,Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "Codi:" + ot.getCodi() + " Nom:" + ot.getNom() + " Poblacio:" + ot.getPoblacio() + " Telefono:" + ot.getTelefono() + " Curs:" + ot.getCicle() + " Data:" + ot.getDataNotificacio() + " Descripcio:" + ot.getDescripcio(), Toast.LENGTH_SHORT).show();

                if (ot == null) {
                    Toast.makeText(getApplicationContext(), "Usuari incorrecte", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(LlistaOfertesActivity.this, DadesOfertaActivity.class);
                    intent.putExtra("Nom", listView.getItemAtPosition(position).toString());
                    intent.putExtra("OfertesTreball", ot);
                    intent.putExtra("Activity", "LlistaOfertesActivity.class");
                    startActivity(intent);
                }
            }
        });

    }

    private void OmplirArrayList() {
        if (cb_dam.isChecked() && cb_asix.isChecked()) {

            condicio = " WHERE Cicle='DAM+ASIX' ORDER BY Codi DESC;";

        } else {
            condicio = " ORDER BY Codi DESC;";

        }
        if (cb_dam.isChecked() && !cb_asix.isChecked()) {
            condicio = " WHERE Cicle='DAM' ORDER BY Codi DESC;";
        }
        if (!cb_dam.isChecked() && cb_asix.isChecked()) {
            condicio = " WHERE Cicle='ASIX' ORDER BY Codi DESC;";
        }
        llista = sqLiteHelper.cargarLv(condicio, llistaOfertes);
        CarregarLV();
    }

    private void CarregarLV() {

        if (llista == null) {
            Log.d("Arraylist", "No disposes de elements en la llista");
            Toast.makeText(getApplicationContext(), "No disposes de registres en la llista", Toast.LENGTH_SHORT).show();
            listView.setAdapter(null);
        } else {
            adaptador = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, llista);
            listView.setAdapter(adaptador);
           
        }
    }

    private void LlegirValorsFirebase() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("-KjZyP6e8VuvFIYlosQx");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String valor = dataSnapshot.getValue(String.class);
                Log.d("Jack",valor);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
