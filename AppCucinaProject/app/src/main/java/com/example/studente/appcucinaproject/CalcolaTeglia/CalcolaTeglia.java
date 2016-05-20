package com.example.studente.appcucinaproject.CalcolaTeglia;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.studente.appcucinaproject.Calcolatrice.Calcolatrice;
import com.example.studente.appcucinaproject.Home;
import com.example.studente.appcucinaproject.R;
import com.example.studente.appcucinaproject.R.*;
import com.example.studente.appcucinaproject.RicercaAvanzata.RicercaAV;
import com.example.studente.appcucinaproject.Ricettario.Ricettario;
import com.example.studente.appcucinaproject.Spesa.Spesa;
import com.example.studente.appcucinaproject.Timer.Timer;

import java.math.BigDecimal;

public class CalcolaTeglia extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button calcola;
    RadioGroup scelta;
    RadioButton rettangolare;
    RadioButton rotonda;
    RadioGroup scelta2;
    RadioButton rettangolare2;
    RadioButton rotonda2;
    EditText lunghezza1;
    EditText larghezza1;
    EditText diametro1;
    EditText lunghezza2;
    EditText larghezza2;
    EditText diametro2;

    TextView risultato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcola_teglia);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setTitle("Calcola Teglia");

        calcola = (Button)findViewById(R.id.calcola);

        scelta = (RadioGroup)findViewById(R.id.scelta);
        scelta2 = (RadioGroup)findViewById(R.id.scelta2);

        rettangolare = (RadioButton)findViewById(R.id.rettangolare);
        rotonda = (RadioButton)findViewById(R.id.rotonda);
        rettangolare2 = (RadioButton)findViewById(R.id.rettangolare2);
        rotonda2 = (RadioButton)findViewById(R.id.rotonda2);

        lunghezza1 = (EditText)findViewById(R.id.editText);
        larghezza1 = (EditText)findViewById(R.id.editText2);
        diametro1 = (EditText)findViewById(R.id.editText3);
        lunghezza2 = (EditText)findViewById(R.id.editText4);
        larghezza2 = (EditText)findViewById(R.id.editText5);
        diametro2 = (EditText)findViewById(R.id.editText6);

        risultato = (TextView)findViewById(R.id.textView3);

        diametro1.setVisibility(View.INVISIBLE);

        lunghezza1.setVisibility(View.INVISIBLE);

        larghezza1.setVisibility(View.INVISIBLE);

        diametro2.setVisibility(View.INVISIBLE);

        lunghezza2.setVisibility(View.INVISIBLE);

        larghezza2.setVisibility(View.INVISIBLE);

        rettangolare.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int selezionato = scelta.getCheckedRadioButtonId();

                if(selezionato == rettangolare.getId())
                {
                    diametro1.setVisibility(View.INVISIBLE);

                    lunghezza1.setVisibility(View.VISIBLE);

                    larghezza1.setVisibility(View.VISIBLE);
                }
            }
        });

        rotonda.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int selezionato = scelta.getCheckedRadioButtonId();

                if (selezionato == rotonda.getId())
                {
                    diametro1.setVisibility(View.VISIBLE);

                    lunghezza1.setVisibility(View.INVISIBLE);

                    larghezza1.setVisibility(View.INVISIBLE);
                }
            }
        });


        rettangolare2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int selezionato = scelta2.getCheckedRadioButtonId();

                if(selezionato == rettangolare2.getId())
                {
                    diametro2.setVisibility(View.INVISIBLE);

                    lunghezza2.setVisibility(View.VISIBLE);

                    larghezza2.setVisibility(View.VISIBLE);
                }
            }
        });

        rotonda2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int selezionato = scelta2.getCheckedRadioButtonId();

                if (selezionato == rotonda2.getId())
                {
                    diametro2.setVisibility(View.VISIBLE);

                    lunghezza2.setVisibility(View.INVISIBLE);

                    larghezza2.setVisibility(View.INVISIBLE);
                }
            }
        });

        calcola.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int selezionato = scelta.getCheckedRadioButtonId();
                int selezionato2 = scelta2.getCheckedRadioButtonId();
                double ris=0;
                double lung1=0;
                double larg1=0;
                double diam1=0;
                double lung2=0;
                double larg2=0;
                double diam2=0;
                double pgreco = 3.14;

                if (selezionato == rettangolare.getId() && selezionato2 == rettangolare2.getId())
                {
                    try
                    {
                        lung1=Double.parseDouble(lunghezza1.getText().toString());
                        larg1=Double.parseDouble(larghezza1.getText().toString());
                        lung2=Double.parseDouble(lunghezza2.getText().toString());
                        larg2=Double.parseDouble(larghezza2.getText().toString());
                        ris=(lung2*larg2)/(lung1*larg1);
                        risultato.setText(Double.toString(roundDecimal(ris)));
                    }
                    catch(Exception E)
                    {
                        System.out.println("errore");
                    }
                }

                else if(selezionato == rettangolare.getId() && selezionato2==rotonda2.getId())
                {
                    try
                    {
                        lung1=Double.parseDouble(lunghezza1.getText().toString());
                        larg1=Double.parseDouble(larghezza1.getText().toString());
                        diam2=Double.parseDouble(diametro2.getText().toString());

                        ris = (pgreco * ((diam2/2) * (diam2/2)))/(lung1 * larg1);
                        risultato.setText(Double.toString(roundDecimal(ris)));
                    }
                    catch(Exception E)
                    {
                        System.out.println("errore");
                    }
                }

                else if(selezionato == rotonda.getId() && selezionato2==rettangolare2.getId())
                {
                    try
                    {
                        lung2 = Double.parseDouble(lunghezza2.getText().toString());
                        larg2 = Double.parseDouble(larghezza2.getText().toString());
                        diam1 = Double.parseDouble(diametro1.getText().toString());

                        ris = (lung2 * larg2) / (pgreco * ((diam1/2) * (diam1/2)));
                        risultato.setText(Double.toString(roundDecimal(ris)));
                    }
                    catch(Exception E)
                    {
                        System.out.println("errore");
                    }
                }

                else if(selezionato == rotonda.getId() && selezionato2==rotonda2.getId())
                {
                    try
                    {
                        diam2 = Double.parseDouble(diametro2.getText().toString());
                        diam1 = Double.parseDouble(diametro1.getText().toString());

                        ris = (pgreco * ((diam2/2) * (diam2/2))) / (pgreco * ((diam1/2) * (diam1/2)));
                        risultato.setText(Double.toString(roundDecimal(ris)));
                    }
                    catch(Exception E)
                    {
                        System.out.println("errore");
                    }
                }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calcola_teglia, menu);
        return true;
    }

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
            //startActivity(new Intent(this, CalcolaTeglia.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public static Double roundDecimal(Double r){        // Funzione per arrotondare il valore del calcola teglia
        BigDecimal bd = new BigDecimal(r);
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP); // 2 cifre decimali

        return bd.doubleValue();
    }

}
