package com.example.studente.appcucinaproject.Ricetta;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studente.appcucinaproject.Cards.RicettaDetails;
import com.example.studente.appcucinaproject.R;
import com.example.studente.appcucinaproject.Timer.Timer;

public class Ricetta extends AppCompatActivity {
    ImageView imageView;
    TextView txtname;

    private String nomeRicetta = "";
    private String tempoRicettaFromDatabase = "";
    private String secondiRicettaFromDatabase = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ricetta_act);

        imageView = (ImageView) findViewById(R.id.d_ricetta_image);
        txtname = (TextView) findViewById(R.id.d_ricetta_title);

        //prendo le info dall'intent
        imageView.setImageResource(getIntent().getIntExtra("img_id", 00));
        txtname.setText("Name:" + getIntent().getStringExtra("name"));

        setTitle(getIntent().getStringExtra("name"));

        nomeRicetta =  getIntent().getStringExtra("name");

        //tempoRicettaFromDatabase = ..... LEGGERE MINUTI DA DATABASE

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(ctx, Timer.class);     //  Intent intent = new Intent(a, Ricetta.class);
                intent.putExtra("img_id",ricetta.getImageRicetta());
                intent.putExtra("name",ricetta.getTitle());
                ctx.startActivity(intent); //starta l'activity- M - a.startActivity(...);*/

                Intent intentToTimer = new Intent(getApplicationContext(), Timer.class);
                intentToTimer.putExtra("name", nomeRicetta);
                //intentToTimer.putExtra("time", tempoRicettaFromDatabase);
                intentToTimer.putExtra("time", 1);
                intentToTimer.putExtra("second", secondiRicettaFromDatabase);

                //startActivityForResult(intentToTimer, 123);
                startActivity(intentToTimer);
            }

        });

    }
}
