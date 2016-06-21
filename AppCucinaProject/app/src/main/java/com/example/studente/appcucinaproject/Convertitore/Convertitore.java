package com.example.studente.appcucinaproject.Convertitore;

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
import android.widget.TextView;

import com.example.studente.appcucinaproject.CalcolaTeglia.CalcolaTeglia;
import com.example.studente.appcucinaproject.Calcolatrice.Calcolatrice;
import com.example.studente.appcucinaproject.Home;
import com.example.studente.appcucinaproject.MenuDelGiorno.MenuDelGiorno;
import com.example.studente.appcucinaproject.R;
import com.example.studente.appcucinaproject.RicercaAvanzata.RicercaAV;
import com.example.studente.appcucinaproject.Ricettario.Ricettario;
import com.example.studente.appcucinaproject.Spesa.Spesa;
import com.example.studente.appcucinaproject.Timer.Timer;

import java.math.BigDecimal;

public class Convertitore extends AppCompatActivity
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
    private Button buttonc;
    private Button buttonpunto;
    private Button buttonpeso;
    private Button buttonacqua;
    private Button buttongradi;
    private TextView unitàdac,unitàc;

    private EditText number,number2;

    String numero1="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertitore);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        buttonc=(Button)findViewById(R.id.buttonC);
        buttonpeso=(Button)findViewById(R.id.buttonpeso);
        buttonacqua=(Button)findViewById(R.id.buttonacqua);
        buttongradi=(Button)findViewById(R.id.buttongradi);
        number=(EditText)findViewById(R.id.editText);
        number2=(EditText)findViewById(R.id.editText2);
        numero1 = "0";
        unitàdac=(TextView)findViewById(R.id.textView);
        unitàc=(TextView)findViewById(R.id.textView2);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numero1!="0") {
                    numero1 += "0";
                    number.setText(numero1);
                }
                else
                {
                    numero1= "0";
                    number.setText(numero1);
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numero1!="0") {
                    numero1 += "1";
                    number.setText(numero1);
                }
                else
                {
                    numero1= "1";
                    number.setText(numero1);
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numero1!="0") {
                    numero1 += "2";
                    number.setText(numero1);
                }
                else
                {
                    numero1= "2";
                    number.setText(numero1);
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numero1!="0") {
                    numero1 += "3";
                    number.setText(numero1);
                }
                else
                {
                    numero1= "3";
                    number.setText(numero1);
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numero1!="0") {
                    numero1 += "4";
                    number.setText(numero1);
                }
                else
                {
                    numero1= "4";
                    number.setText(numero1);
                }
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numero1!="0") {
                    numero1 += "5";
                    number.setText(numero1);
                }
                else
                {
                    numero1= "5";
                    number.setText(numero1);
                }
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numero1!="0") {
                    numero1 += "6";
                    number.setText(numero1);
                }
                else
                {
                    numero1= "6";
                    number.setText(numero1);
                }
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numero1!="0") {
                    numero1 += "7";
                    number.setText(numero1);
                }
                else
                {
                    numero1= "7";
                    number.setText(numero1);
                }
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numero1!="0") {
                    numero1 += "8";
                    number.setText(numero1);
                }
                else
                {
                    numero1= "8";
                    number.setText(numero1);
                }
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numero1!="0") {
                    numero1 += "9";
                    number.setText(numero1);
                }
                else
                {
                    numero1= "9";
                    number.setText(numero1);
                }
            }
        });

        buttonpunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero1 += ".";
                number.setText(numero1);
            }
        });

        buttonpeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double numeroUno = 0.0;
                Double numeroDue = 0.0;
                try {
                    numeroUno = Double.parseDouble(numero1);
                    numeroDue = numeroUno * 28.3495;
                    BigDecimal bd = new BigDecimal(numeroDue);
                    bd = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
                    numeroDue = bd.doubleValue();
                    number2.setText(Double.toString(numeroDue));
                    unitàdac.setText("oz");
                    unitàc.setText("g");
                } catch (Exception E) {
                    number.setText("ERRORE");
                }
            }
        });

        buttonacqua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double numeroUno = 0.0;
                Double numeroDue = 0.0;
                try {
                    numeroUno = Double.parseDouble(numero1);
                    numeroDue = numeroUno * 29.5753;
                    BigDecimal bd = new BigDecimal(numeroDue);
                    bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                    numeroDue = bd.doubleValue();
                    number2.setText(Double.toString(numeroDue));
                    unitàdac.setText("fl oz");
                    unitàc.setText("ml");
                } catch (Exception E) {
                    number.setText("ERRORE");
                }
            }
        });

        buttongradi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double numeroUno = 0.0;
                Double numeroDue = 0.0;
                try {
                    numeroUno = Double.parseDouble(numero1);
                    numeroDue = (numeroUno-32) / 1.8;
                    BigDecimal bd = new BigDecimal(numeroDue);
                    bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                    numeroDue = bd.doubleValue();
                    number2.setText(Double.toString(numeroDue));
                    unitàdac.setText("°F");
                    unitàc.setText("°C");
                } catch (Exception E) {
                    number.setText("ERRORE");
                }
            }
        });

        buttonc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero1 = "0";
                number.setText(numero1);
                number2.setText("");
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.convertitore, menu);
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
            //startActivity(new Intent(this, Convertitore.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
