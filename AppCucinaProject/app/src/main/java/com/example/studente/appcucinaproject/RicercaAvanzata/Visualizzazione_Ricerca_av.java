package com.example.studente.appcucinaproject.RicercaAvanzata;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.studente.appcucinaproject.DatabaseAccess;
import com.example.studente.appcucinaproject.DatiCondivisi;
import com.example.studente.appcucinaproject.R;
import com.example.studente.appcucinaproject.RicercaAvanzata.tabs_visualizzazione.AntipastiFragment_visualizzazione;
import com.example.studente.appcucinaproject.RicercaAvanzata.tabs_visualizzazione.DolciFragment_visualizzazione;
import com.example.studente.appcucinaproject.RicercaAvanzata.tabs_visualizzazione.PrimiFragment_visualizzazione;
import com.example.studente.appcucinaproject.RicercaAvanzata.tabs_visualizzazione.SecondiFragment_visualizzazione;

import java.util.ArrayList;


public class Visualizzazione_Ricerca_av extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<String> listResults = new ArrayList<>();
    private ArrayList<String> listAntipasti = new ArrayList<>();
    private ArrayList<String> listPrimis = new ArrayList<>();
    private ArrayList<String> listSecondi = new ArrayList<>();
    private ArrayList<String> listDolci = new ArrayList<>();

    DatiCondivisi dc;

    private DatabaseAccess myDatabaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizzazione__ricerca_av);

        //dc= new DatiCondivisi(); //

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarVisualizzazioneRAV);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpagerRAV);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabsVisualizzazioneRAV);
        tabLayout.setupWithViewPager(viewPager);

        setTitle("Risultati");

        listResults = getIntent().getStringArrayListExtra("risultati");
    }



    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AntipastiFragment_visualizzazione(), "Antipasti");
        adapter.addFragment(new PrimiFragment_visualizzazione(), "Primi");
        adapter.addFragment(new SecondiFragment_visualizzazione(), "Secondi");
        adapter.addFragment(new DolciFragment_visualizzazione(), "Dolci");
        viewPager.setAdapter(adapter);
    }


   /*@Override
    public void onBackPressed() {
        super.onBackPressed();
       // dc.clearAll(); //metodo per pulire le liste della classe Dati condivisi quando si torna indietro alla selezione dei campi da ricercare
    }*/
}
