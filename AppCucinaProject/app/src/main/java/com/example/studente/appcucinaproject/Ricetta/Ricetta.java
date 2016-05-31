package com.example.studente.appcucinaproject.Ricetta;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studente.appcucinaproject.R;
import com.example.studente.appcucinaproject.Timer.Timer;
import com.example.studente.appcucinaproject.Timer.TimerOverActivity;

public class Ricetta extends AppCompatActivity {
    ImageView imageView;
    TextView txtname;
    ImageButton preferito;
    ImageButton timer;
    ImageButton spesa;
    ImageButton menu_gg;

    private Intent setTimeFromRicetta;
    private String nameRicetta = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ricetta_act);

        imageView = (ImageView) findViewById(R.id.d_ricetta_image);
        txtname = (TextView) findViewById(R.id.d_ricetta_title);

        preferito = (ImageButton) findViewById(R.id.imageButton2);
        timer = (ImageButton) findViewById(R.id.imageButton4);
        spesa = (ImageButton) findViewById(R.id.imageButton3);
        menu_gg = (ImageButton) findViewById(R.id.imageButton5);


        //prendo le info dall'intent
        imageView.setImageResource(getIntent().getIntExtra("img_id",00));
        txtname.setText("Name:"+ getIntent().getStringExtra("name"));

        setTitle(getIntent().getStringExtra("name"));

        nameRicetta = getIntent().getStringExtra("name");

        preferito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(preferito.isActivated()){
                    preferito.setActivated(false);
                    preferito.setColorFilter(Color.argb(255, 255, 255, 255));

                }
                else{
                    preferito.setColorFilter(Color.argb(255, 255, 235, 59));
                    preferito.setActivated(true);
                }
            }
        });

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setTimeFromRicetta = new Intent(getApplicationContext(), Timer.class);
                setTimeFromRicetta.putExtra("name", nameRicetta);
                //setTimeFromRicetta.putExtra("time", )

                startActivity(setTimeFromRicetta);
            }
        });
    }
}