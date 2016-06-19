package com.example.studente.appcucinaproject.RicercaAvanzata;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.studente.appcucinaproject.R;
import com.example.studente.appcucinaproject.RicercaAvanzata.tabs_visualizzazione.AntipastiFragment_visualizzazione;
import com.example.studente.appcucinaproject.RicercaAvanzata.tabs_visualizzazione.DolciFragment_visualizzazione;
import com.example.studente.appcucinaproject.RicercaAvanzata.tabs_visualizzazione.PrimiFragment_visualizzazione;
import com.example.studente.appcucinaproject.RicercaAvanzata.tabs_visualizzazione.SecondiFragment_visualizzazione;


public class Visualizzazione_Ricerca_av extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizzazione__ricerca_av);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarVisualizzazioneRAV);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpagerRAV);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabsVisualizzazioneRAV);
        tabLayout.setupWithViewPager(viewPager);

        setTitle("Risultati");
    }



    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AntipastiFragment_visualizzazione(), "Antipasti");
        adapter.addFragment(new PrimiFragment_visualizzazione(), "Primi");
        adapter.addFragment(new SecondiFragment_visualizzazione(), "Secondi");
        adapter.addFragment(new DolciFragment_visualizzazione(), "Dolci");
        viewPager.setAdapter(adapter);
    }

}
