package com.lpc.bolsedetreballiesjaumeiieljust;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends MenuActivity {
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        String conf="";
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getApplicationContext());
        text= (TextView) findViewById(R.id.tv);
        text.setText("");
/*
        int n=sqLiteHelper.ObtindreCodi("Jan");
        Toast.makeText(this,""+n,Toast.LENGTH_SHORT).show();
*/
        if(getIntent().getExtras()!=null){
            for (String key:getIntent().getExtras().keySet()){
                String value=getIntent().getExtras().getString(key);

                text.append("\n"+key +":"+value+"_");

            }
            Log.d("TAG", "Notificacion recibida");
            Intent intent = new Intent(MainActivity.this, DadesOfertaActivity.class);
            intent.putExtra("Nom", text.getText().toString());
            Log.i("Activity",conf);
            Toast.makeText(getApplicationContext(),conf,Toast.LENGTH_SHORT).show();
            intent.putExtra("Activity","MainActivity.class");
            startActivity(intent);
        }
    }

}
