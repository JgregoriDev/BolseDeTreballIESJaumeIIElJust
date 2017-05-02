package com.lpc.bolsedetreballiesjaumeiieljust;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lpc.bolsedetreballiesjaumeiieljust.Entitat.OfertesTreball;

import java.util.ArrayList;

/**
 * Created by lpc on 25/04/17.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "OfertesTreball";
    private static final int DATABASE_VERSION = 1;

    private String TAG = "SQL";
    public static final String NomTabla = "OfertesTreball";
    public static final String Codi = "Codi";
    public static final String Nom = "Nom";
    public static final String Poblacio = "Poblacio";
    public static final String Email = "Email";
    public static final String Cicle = "Cicle";
    public static final String DataNotificacio = "DataNotificacio";
    public static final String Descripcio = "Descripcio";
    String sql = "CREATE TABLE " + NomTabla + "(" +
            Codi + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Nom + " TEXT," +
            Email + " TEXT," +
            Poblacio + " TEXT," +
            Cicle + " TEXT," +
            //DataNotificacio + "TEXT," +
            Descripcio + " TEXT" +
            ");";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
        Log.d(TAG, "Proba Creació " + sql);
//        db.execSQL("INSERT INTO "+NomTabla+"("+Nom+") VALUES ('Ramon');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NomTabla);
        db.execSQL(sql);
        Log.d(TAG, "Proba actualizació " + sql);
    }

    public void Insertar(OfertesTreball ot) {
        SQLiteDatabase bd = getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put(Nom, ot.getNom());
        registro.put(Email, ot.getEmail());
        registro.put(Poblacio, ot.getPoblacio());
        registro.put(Cicle, ot.getCicle());
        //registro.put(DataNotificacio, ot.getDescripcio());
        registro.put(Descripcio, ot.getDescripcio());

        bd.insert(NomTabla, null, registro);
        Log.d(TAG, "Insert fet de manera correcta");
    }

    public ArrayList<String> cargarLv(String Condicio) {
        ArrayList<String> llista = new ArrayList<>();
        String sql = "SELECT * FROM " + NomTabla + Condicio;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            do {
                int codi=c.getInt(0);
                String nom = c.getString(1);
                String Email = c.getString(2);
                String Poblacio = c.getString(3);
                String Cicle = c.getString(4);
                //String Data = c.getString(5);
                String Descripcio = c.getString(5);
                OfertesTreball ot = new OfertesTreball(nom, Email, Poblacio, Cicle ,Descripcio);
                ot.setCodi(codi);
                llista.add(ot.getCodi()+" "+ot.getNom()+" "+ot.getPoblacio()+" "+ot.getCicle()+" "+ot.getDescripcio());
            } while (c.moveToNext());
        }
        if (llista.isEmpty()) {
            Log.d(TAG, "Llista esta buit");
            return null;

        } else {
            Log.d(TAG, "Llista satisfactoria");
            return llista;
        }
    }

}
