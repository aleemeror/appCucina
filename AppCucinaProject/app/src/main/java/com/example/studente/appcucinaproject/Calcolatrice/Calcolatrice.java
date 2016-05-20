package com.example.studente.appcucinaproject.Calcolatrice;

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

import com.example.studente.appcucinaproject.CalcolaTeglia.CalcolaTeglia;
import com.example.studente.appcucinaproject.Home;
import com.example.studente.appcucinaproject.R;
import com.example.studente.appcucinaproject.RicercaAvanzata.RicercaAV;
import com.example.studente.appcucinaproject.Ricettario.Ricettario;
import com.example.studente.appcucinaproject.Spesa.Spesa;
import com.example.studente.appcucinaproject.Timer.Timer;

public class Calcolatrice extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button0;
    private Button buttonpiù;
    private Button buttonmeno;
    private Button buttonper;
    private Button buttondiviso;
    private Button buttonuguale;
    private Button buttonac;
    private Button buttonpunto;
    private Button buttonapice;
    private Button buttonc;

    private EditText number;

    String numero1;
    String numero2;
    String simbolo;

    float risultato;
    boolean operazione;
    boolean uguale;
    boolean punto1;
    boolean punto2;
    int cancella;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcolatrice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("Calcolatrice");


        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        button4=(Button)findViewById(R.id.button4);
        button5=(Button)findViewById(R.id.button5);
        button6=(Button)findViewById(R.id.button6);
        button7=(Button)findViewById(R.id.button7);
        button8=(Button)findViewById(R.id.button8);
        button9=(Button)findViewById(R.id.button9);
        button0=(Button)findViewById(R.id.button0);
        buttonpunto=(Button)findViewById(R.id.buttonpunto);
        buttonpiù=(Button)findViewById(R.id.buttonpiù);
        buttonmeno=(Button)findViewById(R.id.buttonmeno);
        buttonper=(Button)findViewById(R.id.buttonper);
        buttondiviso=(Button)findViewById(R.id.buttondiviso);
        buttonuguale=(Button)findViewById(R.id.buttonuguale);
        buttonac=(Button)findViewById(R.id.buttonac);
        buttonapice=(Button)findViewById(R.id.buttonapice);
        buttonc=(Button)findViewById(R.id.buttonC);

        number=(EditText)findViewById(R.id.editText);

        numero1 = "";
        numero2 = "";
        simbolo = "";
        risultato = 0;
        operazione = false;
        uguale=false;
        punto1=false;
        punto2=false;
        cancella=0;

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!operazione && !uguale)
                {
                    numero1 += "0";
                    number.setText(numero1);
                    cancella=1;
                }
                else if(operazione && !uguale)
                {
                    numero2 += "0";
                    number.setText(numero1 + simbolo + numero2);
                    cancella=3;
                }
                else if(!operazione && uguale)
                {
                    numero1 = "0";
                    number.setText(numero1);
                    uguale=false;
                    punto1=false;
                    cancella=1;
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!operazione && !uguale)
                {
                    numero1 += "1";
                    number.setText(numero1);
                    cancella=1;
                }
                else if(operazione && !uguale)
                {
                    numero2 += "1";
                    number.setText(numero1 + simbolo + numero2);
                    cancella=3;
                }
                else if(!operazione && uguale)
                {
                    numero1 = "1";
                    number.setText(numero1);
                    uguale=false;
                    punto1=false;
                    cancella=1;
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!operazione && !uguale)
                {
                    numero1 += "2";
                    number.setText(numero1);
                    cancella=1;
                }
                else if(operazione && !uguale)
                {
                    numero2 += "2";
                    number.setText(numero1 + simbolo + numero2);
                    cancella=3;
                }
                else if(!operazione && uguale)
                {
                    numero1 = "2";
                    number.setText(numero1);
                    uguale=false;
                    punto1=false;
                    cancella=1;
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!operazione && !uguale)
                {
                    numero1 += "3";
                    number.setText(numero1);
                    cancella=1;
                }
                else if(operazione && !uguale)
                {
                    numero2 += "3";
                    number.setText(numero1 + simbolo + numero2);
                    cancella=3;
                }
                else if(!operazione && uguale)
                {
                    numero1 = "3";
                    number.setText(numero1);
                    uguale=false;
                    punto1=false;
                    cancella=1;
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!operazione && !uguale)
                {
                    numero1 += "4";
                    number.setText(numero1);
                    cancella=1;
                }
                else if(operazione && !uguale)
                {
                    numero2 += "4";
                    number.setText(numero1 + simbolo + numero2);
                    cancella=3;
                }
                else if(!operazione && uguale)
                {
                    numero1 = "4";
                    number.setText(numero1);
                    uguale=false;
                    punto1=false;
                    cancella=1;
                }
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!operazione && !uguale)
                {
                    numero1 += "5";
                    number.setText(numero1);
                    cancella=1;
                }
                else if(operazione && !uguale)
                {
                    numero2 += "5";
                    number.setText(numero1 + simbolo + numero2);
                    cancella=3;
                }
                else if(!operazione && uguale)
                {
                    numero1 = "5";
                    number.setText(numero1);
                    uguale=false;
                    punto1=false;
                    cancella=1;
                }
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!operazione && !uguale)
                {
                    numero1 += "6";
                    number.setText(numero1);
                    cancella=1;
                }
                else if(operazione && !uguale)
                {
                    numero2 += "6";
                    number.setText(numero1 + simbolo + numero2);
                    cancella=3;
                }
                else if(!operazione && uguale)
                {
                    numero1 = "6";
                    number.setText(numero1);
                    uguale=false;
                    punto1=false;
                    cancella=1;
                }
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!operazione && !uguale)
                {
                    numero1 += "7";
                    number.setText(numero1);
                    cancella=1;
                }
                else if(operazione && !uguale)
                {
                    numero2 += "7";
                    number.setText(numero1 + simbolo + numero2);
                    cancella=3;
                }
                else if(!operazione && uguale)
                {
                    numero1 = "7";
                    number.setText(numero1);
                    uguale=false;
                    punto1=false;
                    cancella=1;
                }
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!operazione && !uguale)
                {
                    numero1 += "8";
                    number.setText(numero1);
                    cancella=1;
                }
                else if(operazione && !uguale)
                {
                    numero2 += "8";
                    number.setText(numero1 + simbolo + numero2);
                    cancella=3;
                }
                else if(!operazione && uguale)
                {
                    numero1 = "8";
                    number.setText(numero1);
                    uguale=false;
                    punto1=false;
                    cancella=1;
                }
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!operazione && !uguale)
                {
                    numero1 += "9";
                    number.setText(numero1);
                    cancella=1;
                }
                else if(operazione && !uguale)
                {
                    numero2 += "9";
                    number.setText(numero1 + simbolo + numero2);
                    cancella=3;
                }
                else if(!operazione && uguale)
                {
                    numero1 = "9";
                    number.setText(numero1);
                    uguale=false;
                    punto1=false;
                    cancella=1;
                }
            }
        });

        buttonpunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!operazione && !punto1) {
                    numero1 += ".";
                    number.setText(numero1);
                    punto1=true;
                    cancella=1;
                } else if(operazione && !punto2) {
                    numero2 += ".";
                    number.setText(numero1 + simbolo + numero2);
                    punto2=true;
                    cancella=3;
                }
                uguale=false;
            }
        });

        buttonpiù.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numero2 == "")
                {
                    operazione = true;
                    simbolo = " + ";
                    number.setText(numero1 + simbolo);
                    uguale=false;
                    cancella=2;
                }
                else
                {
                    float numeroUno = 0;
                    float numeroDue = 0;
                    try {
                        numeroUno = Float.parseFloat(numero1);
                        numeroDue = Float.parseFloat(numero2);
                    }catch(Exception E)
                    {
                        number.setText("ERRORE");
                    }

                    if(simbolo == " - " && numero1 != "" && numero2 != "")
                    {
                        risultato = numeroUno - numeroDue;
                    }
                    else if(simbolo == " + " && numero1 != "" && numero2 != "")
                    {
                        risultato = numeroUno + numeroDue;
                    }
                    else if(simbolo == " * " && numero1 != "" && numero2 != "")
                    {
                        risultato = numeroUno * numeroDue;
                    }
                    else if(simbolo == " / " && numero1 != "" && numeroDue != 0)
                    {
                        risultato = numeroUno / numeroDue;
                    }
                    else if(simbolo == " ^ " && numero1 != "" && numeroDue != 0) {
                        risultato = numeroUno;
                        for (int i = 1; i < numeroDue; i++) {
                            risultato = risultato * numeroUno;
                        }
                    }

                    String ris = Float.toString(risultato);

                    if(simbolo == " / " && numeroDue == 0)
                    {
                        number.setText("ERRORE");
                    }
                    else
                    {
                        if(numero1 == "" || simbolo == "" || numero2 == "")
                        {
                            number.setText("ERRORE");
                        }
                        else {
                            number.setText(ris);
                        }
                    }

                    numero1 = ris;
                    numero2 = "";
                    simbolo = " + ";
                    risultato = 0;
                    operazione = true;
                    uguale=false;
                    punto1=true;
                    punto2=false;
                    number.setText(numero1 + simbolo);
                    cancella=2;
                }
            }
        });

        buttonmeno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numero2 == "")
                {
                    operazione = true;
                    simbolo = " - ";
                    number.setText(numero1 + simbolo);
                    uguale=false;
                    cancella=2;
                }
                else
                {
                    float numeroUno = 0;
                    float numeroDue = 0;
                    try {
                        numeroUno = Float.parseFloat(numero1);
                        numeroDue = Float.parseFloat(numero2);
                    }catch(Exception E)
                    {
                        number.setText("ERRORE");
                    }

                    if(simbolo == " - " && numero1 != "" && numero2 != "")
                    {
                        risultato = numeroUno - numeroDue;
                    }
                    else if(simbolo == " + " && numero1 != "" && numero2 != "")
                    {
                        risultato = numeroUno + numeroDue;
                    }
                    else if(simbolo == " * " && numero1 != "" && numero2 != "")
                    {
                        risultato = numeroUno * numeroDue;
                    }
                    else if(simbolo == " / " && numero1 != "" && numeroDue != 0)
                    {
                        risultato = numeroUno / numeroDue;
                    }
                    else if(simbolo == " ^ " && numero1 != "" && numeroDue != 0) {
                        risultato = numeroUno;
                        for (int i = 1; i < numeroDue; i++) {
                            risultato = risultato * numeroUno;
                        }
                    }

                    String ris = Float.toString(risultato);

                    if(simbolo == " / " && numeroDue == 0)
                    {
                        number.setText("ERRORE");
                    }
                    else
                    {
                        if(numero1 == "" || simbolo == "" || numero2 == "")
                        {
                            number.setText("ERRORE");
                        }
                        else {
                            number.setText(ris);
                        }
                    }

                    numero1 = ris;
                    numero2 = "";
                    simbolo = " - ";
                    risultato = 0;
                    operazione = true;
                    uguale=false;
                    punto1=true;
                    punto2=false;
                    number.setText(numero1 + simbolo);
                    cancella=2;
                }
            }
        });

        buttonper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numero2 == "")
                {
                    operazione = true;
                    simbolo = " * ";
                    number.setText(numero1 + simbolo);
                    uguale=false;
                    cancella=2;
                }
                else
                {
                    float numeroUno = 0;
                    float numeroDue = 0;
                    try {
                        numeroUno = Float.parseFloat(numero1);
                        numeroDue = Float.parseFloat(numero2);
                    }catch(Exception E)
                    {
                        number.setText("ERRORE");
                    }

                    if(simbolo == " - " && numero1 != "" && numero2 != "")
                    {
                        risultato = numeroUno - numeroDue;
                    }
                    else if(simbolo == " + " && numero1 != "" && numero2 != "")
                    {
                        risultato = numeroUno + numeroDue;
                    }
                    else if(simbolo == " * " && numero1 != "" && numero2 != "")
                    {
                        risultato = numeroUno * numeroDue;
                    }
                    else if(simbolo == " / " && numero1 != "" && numeroDue != 0)
                    {
                        risultato = numeroUno / numeroDue;
                    }
                    else if(simbolo == " ^ " && numero1 != "" && numeroDue != 0) {
                        risultato = numeroUno;
                        for (int i = 1; i < numeroDue; i++) {
                            risultato = risultato * numeroUno;
                        }
                    }

                    String ris = Float.toString(risultato);

                    if(simbolo == " / " && numeroDue == 0)
                    {
                        number.setText("ERRORE");
                    }
                    else
                    {
                        if(numero1 == "" || simbolo == "" || numero2 == "")
                        {
                            number.setText("ERRORE");
                        }
                        else {
                            number.setText(ris);
                        }
                    }

                    numero1 = ris;
                    numero2 = "";
                    simbolo = " * ";
                    risultato = 0;
                    operazione = true;
                    uguale=false;
                    punto1=true;
                    punto2=false;
                    number.setText(numero1 + simbolo);
                    cancella=2;
                }
            }
        });

        buttondiviso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numero2 == "")
                {
                    operazione = true;
                    simbolo = " / ";
                    number.setText(numero1 + simbolo);
                    uguale=false;
                    cancella=2;
                }
                else
                {
                    float numeroUno = 0;
                    float numeroDue = 0;
                    try {
                        numeroUno = Float.parseFloat(numero1);
                        numeroDue = Float.parseFloat(numero2);
                    }catch(Exception E)
                    {
                        number.setText("ERRORE");
                    }

                    if(simbolo == " - " && numero1 != "" && numero2 != "")
                    {
                        risultato = numeroUno - numeroDue;
                    }
                    else if(simbolo == " + " && numero1 != "" && numero2 != "")
                    {
                        risultato = numeroUno + numeroDue;
                    }
                    else if(simbolo == " * " && numero1 != "" && numero2 != "")
                    {
                        risultato = numeroUno * numeroDue;
                    }
                    else if(simbolo == " / " && numero1 != "" && numeroDue != 0)
                    {
                        risultato = numeroUno / numeroDue;
                    }
                    else if(simbolo == " ^ " && numero1 != "" && numeroDue != 0) {
                        risultato = numeroUno;
                        for (int i = 1; i < numeroDue; i++) {
                            risultato = risultato * numeroUno;
                        }
                    }

                    String ris = Float.toString(risultato);

                    if(simbolo == " / " && numeroDue == 0)
                    {
                        number.setText("ERRORE");
                    }
                    else
                    {
                        if(numero1 == "" || simbolo == "" || numero2 == "")
                        {
                            number.setText("ERRORE");
                        }
                        else {
                            number.setText(ris);
                        }
                    }

                    numero1 = ris;
                    numero2 = "";
                    simbolo = " / ";
                    risultato = 0;
                    operazione = true;
                    uguale=false;
                    punto1=true;
                    punto2=false;
                    number.setText(numero1 + simbolo);
                    cancella=2;
                }
            }
        });

        buttonapice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numero2 == "")
                {
                    operazione = true;
                    simbolo = " ^ ";
                    number.setText(numero1 + simbolo);
                    uguale=false;
                    cancella=2;
                }
                else
                {
                    float numeroUno = 0;
                    float numeroDue = 0;
                    try {
                        numeroUno = Float.parseFloat(numero1);
                        numeroDue = Float.parseFloat(numero2);
                    }catch(Exception E)
                    {
                        number.setText("ERRORE");
                    }

                    if(simbolo == " - " && numero1 != "" && numero2 != "")
                    {
                        risultato = numeroUno - numeroDue;
                    }
                    else if(simbolo == " + " && numero1 != "" && numero2 != "")
                    {
                        risultato = numeroUno + numeroDue;
                    }
                    else if(simbolo == " * " && numero1 != "" && numero2 != "")
                    {
                        risultato = numeroUno * numeroDue;
                    }
                    else if(simbolo == " / " && numero1 != "" && numeroDue != 0)
                    {
                        risultato = numeroUno / numeroDue;
                    }
                    else if(simbolo == " ^ " && numero1 != "" && numeroDue != 0) {
                        risultato = numeroUno;
                        for (int i = 1; i < numeroDue; i++) {
                            risultato = risultato * numeroUno;
                        }
                    }

                    String ris = Float.toString(risultato);

                    if(simbolo == " / " && numeroDue == 0)
                    {
                        number.setText("ERRORE");
                    }
                    else
                    {
                        if(numero1 == "" || simbolo == "" || numero2 == "")
                        {
                            number.setText("ERRORE");
                        }
                        else {
                            number.setText(ris);
                        }
                    }

                    numero1 = ris;
                    numero2 = "";
                    simbolo = " ^ ";
                    risultato = 0;
                    operazione = true;
                    uguale=false;
                    punto1=true;
                    punto2=false;
                    number.setText(numero1 + simbolo);
                    cancella=2;
                }
            }
        });


        buttonac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero1 = "";
                numero2 = "";
                simbolo = "";
                risultato = 0;
                operazione = false;
                number.setText("");
                uguale=false;
                punto1=false;
                punto2=false;
                cancella=0;
            }
        });

        buttonuguale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float numeroUno = 0;
                float numeroDue = 0;
                try {
                    numeroUno = Float.parseFloat(numero1);
                    numeroDue = Float.parseFloat(numero2);
                }catch(Exception E)
                {
                    number.setText("ERRORE");
                }

                if(simbolo == " - " && numero1 != "" && numero2 != "")
                {
                    risultato = numeroUno - numeroDue;
                }
                else if(simbolo == " + " && numero1 != "" && numero2 != "")
                {
                    risultato = numeroUno + numeroDue;
                }
                else if(simbolo == " * " && numero1 != "" && numero2 != "")
                {
                    risultato = numeroUno * numeroDue;
                }
                else if(simbolo == " / " && numero1 != "" && numeroDue != 0)
                {
                    risultato = numeroUno / numeroDue;
                }
                else if(simbolo == " ^ " && numero1 != "" && numeroDue != 0) {
                    risultato = numeroUno;
                    for (int i = 1; i < numeroDue; i++) {
                        risultato = risultato * numeroUno;
                    }
                }

                String ris = Float.toString(risultato);

                if(simbolo == " / " && numeroDue == 0)
                {
                    number.setText("ERRORE");
                }
                else
                {
                    if(numero1 == "" || simbolo == "" || numero2 == "")
                    {
                        number.setText("ERRORE");
                    }
                    else {
                        number.setText(ris);
                    }
                }

                numero1 = ris;
                numero2 = "";
                simbolo = "";
                risultato = 0;
                operazione = false;
                uguale=true;
                punto1=true;
                punto2=false;
                cancella=1;
            }
        });

        buttonc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cancella==0)
                {
                    number.setText(numero1);
                }
                else if(cancella==1)
                {
                    if(numero1.length()==0)
                    {
                        cancella=0;
                    }
                    else {
                        numero1 = numero1.substring(0, numero1.length() - 1);
                        number.setText(numero1);
                    }
                }
                else if(cancella==2)
                {
                    number.setText(numero1);
                    operazione=false;
                    cancella=1;
                }
                else if(cancella==3)
                {
                    if(numero2.length()==0)
                    {
                        number.setText(numero1+simbolo);
                        cancella=2;
                    }
                    else
                    {
                        numero2=numero2.substring(0,numero2.length()-1);
                        number.setText(numero1+simbolo+numero2);
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
        getMenuInflater().inflate(R.menu.calcolatrice, menu);
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
            //startActivity(new Intent(this, Calcolatrice.class));

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
