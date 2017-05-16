package com.lpc.bolsedetreballiesjaumeiieljust;

import android.content.Intent;
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
import com.lpc.bolsedetreballiesjaumeiieljust.Entitat.OfertesTreball;

import java.util.ArrayList;

public class LlistaOfertesActivity extends MenuActivity {
    private ArrayList<OfertesTreball> llistaOfertes;
    private ListView listView;
    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase bd;
    private Button b_clear;
    private CheckBox cb_dam, cb_asix;
    private int num;

    //    private ArrayList<OfertesTreball> llista;
    private ArrayList<String> llista;
    private ArrayList<OfertesTreball> llistaOfertesTreballs;
    private ArrayAdapter adaptador;
    private String condicio = "";
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llista__ofertes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




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
                String valors = listView.getItemAtPosition(position).toString();
                String[] arrayValorsOt = new String[3];
                arrayValorsOt = valors.split("\n");
                String codilletres = arrayValorsOt[0];
                String[] codill = new String[2];
                codill = codilletres.split(":");
                int codi = 0;
                codi = Integer.parseInt(codill[1]);

                OfertesTreball ot = BuscarOfertesTreball(codi);
                if (ot != null) {
                    Toast.makeText(getApplicationContext(), "Trobat", Toast.LENGTH_SHORT);
                    Intent intent = new Intent(LlistaOfertesActivity.this, DadesOfertaActivity.class);
                    intent.putExtra("Nom", listView.getItemAtPosition(position).toString());
                    intent.putExtra("OfertesTreball", ot);
                    intent.putExtra("Activity", "LlistaOfertesActivity.class");
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No s'ha trobat", Toast.LENGTH_SHORT);

                }
            }
        });

    }

    private OfertesTreball BuscarOfertesTreball(int num) {
        ArrayList<OfertesTreball> ofertesTreballs = sqLiteHelper.getOfertesTreballs();
//        Log.d("Jack",""+ofertesTreballs.size());
        OfertesTreball ot = null;
        for (OfertesTreball ofertesTreball : ofertesTreballs) {
//            Log.d("Jack",""+ofertesTreball.getCodi()+ofertesTreball.getNom()+ofertesTreball.getDataNotificacio());
            if (num == ofertesTreball.getCodi()) {
                ot = ofertesTreball;
                break;
            }
        }
        if (ot != null) {
//            Log.d("Jack", "trobat");
            return ot;

        } else {
//            Log.d("Jack", "no trobat");
            return null;
        }
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
//            Log.d("Arraylist", "No disposes de elements en la llista");
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
                Log.d("Jack", valor);
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
