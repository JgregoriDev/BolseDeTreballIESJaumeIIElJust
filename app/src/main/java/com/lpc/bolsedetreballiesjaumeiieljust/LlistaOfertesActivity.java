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
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lpc.bolsedetreballiesjaumeiieljust.Entitat.OfertesTreball;

import java.util.ArrayList;

public class LlistaOfertesActivity extends MenuActivity {

    private ListView listView;
    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase bd;
    private CheckBox cb_dam,cb_asix;
    private ArrayList<OfertesTreball> list;
    private ArrayAdapter adaptador;
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
        listView = (ListView) findViewById(R.id.listView);
        cb_dam=(CheckBox)findViewById(R.id.cb_dam);
        cb_asix=(CheckBox)findViewById(R.id.cb_asix);
        String requisits=null;
        sqLiteHelper=new SQLiteHelper(getApplicationContext());
        //CargarLV();
//        if (cb_dam.isChecked() && !cb_asix.isChecked()) {
//            requisits = cb_dam.getText().toString();
//            bandera=true;
//        }
//        if (!cb_dam.isChecked() && cb_asix.isChecked()) {
//            requisits = cb_asix.getText().toString();
//            bandera=true;
//        }
//        if (cb_dam.isChecked() && cb_asix.isChecked()) {
//            requisits = cb_dam.getText().toString() + "+" + cb_asix.getText().toString();
//            bandera=true;
//        }
//        if(bandera){
//            if(requisits!=null){
//                CargarLVFiltrat(requisits);
//            }
//        }else{
//            CargarLV();
//        }

        //CargarLV();
        //textResultado= (TextView) findViewById(R.id.tv_llistabd);

        //bd=sqLiteHelper.getWritableDatabase();
//        Cargar();



    }

    private void CargarLV(){
        list=sqLiteHelper.cargarLv();
//        for (OfertesTreball ot:list) {
//            Log.d("Número ",""+ot.getNom());
//        }
//        if(!list.isEmpty()){
            //adaptador=new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,list);
            //listView.setAdapter(adaptador);
            //Toast.makeText(this,"Rellenando campos",Toast.LENGTH_SHORT).show();
//        }else{
//            Log.d("ListView error","Es null");
//        }

    }
//    private void CargarLVFiltrat(String requisits){
//
//        list=sqLiteHelper.cargarLv(requisits);
//        if(list!=null){
//            adaptador=new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,list);
//            listView.setAdapter(adaptador);
//        }else{
//            Log.d("ListView error","Es null");
//        }
//    }


}
