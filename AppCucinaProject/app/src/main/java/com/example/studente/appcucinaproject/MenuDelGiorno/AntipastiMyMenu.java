package com.example.studente.appcucinaproject.MenuDelGiorno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.studente.appcucinaproject.Cards.MyCardAdapter;
import com.example.studente.appcucinaproject.Cards.MyCardAdapterHome;
import com.example.studente.appcucinaproject.Cards.RicettaDetails;
import com.example.studente.appcucinaproject.DatabaseAccess;
import com.example.studente.appcucinaproject.R;

import java.util.ArrayList;

public class AntipastiMyMenu extends AppCompatActivity {

    DatabaseAccess databaseAccess;
    LinearLayoutManager layoutManager;
    private ArrayList<RicettaDetails> list = new ArrayList<RicettaDetails>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antipasti_my_menu);

        databaseAccess = DatabaseAccess.getInstance(this);

        databaseAccess.open();
            list= databaseAccess.getRicettaAntipastoConImage();
        databaseAccess.close();

        RecyclerView rv = (RecyclerView) findViewById(R.id.rec_view_antipasti_mymenu);
        rv.setHasFixedSize(true);
        MyCardAdapterHome adapter = new MyCardAdapterHome(list, this);
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        setTitle("Seleziona Antipasto");
    }
}
