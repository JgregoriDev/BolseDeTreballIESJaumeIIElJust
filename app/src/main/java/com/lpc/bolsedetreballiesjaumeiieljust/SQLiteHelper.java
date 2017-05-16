package com.lpc.bolsedetreballiesjaumeiieljust;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lpc.bolsedetreballiesjaumeiieljust.Entitat.OfertesTreball;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lpc on 25/04/17.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "OfertesTreball";
    private static final int DATABASE_VERSION = 1;
    private ArrayList<OfertesTreball>ofertesTreballs;
    private String TAG = "SQL";
    public static final String NomTabla = "OfertesTreball";
    public static final String Codi = "Codi";
    public static final String Nom = "Nom";
    public static final String Poblacio = "Poblacio";
    public static final String Email = "Email";
    public static final String Cicle = "Cicle";
    public static final String DataNotificacio = "DataNotificacio";
    public static final String Telefon = "Telefon";
    public static final String Descripcio = "Descripcio";
    String sql = "CREATE TABLE " + NomTabla + "(" +
            Codi + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Nom + " TEXT," +
            Email + " TEXT," +
            Telefon + " TEXT," +
            Poblacio + " TEXT," +
            Cicle + " TEXT," +
            DataNotificacio + " TEXT," +
            Descripcio + " TEXT" +
            ");";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        ofertesTreballs=new ArrayList<>();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
        Log.d(TAG, "Proba Creació " + sql);

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
        registro.put(Telefon, ot.getTelefono());
        registro.put(Cicle, ot.getCicle());
        registro.put(DataNotificacio, ot.getDataNotificacio());
        registro.put(Descripcio, ot.getDescripcio());

        bd.insert(NomTabla, null, registro);
        Log.d("Jack", "Insert fet de manera correcta en SQLITE Nom" + ot.getNom() + " Email" + ot.getEmail() + "Telefono" + ot.getTelefono() + " Poblacio" + ot.getPoblacio() + " Cicle" + ot.getCicle() + " Descripcio" + ot.getDescripcio());
//        Log.d(TAG, "Insert fet de manera correcta en SQLITE");
//        InsertarValorsFirebase(ot);
    }

    public ArrayList<String> cargarLv(String Condicio, ArrayList<OfertesTreball> lo) {
        ArrayList<String> llista = new ArrayList<>();
        String sql = "SELECT * FROM " + NomTabla + Condicio;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            do {
                int codi = c.getInt(0);
                String nom = c.getString(1);
                String Email = c.getString(2);
                String Telefono = c.getString(3);
                String Poblacio = c.getString(4);
                String Cicle = c.getString(5);
                String Data = c.getString(6);
                String Descripcio = c.getString(7);
                OfertesTreball ot = new OfertesTreball(nom, Poblacio, Email, Cicle, Data, Descripcio, Telefon);
                ot.setCodi(codi);
                ot.setTelefono(Telefono);
                llista.add(ot.getDataNotificacio()+" Codi:" + ot.getCodi() +""+ "\nNom empresa:" + ot.getNom() + "\nPoblacio:" + ot.getPoblacio());
                ofertesTreballs.add(ot);
                /*llista.add(ot.getCodi() + " " + ot.getNom() + " " + ot.getEmail() + " " + ot.getTelefono() +
                        " " + ot.getPoblacio() + " " + ot.getCicle() + " " + ot.getDataNotificacio() +
                        " " + ot.getDescripcio());*/

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

    public int ObtindreCodi(String nom) {
        String sql = "SELECT Codi FROM " + NomTabla + " WHERE " + Nom + "='" + nom + "';";
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(sql, null);
        return c.getInt(1);
    }

    private void InsertarValorsFirebase(OfertesTreball ot) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("OfertaTreball");
        HashMap<String, String> hashMap = new HashMap<>();
//        String codi = String.valueOf(ot.getCodi());
        hashMap.put(Nom, ot.getNom());
        hashMap.put(Telefon, ot.getTelefono());
        hashMap.put(Poblacio, ot.getPoblacio());
        hashMap.put(Cicle, ot.getCicle());
        hashMap.put(DataNotificacio, ot.getCicle());
        hashMap.put(Descripcio, ot.getDescripcio());
        databaseReference.push().setValue(hashMap);
        Log.d("SQL", "Inserció a Firebase Exitosa");
    }

    private void LlegirValorsFirebase(OfertesTreball ot) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("OfertaTreball");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String valor = dataSnapshot.getValue(String.class);

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

    public void BorrarRegistre(int codi) {
        String sql = "DELETE FROM " + NomTabla + " WHERE " + Codi + "='" + codi+ "';";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }
    public ArrayList<OfertesTreball> getOfertesTreballs() {
        return ofertesTreballs;
    }


}
