package com.example.studente.appcucinaproject.MenuDelGiorno;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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
import android.widget.ImageView;

import com.example.studente.appcucinaproject.CalcolaTeglia.CalcolaTeglia;
import com.example.studente.appcucinaproject.Calcolatrice.Calcolatrice;
import com.example.studente.appcucinaproject.Cards.MyCardAdapterHome;
import com.example.studente.appcucinaproject.Cards.RicettaDetails;
import com.example.studente.appcucinaproject.Convertitore.Convertitore;
import com.example.studente.appcucinaproject.Home;
import com.example.studente.appcucinaproject.R;
import com.example.studente.appcucinaproject.RicercaAvanzata.RicercaAV;
import com.example.studente.appcucinaproject.Ricettario.Ricettario;
import com.example.studente.appcucinaproject.Spesa.Spesa;
import com.example.studente.appcucinaproject.Timer.Timer;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class MenuDelGiorno extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    CardView cardv;
    ArrayList<RicettaDetails> list = new ArrayList<RicettaDetails>();

    CardView cardAntipasto;
    CardView cardAPrimo;
    CardView cardSecondo;
    CardView cardDolce;

    ImageView img_antipasto;
    ImageView img_primo;
    ImageView img_secondo;
    ImageView img_dolce;

    String portata=null;



    int[] images = {R.drawable.antipasto,R.drawable.primo,R.drawable.secondo,R.drawable.dolce};
    String[] title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_del_giorno);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("Menu del giorno");

        cardAntipasto = (CardView)findViewById(R.id.card_antipasto_mymenu);
        cardAPrimo = (CardView)findViewById(R.id.card_primo_mymenu);
        cardSecondo = (CardView)findViewById(R.id.card_secondo_mymenu);
        cardDolce = (CardView)findViewById(R.id.card_dolce_mymenu);


        img_antipasto=(ImageView)findViewById(R.id.imageViewAntipastomymenu);
        img_primo=(ImageView)findViewById(R.id.imageViewPrimomymenu);
        img_secondo=(ImageView)findViewById(R.id.imageViewSecondomymenu);
        img_dolce=(ImageView)findViewById(R.id.imageViewDolcemymenu);

        cardAntipasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), AntipastiMyMenu.class);
                startActivity(intent);
            }
        });

        cardAPrimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getBaseContext(), AntipastiMyMenu.class);
                startActivity(intent);*/
            }
        });

        cardSecondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getBaseContext(), AntipastiMyMenu.class);
                startActivity(intent);*/
            }
        });

        cardDolce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getBaseContext(), AntipastiMyMenu.class);
                startActivity(intent);*/
            }
        });

        //portata = getIntent().getStringExtra("tipoPiattoScelto");

        /*if(getIntent().getStringExtra("tipoPiattoScelto").toString().equals("Antipasto")){
            ByteArrayInputStream inputStream = new ByteArrayInputStream(getIntent().getByteArrayExtra("img_id"));
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            img_antipasto.setImageBitmap(bitmap);
        }
        else if(getIntent().getStringExtra("tipoPiattoScelto").toString().equals("Primo")){
            ByteArrayInputStream inputStream = new ByteArrayInputStream(getIntent().getByteArrayExtra("img_id"));
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            img_primo.setImageBitmap(bitmap);

        }
        else if(getIntent().getStringExtra("tipoPiattoScelto").toString().equals("Secondo")){
            ByteArrayInputStream inputStream = new ByteArrayInputStream(getIntent().getByteArrayExtra("img_id"));
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            img_secondo.setImageBitmap(bitmap);

        }
        else if(getIntent().getStringExtra("tipoPiattoScelto").toString().equals("Dolce")){
            ByteArrayInputStream inputStream = new ByteArrayInputStream(getIntent().getByteArrayExtra("img_id"));
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            img_dolce.setImageBitmap(bitmap);

        }*/

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
        getMenuInflater().inflate(R.menu.menu_del_giorno, menu);
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
            //startActivity(new Intent(this, MenuDelGiorno.class));
           // finish();

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
