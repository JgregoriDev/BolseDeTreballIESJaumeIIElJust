package com.lpc.bolsedetreballiesjaumeiieljust;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lpc.bolsedetreballiesjaumeiieljust.Entitat.OfertesTreball;

public class DadesOfertaActivity extends MenuActivity {
    private TextView tv_codi, tv_nom, tv_email, tv_telefon, tv_poblacio, tv_cicle, tv_data, tv_descripcio;


    private String Email;
    private String Telefono;
    private String Poblacio;
    private OfertesTreball ot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dades_oferta);
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
        tv_codi = (TextView) findViewById(R.id.tv_codi);
        tv_nom = (TextView) findViewById(R.id.tv_nom);
        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_telefon = (TextView) findViewById(R.id.tv_telefon);
        tv_poblacio = (TextView) findViewById(R.id.tv_poblacio);
        tv_cicle = (TextView) findViewById(R.id.tv_cicle);
        tv_data = (TextView) findViewById(R.id.tv_data);
        tv_descripcio = (TextView) findViewById(R.id.tv_descripcio);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            final String Activity = bundle.getString("Activity");
            if (Activity.equals("MainActivity.class")) {
              /*  tv_codi.setVisibility(View.INVISIBLE);
                tv_email.setVisibility(View.INVISIBLE);
                tv_telefon.setVisibility(View.INVISIBLE);
                tv_cicle.setVisibility(View.INVISIBLE);
                tv_data.setVisibility(View.INVISIBLE);
                tv_poblacio.setVisibility(View.INVISIBLE);
                tv_descripcio.setVisibility(View.INVISIBLE);
                String bundleString = bundle.getString("Nom");
*/

            } else if (Activity.equals("LlistaOfertesActivity.class")) {
                ot = bundle.getParcelable("OfertesTreball");
                if (ot != null) {
                    if (ot.getNom() != null) {


                        tv_codi.append(": " + ot.getCodi());
                        tv_nom.append(": " + ot.getNom());
                        if (ot.getEmail() != null) {

                            Email = ot.getEmail();
                            if (Email.equals("null")) {

                                tv_email.append(": No ens han donat");

                            }else{
                                tv_email.append(": " + ot.getEmail());
                                tv_email.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        sendMail();
                                    }
                                });
                            }

                        }
                        if (ot.getCicle() != null) {
                            tv_cicle.append(": " + ot.getCicle());

                        }
                        if (ot.getTelefono() != null) {
                            Telefono = ot.getTelefono();
                            if (Telefono.equals("null")) {

                                tv_telefon.append(": No ens han donat");

                            }else{
                                tv_telefon.append(": " + ot.getTelefono());
                                tv_telefon.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        callNumberPhone();
                                    }
                                });
                            }

                        }
                        if (ot.getPoblacio() != null) {
                            Poblacio=ot.getPoblacio();
                            tv_poblacio.append(": " + ot.getPoblacio());
                            tv_poblacio.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    LauchGMap();
                                }
                            });
                        }
                        if (ot.getDescripcio() != null) {
                            tv_descripcio.append("Descripcio: " + ot.getDescripcio());

                        }

                        tv_data.append(": " + ot.getDataNotificacio());

                    }
                }


            } else {
                tv_nom.setText("No hi ha informació");
            }
        } else {
            Toast.makeText(this, "No hay información", Toast.LENGTH_SHORT).show();
        }
//
    }


    public void callNumberPhone() {
        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + ot.getTelefono()));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(i);
    }

    public void sendMail() {
        Intent sendEmail = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", ot.getEmail(), null));
        startActivity(Intent.createChooser(sendEmail, "Send email"));
    }
    public void LauchGMap(){
        Intent launchGMap= new Intent(Intent.ACTION_VIEW);
        getIntent().setData(Uri.parse("geo:"+Poblacio));
        Intent chooser=Intent.createChooser(launchGMap,"Launch maps");
        startActivity(chooser);
    }


}
