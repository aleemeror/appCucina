package com.example.studente.appcucinaproject.Spesa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.studente.appcucinaproject.CalcolaTeglia.CalcolaTeglia;
import com.example.studente.appcucinaproject.Calcolatrice.Calcolatrice;
import com.example.studente.appcucinaproject.Cards.MyCardAdapterSpesa;
import com.example.studente.appcucinaproject.Convertitore.Convertitore;
import com.example.studente.appcucinaproject.Home;
import com.example.studente.appcucinaproject.MenuDelGiorno.MenuDelGiorno;
import com.example.studente.appcucinaproject.R;
import com.example.studente.appcucinaproject.RicercaAvanzata.RicercaAV;
import com.example.studente.appcucinaproject.Ricettario.Ricettario;
import com.example.studente.appcucinaproject.Timer.Timer;

import java.util.ArrayList;

public class Spesa extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    CardView cardv;
    public ArrayList<SpesaObject> listSpesa = new ArrayList<>();
    public ArrayList<String> list = new ArrayList<String>();

    private final String KEY_RECYCLER_STATE = "recycler_state";
    private static Bundle mBundleRecyclerViewState;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spesa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setTitle("Spesa");

        if(getIntent().hasExtra("listaDiSpeseInput")){
            listSpesa= getIntent().getParcelableArrayListExtra("listaDiSpeseInput"); //prendo la lista di oggetti che mi arrivano e aggiorno quella nella main della spesa
        } else{
            /*for(int count =0; count < 5; count++){
                listSpesa.add("Spesa "+ count);
            }*/

        }

        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewSpesa);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setHasFixedSize(true);
        adapter = new MyCardAdapterSpesa(listSpesa,Spesa.this);    //passo una lista di oggetti "SpesaObject"
        recyclerView.setAdapter(adapter);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Inserita una nuova lista della spesa", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(Spesa.this, InserisciSpesa.class); //Mi porta alla activity per inserire i dati della ricetta
                intent.putParcelableArrayListExtra("listaSpese", listSpesa); //passo alla activity di destinazione una lista di stringhe identificata da un "ID" sottoforma di stringa
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.spesa, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(this, Home.class));
            finish();
        } else if (id == R.id.nav_ricettario) {

            startActivity(new Intent(this, Ricettario.class));
            finish();

        } else if (id == R.id.nav_ricerca) {
            startActivity(new Intent(this, RicercaAV.class));
            finish();

        } else if (id == R.id.nav_menu_del_giorno) {
            startActivity(new Intent(this, MenuDelGiorno.class));
            finish();

        } else if (id == R.id.nav_spesa) {
            startActivity(new Intent(this, Spesa.class));
            finish();

        } else if (id == R.id.nav_calcolatrice) {
            startActivity(new Intent(this, Calcolatrice.class));
            finish();

        } else if (id == R.id.nav_timer) {
            startActivity(new Intent(this, Timer.class));
            finish();
        }
        else if (id == R.id.nav_calcolateglia) {
            startActivity(new Intent(this, CalcolaTeglia.class));
            finish();
        }
        else if(id == R.id.nav_convertitore){
            startActivity(new Intent(this, Convertitore.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}