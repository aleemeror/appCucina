package com.example.studente.appcucinaproject;

import android.content.Context;

import com.example.studente.appcucinaproject.Cards.RicettaDetails;

import java.util.ArrayList;

/**
 * Created by Mattia on 28/06/2016.
 */
public class DatiCondivisi {

    public ArrayList<String> ricetteVis;
    public ArrayList<String> antipastiVis;
    public ArrayList<String> primiVis;
    public ArrayList<String> secondiVis;
    public ArrayList<String> dolciVis;
    public ArrayList<String> PreferitiTitoli;
    Context cx;

    private DatabaseAccess myDatabaseAccess;


    public DatiCondivisi() {

    }

    public ArrayList<String> getRicetteVis() {
        return ricetteVis;
    }


    public ArrayList<String> getPreferitiTitoli() {
        return PreferitiTitoli;
    }

    public void setPreferitiTitoli(ArrayList<String> preferitiTitoli) {
        PreferitiTitoli = preferitiTitoli;
    }

    public void deleteOnePreferito(String titolo){  //per eliminare un certo elemento dai preferiti
        boolean check=false;
        int i=0;

        while(!check && i<PreferitiTitoli.size()){
            if(PreferitiTitoli.get(i).equals(titolo)){
                PreferitiTitoli.remove(i);
                check=true;
            }
        }

    }

    public void setRicetteVis(ArrayList<String> ricette_vis) {
        /*this.ricetteVis = ricette_vis;


        myDatabaseAccess = DatabaseAccess.getInstance(cx);
        myDatabaseAccess.open();

        for(int i=0;i<ricetteVis.size();i++){

            String antipasto = myDatabaseAccess.getRicettaAntipastoVisualizzazione(ricetteVis.get(i));

            if(antipasto != "nessun risultato"){
                antipastiVis.add(antipasto);
            }
        }

        for(int i=0;i<ricetteVis.size();i++){

            String primo = myDatabaseAccess.getRicettaPrimiVisualizzazione(ricetteVis.get(i));

            if(primo != "nessun risultato"){
                primiVis.add(primo);
            }

        }

        for(int i=0;i<ricetteVis.size();i++){

            String secondo = myDatabaseAccess.getRicettaSecondiVisualizzazione(ricetteVis.get(i));

            if(secondo != "nessun risultato"){
                secondiVis.add(secondo);
            }
        }

        for(int i=0;i<ricetteVis.size();i++){

            String dolce = myDatabaseAccess.getRicettaDolciVisualizzazione(ricetteVis.get(i));

            if(dolce != "nessun risultato"){
                dolciVis.add(dolce);
            }
        }

        myDatabaseAccess.close();*/
    }


    public void ClearAll(){ //metodo per pulire tutte le liste
        ricetteVis.clear();
        primiVis.clear();
        secondiVis.clear();
        dolciVis.clear();
    }


}
