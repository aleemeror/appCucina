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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;

import com.example.studente.appcucinaproject.CalcolaTeglia.CalcolaTeglia;
import com.example.studente.appcucinaproject.Calcolatrice.Calcolatrice;
import com.example.studente.appcucinaproject.Convertitore.Convertitore;
import com.example.studente.appcucinaproject.DatabaseAccess;
import com.example.studente.appcucinaproject.Home;
import com.example.studente.appcucinaproject.MenuDelGiorno.MenuDelGiorno;
import com.example.studente.appcucinaproject.R;
import com.example.studente.appcucinaproject.Ricettario.Ricettario;
import com.example.studente.appcucinaproject.Spesa.Spesa;
import com.example.studente.appcucinaproject.Timer.Timer;

import org.florescu.android.rangeseekbar.RangeSeekBar;

import java.util.ArrayList;
import java.util.List;

public class RicercaAV extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button cercaButton;
    private Button cancellaButton;
    private EditText nomeRicetta;   //editText
    private EditText ingrediente1,ingrediente2,ingrediente3; //2,3,4
    private CheckBox antipastoCB, primoCB, secondoCB, dolceCB;
    private RangeSeekBar rangeBarCalorie, rangeBarTempo;
    private RadioGroup difficolta;
    private String myNomeRicetta, myIngrediente1, myIngrediente2, myIngrediente3;

    private int minCal = 0, maxCal = 0;
    private int minTime = 0, maxTime = 0;

    private DatabaseAccess myDatabaseAccess;

    private Intent showResultsIntent;

    private boolean isAntipastoChecked;
    private boolean isPrimoCBChecked;
    private boolean isSecondoCBChecked;
    private boolean isDolceCBChecked;

    private int calorieMINValueSelected;
    private int calorieMAXValueSelected;

    private int tempoMINValueSelected;
    private int tempoMAXValueSelected;

    private int ID_Difficolta;

    private ArrayList<String> listaRisultati;

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

        isAntipastoChecked = false;
        isPrimoCBChecked = false;
        isSecondoCBChecked = false;
        isDolceCBChecked = false;

        cercaButton = (Button)findViewById(R.id.buttonCerca);
        cancellaButton = (Button) findViewById(R.id.buttonCancella);
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

        //leggo dal database i massimi e minimi di tempo e calorie
        myDatabaseAccess = DatabaseAccess.getInstance(this);
        myDatabaseAccess.open();
        minCal = myDatabaseAccess.getMINCalorie();
        maxCal = myDatabaseAccess.getMAXCalorie();

        minTime = myDatabaseAccess.getMINTempo();
        maxTime = myDatabaseAccess.getMAXTempo();

        myDatabaseAccess.close();

        //setto i massimi e i minimi nelle range bars
        rangeBarTempo.setRangeValues(minTime, maxTime);
        rangeBarCalorie.setRangeValues(minCal, maxCal);

        cancellaButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                nomeRicetta.setText("");
                ingrediente1.setText("");
                ingrediente2.setText("");
                ingrediente3.setText("");

                rangeBarTempo.setSelectedMaxValue(maxTime);
                rangeBarTempo.setSelectedMinValue(minTime);

                rangeBarCalorie.setSelectedMaxValue(maxCal);
                rangeBarCalorie.setSelectedMinValue(minCal);

                antipastoCB.setChecked(false);
                primoCB.setChecked(false);
                secondoCB.setChecked(false);
                dolceCB.setChecked(false);

                //imposto la difficoltà a "qualsiasi"
                difficolta.check(R.id.radioButton4);

            }
        });


        cercaButton.setOnClickListener(new View.OnClickListener() {         //CERCA BUTTON
            @Override
            public void onClick(View v) {

                findDifficoltaID();

                //CON IL TRIM TOLGO TUTTI GLI SPAZI PRIMA E DOPO LA STRINGA SENZA ALTERARE QUELLI IN MEZZO
                myNomeRicetta = nomeRicetta.getText().toString().trim();
                myIngrediente1 = ingrediente1.getText().toString().trim();
                myIngrediente2 = ingrediente2.getText().toString().trim();
                myIngrediente3 = ingrediente3.getText().toString().trim();

                isAntipastoChecked = antipastoCB.isChecked();
                isPrimoCBChecked = primoCB.isChecked();
                isSecondoCBChecked = secondoCB.isChecked();
                isDolceCBChecked = dolceCB.isChecked();

                calorieMINValueSelected = rangeBarCalorie.getSelectedMinValue().intValue();  //numero minimo calorie
                calorieMAXValueSelected = rangeBarCalorie.getSelectedMaxValue().intValue();  //numero massimo calorie

                tempoMINValueSelected = rangeBarTempo.getSelectedMinValue().intValue();  //numero minimo tempo
                tempoMAXValueSelected = rangeBarTempo.getSelectedMaxValue().intValue();  //numero massimo tempo

                listaRisultati = new ArrayList<>();

                myDatabaseAccess.open();

                listaRisultati = myDatabaseAccess.getAllResults(myNomeRicetta, myIngrediente1, myIngrediente2, myIngrediente3,
                                                        isAntipastoChecked, isPrimoCBChecked, isSecondoCBChecked, isDolceCBChecked,
                                                        calorieMINValueSelected, calorieMAXValueSelected,
                                                        tempoMINValueSelected, tempoMAXValueSelected,
                                                        ID_Difficolta);

                myDatabaseAccess.close();

                //intent all'activity risultati
                showResultsIntent = new Intent(getApplicationContext(), Visualizzazione_Ricerca_av.class);
                //showResultsIntent.putExtra("risultati", listaRisultati);    //???
                showResultsIntent.putStringArrayListExtra("risultati", listaRisultati);

                startActivity(showResultsIntent);

            }
        });

    }

    private void findDifficoltaID(){
        //leggo l'id selezionato
        int ID_RadioGroup = difficolta.getCheckedRadioButtonId();

        //confronto l'id selezionato con ogni id del gruppo di radiobutton
        if (ID_RadioGroup == R.id.radioButton){
            ID_Difficolta = 1;
        }

        if (ID_RadioGroup == R.id.radioButton2){
            ID_Difficolta = 2;
        }

        if (ID_RadioGroup == R.id.radioButton3){
            ID_Difficolta = 3;
        }

        if (ID_RadioGroup == R.id.radioButton4){
            ID_Difficolta = 4;
        }
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
        else if(id == R.id.nav_convertitore){
            startActivity(new Intent(this, Convertitore.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
