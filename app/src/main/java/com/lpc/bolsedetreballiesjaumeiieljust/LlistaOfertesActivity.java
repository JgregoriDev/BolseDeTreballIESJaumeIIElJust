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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class LlistaOfertesActivity extends MenuActivity {

    private Button b_afegir;
    private ListView listView;
    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase bd;
    private CheckBox cb_dam,cb_asix;
    ArrayList<String> list;
    ArrayAdapter adaptador;
    boolean bandera;
    private ArrayList<String> listData;
    private TextView textResultado;

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
        bandera=false;
        cb_dam=(CheckBox)findViewById(R.id.cb_dam);
        cb_asix=(CheckBox)findViewById(R.id.cb_asix);
        String requisits=null;
        if (cb_dam.isChecked() && !cb_asix.isChecked()) {
            requisits = cb_dam.getText().toString();
            bandera=true;
        }
        if (!cb_dam.isChecked() && cb_asix.isChecked()) {
            requisits = cb_asix.getText().toString();
            bandera=true;
        }
        if (cb_dam.isChecked() && cb_asix.isChecked()) {
            requisits = cb_dam.getText().toString() + "+" + cb_asix.getText().toString();
            bandera=true;
        }
        if(bandera){
            if(requisits!=null){
                CargarLVFiltrat(requisits);
            }
        }else{
            CargarLV();
        }

        textResultado= (TextView) findViewById(R.id.tv_llistabd);
        sqLiteHelper=new SQLiteHelper(getApplicationContext());
        bd=sqLiteHelper.getWritableDatabase();
        //listView = (ListView) findViewById(R.id.listView);
//        Cargar();



    }

    private void CargarLV(){

        list=sqLiteHelper.cargarLv();
        if(list!=null){
            adaptador=new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,list);
            listView.setAdapter(adaptador);
        }else{
            Log.d("ListView error","Es null");
        }

    }
    private void CargarLVFiltrat(String requisits){

        list=sqLiteHelper.cargarLv(requisits);
        if(list!=null){
            adaptador=new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,list);
            listView.setAdapter(adaptador);
        }else{
            Log.d("ListView error","Es null");
        }
    }
  /*  private void Cargar(){
        String sql="SELECT * FROM Ofertes";
        Cursor c = bd.rawQuery(sql, null);

        textResultado.setText("");
        if (c.moveToFirst()) {
            do {
                String id=c.getString(0);
                String nom=c.getString(1);
                String direccio=c.getString(2);
                textResultado.append(id+nom+direccio+"\n");
                Log.d("Proba","TextView Cargado");
            }while (c.moveToNext());
        }
    }*/

}
