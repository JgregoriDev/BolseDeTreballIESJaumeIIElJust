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

import com.lpc.bolsedetreballiesjaumeiieljust.Entitat.OfertesTreball;

import static com.lpc.bolsedetreballiesjaumeiieljust.SQLiteHelper.DataNotificacio;
import static com.lpc.bolsedetreballiesjaumeiieljust.SQLiteHelper.Descripcio;
import static com.lpc.bolsedetreballiesjaumeiieljust.SQLiteHelper.Email;
import static com.lpc.bolsedetreballiesjaumeiieljust.SQLiteHelper.Nom;
import static com.lpc.bolsedetreballiesjaumeiieljust.SQLiteHelper.NomTabla;
import static com.lpc.bolsedetreballiesjaumeiieljust.SQLiteHelper.Poblacio;
import static com.lpc.bolsedetreballiesjaumeiieljust.SQLiteHelper.Cicle;


public class InsertarActivity extends MenuActivity {
    private Button b_guardar, b_cancelar;
    private EditText ed_nom, ed_email, ed_telefono, ed_Descripcio, ed_data, ed_poblacio;
    private CheckBox cb_asix, cb_dam;
    private SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
        sqLiteHelper = new SQLiteHelper(getApplicationContext());
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
            if (ed_nom.length() < 1 || ed_poblacio.length() < 1 || ed_Descripcio.length() < 1 || ed_poblacio.length() < 1 || ed_email.length() < 1) {
                Toast.makeText(getApplicationContext(), "Has de rellenar tots els camps", Toast.LENGTH_SHORT).show();
            } else {
                String cicle = condicions();
                OfertesTreball ofertesTreball = new OfertesTreball(ed_nom.getText().toString(),
                        ed_poblacio.getText().toString(), ed_email.getText().toString(),
                        cicle,
                        ed_data.getText().toString(), ed_Descripcio.getText().toString());
                sqLiteHelper.Insertar(ofertesTreball);

                Toast.makeText(getApplicationContext(), "Has guardat el contingut", Toast.LENGTH_LONG).show();
                startActivity(new Intent(InsertarActivity.this, LlistaOfertesActivity.class));
            }
        }
    }

    private String condicions() {
        String cicle = "";
        //Modificar el contingut del checkbox segons el que tinga seleccionat
        if (cb_dam.isChecked() && !cb_asix.isChecked()) {
            cicle = cb_dam.getText().toString();
        }
        if (!cb_dam.isChecked() && cb_asix.isChecked()) {
            cicle = cb_asix.getText().toString();

        }
        if (cb_dam.isChecked() && cb_asix.isChecked()) {
            cicle = cb_dam.getText().toString() + "+" + cb_asix.getText().toString();
        }
        return cicle;
    }

}
