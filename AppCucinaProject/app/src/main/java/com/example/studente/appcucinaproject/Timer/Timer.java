package com.example.studente.appcucinaproject.Timer;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
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
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studente.appcucinaproject.CalcolaTeglia.CalcolaTeglia;
import com.example.studente.appcucinaproject.Calcolatrice.Calcolatrice;
import com.example.studente.appcucinaproject.Home;
import com.example.studente.appcucinaproject.R;
import com.example.studente.appcucinaproject.RicercaAvanzata.RicercaAV;
import com.example.studente.appcucinaproject.Ricettario.Ricettario;
import com.example.studente.appcucinaproject.Spesa.Spesa;

import java.util.concurrent.TimeUnit;

public class Timer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NumberPicker ore, minuti, secondi;

    private Button setTime, cancelTime;
    private Button pauseButton, resumeButton, stopButton;
    private TextView myResult;

    private TextView editOre;
    private TextView editMinuti;
    private TextView editSecondi;

    private Ticker tt = null;

    private int tot = 0;
    private long millis = 0;

    boolean StatoBottoneAttuale=true; //true = "pausa" ; false = "resume"

    private Intent secondACT;
    private EventTimer r;

    private boolean timerAttivato = false;

    private String nomeRicettaFromIntent = "";
    private int tempoRicettaFromIntent = 0;
    private String secondiFromRicetta = "";

    private boolean isTimeFromRicetta = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("Timer");

        //ROTAZIONE SCHERMO BLOCCATA
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setTime = (Button)findViewById(R.id.setTime);
        cancelTime = (Button)findViewById(R.id.cancel);
        pauseButton = (Button)findViewById(R.id.buttonPause);
        stopButton = (Button)findViewById(R.id.buttonStop);
        resumeButton = (Button)findViewById(R.id.buttonResume);

        editSecondi = (TextView)findViewById(R.id.txtTimerSecond);
        editMinuti = (TextView)findViewById(R.id.txtTimerMinute);
        editOre = (TextView)findViewById(R.id.txtTimerHour);

        myResult = (TextView)findViewById(R.id.risultato);

        ore = (NumberPicker) findViewById(R.id.npicker_hours);
        ore.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        ore.setMaxValue(99);
        ore.setMinValue(00);
        ore.setFocusable(true);
        ore.setFocusableInTouchMode(true);


        minuti = (NumberPicker) findViewById(R.id.npicker_minutes);
        minuti.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        minuti.setMaxValue(59);
        minuti.setMinValue(00);
        minuti.setFocusable(true);
        minuti.setFocusableInTouchMode(true);


        secondi = (NumberPicker) findViewById(R.id.npicker_seconds);
        secondi.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        secondi.setMaxValue(59);
        secondi.setMinValue(00);
        secondi.setFocusable(true);
        secondi.setFocusableInTouchMode(true);

        ore.setValue(0);
        minuti.setValue(0);
        secondi.setValue(0);

        if(!timerAttivato)
        {
            ore.setEnabled(true);
            minuti.setEnabled(true);
            secondi.setEnabled(true);
        }

        nomeRicettaFromIntent = getIntent().getStringExtra("name");
        tempoRicettaFromIntent = getIntent().getIntExtra("time", 0);
        //secondiFromRicetta = getIntent().getStringExtra("seconds");

        //tempoRicettaFromIntent = "123";

        //FARE I CONTROLLI PER QUANDO PARTE
        if(!isTimeFromRicetta)
        {
            if((tempoRicettaFromIntent != 0) || (secondiFromRicetta != ""))
            {
                isTimeFromRicetta = true;

                int secondiConvertiti = 0;
                int minutiCalcolati = 0;
                int oreCalcolate = 0;

                if(secondiFromRicetta != "")
                {
                    secondiConvertiti = Integer.parseInt(secondiFromRicetta);
                    secondi.setValue(secondiConvertiti);
                }

                if(tempoRicettaFromIntent != 0)
                {
                    int minutiFromString = tempoRicettaFromIntent;
                    oreCalcolate = minutiFromString / 60;
                    minutiCalcolati = minutiFromString - (60 * oreCalcolate);

                    ore.setValue(oreCalcolate);
                    minuti.setValue(minutiCalcolati);
                }

                String oreInString = Integer.toString(oreCalcolate);
                String minutiInString = Integer.toString(minutiCalcolati);

                //aggiunge uno zero davanti solo per una questione estetica
                /*if(minutiCalcolati < 10)
                    minutiInString = "0" + minutiInString;*/

                if(oreCalcolate != 0)
                    Toast.makeText(getApplicationContext(),"Timer setted at : " + oreInString + "h" + " " + minutiInString + "m", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"Timer setted at : " + minutiInString + "m", Toast.LENGTH_LONG).show();

                ore.setEnabled(false);
                minuti.setEnabled(false);
                secondi.setEnabled(false);
            }
        }

        /*INTENT PER LA ECONDA ACTIVITY*/
        secondACT = new Intent(this.getApplicationContext(),TimerOverActivity.class);

        /*if(tempoRicettaFromIntent == "")
            tot = s * 1000 + m * 60000 + h * 3600000 + 1000;    //+ 1000 finale a livello estetico
        else{
            //tot = CONVERTIRE LA STRINGA IN INT E
            long totInMillis = tot;
            String hh = String.format("%02d", TimeUnit.MILLISECONDS.toHours(totInMillis));
            editOre.setText(hh);
            String mm = String.format("%02d", TimeUnit.MILLISECONDS.toMinutes(totInMillis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(totInMillis)));
            editMinuti.setText(mm);
            String ss = String.format("%02d", TimeUnit.MILLISECONDS.toSeconds(totInMillis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(totInMillis)));
            editSecondi.setText(ss);
        }*/

        pauseButton.setEnabled(false);

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String myH = Integer.toString(h);

                int h = ore.getValue();
                int m = minuti.getValue();
                int s = secondi.getValue();

                tot = s * 1000 + m * 60000 + h * 3600000 + 1000;

                if ((h != 0) || (m != 0) || (s != 0))
                {
                    if (tot != 0) {
                        r = new EventTimer();
                        tt = new Ticker(r, 1000, tot);
                        tt.Start();
                        setTime.setClickable(false);
                        timerAttivato = true;

                        isTimeFromRicetta = false;

                        pauseButton.setEnabled(true);

                        ore.setEnabled(false);
                        minuti.setEnabled(false);
                        secondi.setEnabled(false);
                    }
                }
            }
        });

        cancelTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!timerAttivato)
                    setTime.setClickable(true);

                ore.setValue(0);
                minuti.setValue(0);
                secondi.setValue(0);
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tt != null) {
                    tt.Pause(); //il metodo fa timer.stop()
                }
                editOre.setText("00");
                editMinuti.setText("00");
                editSecondi.setText("00");

                tot = 0;
                millis = 0;

                timerAttivato = false;
                setTime.setClickable(true);

                nomeRicettaFromIntent = "";

                pauseButton.setText("PAUSE");
                StatoBottoneAttuale=true; //true
                pauseButton.setEnabled(false);

                ore.setValue(0);
                minuti.setValue(0);
                secondi.setValue(0);

                ore.setEnabled(true);
                minuti.setEnabled(true);
                secondi.setEnabled(true);
            }
        });


        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerAttivato = true;
                setTime.setClickable(false);

                if(StatoBottoneAttuale)
                {
                    if((tot != 0) || (millis !=0)) {
                        setTime.setClickable(false);

                        if (tt != null) {
                            tt.Pause();
                        }
                        String h = String.format("%02d", TimeUnit.MILLISECONDS.toHours(millis));
                        editOre.setText(h);
                        String m = String.format("%02d", TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)));
                        editMinuti.setText(m);
                        String s = String.format("%02d", TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                        editSecondi.setText(s);

                        pauseButton.setText("RESUME");
                    }
                }
                else
                {
                    setTime.setClickable(false);
                    if (millis != 0) {
                        tt = new Ticker(r, 1000, millis);
                        tt.Start();
                        pauseButton.setText("PAUSE");
                        timerAttivato = true;

                        ore.setValue(0);
                        minuti.setValue(0);
                        secondi.setValue(0);
                    }
                }
                StatoBottoneAttuale=!StatoBottoneAttuale;
            }
        });
    }

    class  SetTimeTextBox implements  Runnable
    {
        String h,m,s;
        public  SetTimeTextBox(String h,String m,String s)
        {
            this.h=h;
            this.m=m;
            this.s=s;
        }
        @Override
        public void run() {

            editOre.setText(h);
            editMinuti.setText(m);
            editSecondi.setText(s);
        }
    }

    class EventTimer implements Responder
    {

        @Override
        public void Tick(long MillisecRimasti) {

            millis = MillisecRimasti;
            String h = String.format("%02d", TimeUnit.MILLISECONDS.toHours(millis));
            String m = String.format("%02d", TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)));
            String s = String.format("%02d", TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            runOnUiThread(new SetTimeTextBox(h,m,s));
        }

        @Override
        public void End() {
            secondACT.putExtra("name", nomeRicettaFromIntent);
            //startActivity(secondACT);
            int codiceIntent = 0;

            if(nomeRicettaFromIntent != "")
                codiceIntent = 123;

            //pauseButton.setEnabled(false);

            startActivityForResult(secondACT, codiceIntent);
            runOnUiThread(new SetTimeTextBox("00", "00", "00"));

            setTime.setClickable(true);
            timerAttivato = false;
        }

    }

    /*@Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }*/

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            super.onBackPressed();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.timer, menu);
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

        } else if (id == R.id.nav_spesa) {
            startActivity(new Intent(this, Spesa.class));
            finish();

        } else if (id == R.id.nav_calcolatrice) {
            startActivity(new Intent(this, Calcolatrice.class));
            finish();

        } else if (id == R.id.nav_timer) {
            //startActivity(new Intent(this, Timer.class));
        }
        else if (id == R.id.nav_calcolateglia) {
            startActivity(new Intent(this, CalcolaTeglia.class));
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 123) {
            nomeRicettaFromIntent = "";

            /*if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
                nomeRicettaFromIntent = "";
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }*/
        }
        timerAttivato = false;

        ore.setEnabled(true);
        minuti.setEnabled(true);
        secondi.setEnabled(true);

        pauseButton.setEnabled(false);
    }
}
