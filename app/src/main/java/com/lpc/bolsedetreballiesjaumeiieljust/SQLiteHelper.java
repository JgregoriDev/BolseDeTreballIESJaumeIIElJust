package com.lpc.bolsedetreballiesjaumeiieljust;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by lpc on 25/04/17.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Ofertes";
    private static final int DATABASE_VERSION = 4;


    public static final String Ofertes = "Ofertes";
    public static final String Nom = "Nom";
    public static final String Poblacio = "Poblacio";
    public static final String Email = "Email";
    public static final String Requerirements = "Requerirements";
    public static final String DataNotificacio = "DataNotificacio";
    public static final String Descripcio = "Descripcio";
    String sql = "CREATE TABLE " + Ofertes
            + "(" + Nom + " TEXT," +
            Email + " TEXT," +
            Poblacio + " TEXT," +
            Requerirements + " TEXT," +
            DataNotificacio + "TEXT," +
            Descripcio + " TEXT" +
            ")";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
        Log.d("Proba", "Proba inserció " + sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Ofertes);
        db.execSQL(sql);
        Log.d("Proba", "Proba actualizació " + sql);
    }

    public void Insertar(String nom, String email, String poblacio, String requeriments, String dataNotificacio, String descripcio) {
        SQLiteDatabase bd = getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put(Nom, nom);
        registro.put(Email, email);
        registro.put(Poblacio, poblacio);
        registro.put(Requerirements, requeriments);


        registro.put(DataNotificacio, dataNotificacio);
        registro.put(Descripcio, Descripcio);

        bd.insert(Ofertes, null, registro);
        Log.d("Proba Insert", "Insert fet de manera correcta");
//        Toast.makeText(context, "Has guardat el contingut", Toast.LENGTH_LONG).show();
    }
    public ArrayList<String> cargarLv() {
        ArrayList<String>lista=new ArrayList<>();
        String sql="SELECT * FROM "+Ofertes;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(sql, null);


        if (c.moveToFirst()) {
            do {
                String nom=c.getString(0);
                String descripcio=c.getString(5);

                lista.add(nom+"_"+descripcio);
                //textResultado.append(id+" "+nom+" "+direccio+"\n");
                Log.d("SELECT",""+nom+" "+descripcio);
            }while (c.moveToNext());
        }
        if(lista.isEmpty()){
            return null;
        }else{
            return lista;
        }

    }
    public ArrayList<String> cargarLv(String Requisits) {
        ArrayList<String>lista=new ArrayList<>();
        String sql="SELECT * FROM "+Ofertes+" WHERE "+Requerirements+"="+Requisits;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(sql, null);


        if (c.moveToFirst()) {
            do {
                String nom=c.getString(0);
                String descripcio=c.getString(5);

                lista.add(nom+"_"+descripcio);
                //textResultado.append(id+" "+nom+" "+direccio+"\n");
                Log.d("SELECT",""+nom+" "+descripcio);
            }while (c.moveToNext());
        }
        if(lista.isEmpty()){
            return null;
        }else{
            return lista;
        }

    }

}
