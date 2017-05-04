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

import java.util.ArrayList;

public class LlistaOfertesActivity extends MenuActivity {

    private ListView listView;
    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase bd;
    private Button b_clear;
    private CheckBox cb_dam,cb_asix;
//    private ArrayList<OfertesTreball> llista;
    private ArrayList<String> llista;
    private ArrayAdapter adaptador;
    private String condicio="";
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


        sqLiteHelper=new SQLiteHelper(getApplicationContext());
        listView = (ListView) findViewById(R.id.listView);
        cb_dam=(CheckBox)findViewById(R.id.cb_dam);
        cb_asix=(CheckBox)findViewById(R.id.cb_asix);
        b_clear= (Button) findViewById(R.id.b_clear);

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
                Intent intent = new Intent(LlistaOfertesActivity.this, DadesOfertaActivity.class);
                intent.putExtra("Nom", listView.getItemAtPosition(position).toString());
                intent.putExtra("Activity","LlistaOfertesActivity.class");
                startActivity(intent);
            }
        });

    }

    private void OmplirArrayList(){
        if(cb_dam.isChecked() && cb_asix.isChecked()){
            condicio=" WHERE Cicle='DAM+ASIX';";
        }
        else{
            condicio=";";

        }
        if(cb_dam.isChecked() && !cb_asix.isChecked()){
            condicio=" WHERE Cicle='DAM';";
        }
        if(!cb_dam.isChecked() && cb_asix.isChecked()){
            condicio=" WHERE Cicle='ASIX';";
        }
        llista =sqLiteHelper.cargarLv(condicio);
        CarregarLV();
    }
    private void CarregarLV(){
        if (llista==null) {
            Log.d("Arraylist", "No disposes de elements en la llista");
            Toast.makeText(getApplicationContext(),"No disposes de registres en la llista",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,MainActivity.class));
        } else {
            adaptador = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, llista);
            listView.setAdapter(adaptador);
        }

    }

}
