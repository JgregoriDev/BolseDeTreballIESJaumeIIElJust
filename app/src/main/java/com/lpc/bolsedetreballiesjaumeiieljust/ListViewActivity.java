package com.lpc.bolsedetreballiesjaumeiieljust;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ListViewActivity extends MenuActivity {
    TextView tv_nom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
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
        tv_nom=(TextView)findViewById(R.id.tv_nom);
        Bundle bundle=getIntent().getExtras();
        String bundleString=bundle.getString("Nom");
        String[]parts=bundleString.split(" ");
        String codi=parts[0];
        String nom=parts[1];
        String Email=parts[2];
        String Cicle=parts[3];
        String Descripcio=parts[4];

            tv_nom.setText(codi+" "+nom+" "+" "+Email+" "+Cicle+" "+Descripcio);
//        for (int i=0;i<parts.length;i++) {
//            tv_nom.append(parts[i]+" ");
//        }
    }

}
