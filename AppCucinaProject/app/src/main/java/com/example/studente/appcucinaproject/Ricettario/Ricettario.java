package com.example.studente.appcucinaproject.Ricettario;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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
import com.example.studente.appcucinaproject.Home;
import com.example.studente.appcucinaproject.MenuDelGiorno.MenuDelGiorno;
import com.example.studente.appcucinaproject.R;
import com.example.studente.appcucinaproject.RicercaAvanzata.RicercaAV;
import com.example.studente.appcucinaproject.Spesa.Spesa;
import com.example.studente.appcucinaproject.Timer.Timer;
import com.example.studente.appcucinaproject.Ricettario.tabs.*;

public class Ricettario extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricettario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("Ricettario");
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
        getMenuInflater().inflate(R.menu.ricettario, menu);
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

            /*startActivity(new Intent(this, Ricettario.class));
            finish();*/

        }  else if (id == R.id.nav_ricerca) {
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AntipastiFragment(), "Antipasti");
        adapter.addFragment(new PrimiFragment(), "Primi");
        adapter.addFragment(new SecondiFragment(), "Secondi");
        adapter.addFragment(new DolciFragment(), "Dolci");
        viewPager.setAdapter(adapter);
    }


}
