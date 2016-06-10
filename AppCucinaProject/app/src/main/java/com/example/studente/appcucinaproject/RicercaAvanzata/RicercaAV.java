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
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;

import com.example.studente.appcucinaproject.CalcolaTeglia.CalcolaTeglia;
import com.example.studente.appcucinaproject.Calcolatrice.Calcolatrice;
import com.example.studente.appcucinaproject.DatabaseAccess;
import com.example.studente.appcucinaproject.Home;
import com.example.studente.appcucinaproject.MenuDelGiorno.MenuDelGiorno;
import com.example.studente.appcucinaproject.R;
import com.example.studente.appcucinaproject.Ricettario.Ricettario;
import com.example.studente.appcucinaproject.Spesa.Spesa;
import com.example.studente.appcucinaproject.Timer.Timer;

import org.florescu.android.rangeseekbar.RangeSeekBar;

public class RicercaAV extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private EditText nomeRicetta;   //editText
    private EditText ingrediente1,ingrediente2,ingrediente3; //2,3,4
    private CheckBox antipastoCB, primoCB, secondoCB, dolceCB;
    private RangeSeekBar rangeBarCalorie, rangeBarTempo;
    private RadioGroup difficolta;
    private String myNomeRicetta, myIngrediente1, myIngrediente2, myIngrediente3;

    private int minCal = 0, maxCal = 0;
    private int minTime = 0, maxTime = 0;

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

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        myNomeRicetta = "";
        myIngrediente1 = "";
        myIngrediente2 = "";
        myIngrediente3 = "";

        nomeRicetta = (EditText)findViewById(R.id.editText);
        ingrediente1 = (EditText)findViewById(R.id.editText2);
        ingrediente2 = (EditText)findViewById(R.id.editText3);
        ingrediente3 = (EditText)findViewById(R.id.editText4);

        antipastoCB = (CheckBox)findViewById(R.id.checkBox);
        primoCB = (CheckBox)findViewById(R.id.checkBox2);
        secondoCB = (CheckBox)findViewById(R.id.checkBox3);
        dolceCB = (CheckBox)findViewById(R.id.checkBox4);

        rangeBarTempo = (RangeSeekBar)findViewById(R.id.seekbar);
        rangeBarCalorie = (RangeSeekBar)findViewById(R.id.seekBar2);

        difficolta = (RadioGroup)findViewById(R.id.radioGroup);

        int ID_Difficolta = difficolta.getCheckedRadioButtonId();

        myNomeRicetta = nomeRicetta.getText().toString();
        myIngrediente1 = ingrediente1.getText().toString();
        myIngrediente2 = ingrediente2.getText().toString();
        myIngrediente3 = ingrediente3.getText().toString();

        boolean isAntipastoChecked = antipastoCB.isChecked();
        boolean isPrimoCBChecked = primoCB.isChecked();
        boolean isSecondoCBChecked = secondoCB.isChecked();
        boolean isDolceCBChecked = dolceCB.isChecked();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        minCal = databaseAccess.getMINCalorie();
        maxCal = databaseAccess.getMAXCalorie();

        minTime = databaseAccess.getMINTempo();
        maxTime = databaseAccess.getMAXTempo();

        databaseAccess.close();

        /*rangeTempo.setRangeValues(minTime, maxTime);
        rangeBarCalorie.setRangeValues(minCal, maxCal);

        int calorieMINValue = rangeBarTempo.getSelectedMinValue().intValue();  //numero minimo
        int calorieMAXValue = rangeBarTempo.getSelectedMaxValue().intValue();  //numero massimo

        int tempoMINValue = rangeBarCalorie.getSelectedMinValue().intValue();  //numero minimo
        int tempoMAXValue = rangeBarCalorie.getSelectedMaxValue().intValue();  //numero massimo */

        


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
            // startActivity(new Intent(this, RicercaAV.class));

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
