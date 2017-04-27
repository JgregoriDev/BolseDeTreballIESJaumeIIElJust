package com.lpc.bolsedetreballiesjaumeiieljust;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import static com.lpc.bolsedetreballiesjaumeiieljust.SQLiteHelper.DataNotificacio;
import static com.lpc.bolsedetreballiesjaumeiieljust.SQLiteHelper.Descripcio;
import static com.lpc.bolsedetreballiesjaumeiieljust.SQLiteHelper.Email;
import static com.lpc.bolsedetreballiesjaumeiieljust.SQLiteHelper.Nom;
import static com.lpc.bolsedetreballiesjaumeiieljust.SQLiteHelper.Ofertes;
import static com.lpc.bolsedetreballiesjaumeiieljust.SQLiteHelper.Poblacio;
import static com.lpc.bolsedetreballiesjaumeiieljust.SQLiteHelper.Requerirements;


public class InsertarActivity extends MenuActivity {
    private Button b_guardar, b_cancelar;
    private EditText ed_nom, ed_email, ed_telefono, ed_Descripcio, ed_data, ed_poblacio;
    private CheckBox cb_asix, cb_dam;
    private SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
        sqLiteHelper = new SQLiteHelper(this);
        b_cancelar = (Button) findViewById(R.id.b_cancelar);
        b_guardar = (Button) findViewById(R.id.b_guardar);
        ed_nom = (EditText) findViewById(R.id.et_nom);
        ed_poblacio = (EditText) findViewById(R.id.et_Poblacio);
        ed_email = (EditText) findViewById(R.id.et_email);
        ed_telefono = (EditText) findViewById(R.id.et_telefono);
        ed_Descripcio = (EditText) findViewById(R.id.et_descripcio);
        ed_data = (EditText) findViewById(R.id.et_data);
        cb_dam = (CheckBox) findViewById(R.id.cb_dam);
        cb_asix = (CheckBox) findViewById(R.id.cb_asix);
        b_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guardar();
            }
        });
        b_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cancelar();
            }
        });
    }

    private void Cancelar() {
        Toast.makeText(getApplicationContext(), "Has cancelat l'inserció", Toast.LENGTH_LONG).show();
        startActivity(new Intent(InsertarActivity.this, LlistaOfertesActivity.class));
    }

    private void Guardar() {
        //Control de Checkbox
        if (!cb_dam.isChecked() && !cb_asix.isChecked()) {
            Toast.makeText(getApplicationContext(), "Has de seleccionar si vols de dam, de asix o dels dos", Toast.LENGTH_SHORT).show();
        } else {
            SQLiteDatabase bd = sqLiteHelper.getWritableDatabase();
            ContentValues registro = new ContentValues();
            registro.put(Poblacio, ed_poblacio.getText().toString());
            String requisits = "";
            //Modificar el contingut del checkbox segons el que tinga seleccionat
            if (cb_dam.isChecked() && !cb_asix.isChecked()) {
                requisits = cb_dam.getText().toString();
            }
            if (!cb_dam.isChecked() && cb_asix.isChecked()) {
                requisits = cb_asix.getText().toString();
            }
            if (cb_dam.isChecked() && cb_asix.isChecked()) {
                requisits = cb_dam.getText().toString() + "+" + cb_asix.getText().toString();
            }

            Insertar(ed_nom.getText().toString(),
                    ed_email.getText().toString(), ed_poblacio.getText().toString(),
                    requisits,
                    ed_data.getText().toString(), ed_Descripcio.getText().toString());
            //Log.d("Proba Insert", "Insert fet de manera correcta");
            Toast.makeText(getApplicationContext(), "Has guardat el contingut", Toast.LENGTH_LONG).show();

            startActivity(new Intent(InsertarActivity.this, LlistaOfertesActivity.class));
        }
    }
    public void Insertar(String nom, String email, String poblacio, String requeriments, String dataNotificacio, String descripcio) {
        SQLiteDatabase bd = sqLiteHelper.getWritableDatabase();
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

}
