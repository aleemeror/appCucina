package com.example.studente.appcucinaproject.Spesa;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.studente.appcucinaproject.R;

public class SpesaDetailsActivity extends AppCompatActivity {

    TextView spesaTitolo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spesa_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spesaTitolo = (TextView) findViewById(R.id.titoloSpesa);

        setTitle("Spesa");

        String str = getIntent().getStringExtra("nameSpesa");

        spesaTitolo.setText(str);
    }

}
