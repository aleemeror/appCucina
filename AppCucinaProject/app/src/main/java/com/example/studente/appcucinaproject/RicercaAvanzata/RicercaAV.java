package com.example.studente.appcucinaproject.RicercaAvanzata;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.example.studente.appcucinaproject.CalcolaTeglia.CalcolaTeglia;
import com.example.studente.appcucinaproject.Calcolatrice.Calcolatrice;
import com.example.studente.appcucinaproject.Home;
import com.example.studente.appcucinaproject.R;
import com.example.studente.appcucinaproject.Ricettario.Ricettario;
import com.example.studente.appcucinaproject.Spesa.Spesa;
import com.example.studente.appcucinaproject.Timer.Timer;

public class RicercaAV extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //private NumberPicker pickerOre, pickerMinuti,pickerSecondi;
    private EditText nomeRicerca;   //editText
    private EditText ingrediente1,ingrediente2,ingrediente3; //2,3,4
    private CheckBox antipastoCB, primoCB, secondoCB, dolceCB;

    private SQLiteDatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricerca_av);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("Ricerca Avanzata");

        //ROTAZIONE SCHERMO BLOCCATA
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        nomeRicerca = (EditText)findViewById(R.id.editText);
        ingrediente1 = (EditText)findViewById(R.id.editText2);
        ingrediente2 = (EditText)findViewById(R.id.editText3);
        ingrediente3 = (EditText)findViewById(R.id.editText4);

        antipastoCB = (CheckBox)findViewById(R.id.checkBox);
        primoCB = (CheckBox)findViewById(R.id.checkBox2);
        secondoCB = (CheckBox)findViewById(R.id.checkBox3);
        dolceCB = (CheckBox)findViewById(R.id.checkBox4);


        mydatabase = openOrCreateDatabase("your database name",MODE_PRIVATE,null);



        /*pickerOre = (NumberPicker) findViewById(R.id.numberPickerHours);
        pickerOre.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        pickerOre.setMaxValue(99);
        pickerOre.setMinValue(00);
        pickerOre.setFocusable(true);
        pickerOre.setFocusableInTouchMode(true);

        pickerMinuti = (NumberPicker) findViewById(R.id.numberPickerMinutes);
        pickerMinuti.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        pickerMinuti.setMaxValue(59);
        pickerMinuti.setMinValue(00);
        pickerMinuti.setFocusable(true);
        pickerMinuti.setFocusableInTouchMode(true);

        pickerSecondi = (NumberPicker) findViewById(R.id.numberPickerSeconds);
        pickerSecondi.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        pickerSecondi.setMaxValue(59);
        pickerSecondi.setMinValue(00);
        pickerSecondi.setFocusable(true);
        pickerSecondi.setFocusableInTouchMode(true);

        pickerOre.setValue(0);
        pickerMinuti.setValue(0);
        pickerSecondi.setValue(0);*/






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

    //TOLGO TRE PUNTINI settings
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ricerca_av, menu);
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
            //startActivity(new Intent(this, RicercaAV.class));

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
