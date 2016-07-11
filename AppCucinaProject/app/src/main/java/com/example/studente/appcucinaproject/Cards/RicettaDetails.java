package com.example.studente.appcucinaproject.Cards;

import android.graphics.Bitmap;
import android.media.Image;

import com.example.studente.appcucinaproject.Ricettario.tabs.SecondiFragment;

/**
 * Created by Mattia on 22/04/2016.
 */
public class RicettaDetails {

    private String titleRicetta;
    private String descrRicetta;
    private Bitmap imgRicettaID;
    private boolean isPreferito;
    private String portata;

    /*public RicettaDetails(int imgRicetta,String title) {
        this.setTitle(title);
        this.setImageRicetta(imgRicetta);
    }*/

    public RicettaDetails(Bitmap imgRicetta, String title) {
        this.setTitle(title);
        this.setImageRicetta(imgRicetta);
    }

    public String getTitle() {
        return titleRicetta;
    }

    public void setTitle(String msg) {
        this.titleRicetta = msg;
    }
    
    public Bitmap getImageRicetta(){
        return imgRicettaID;
    }

    public void setImageRicetta(Bitmap imgID){
        this.imgRicettaID = imgID;
    }

    public String getDescrRicetta() {
        return descrRicetta;
    }   //prendo il testo di come realizzare la ricetta

    public void setDescrRicetta(String descrRicetta) {
        this.descrRicetta = descrRicetta;
    }   //setto i vari passaggi per realizzare la ricetta


    public boolean isPreferito() {
        return isPreferito;
    }       //per dire se è preferito o meno

    public void setPreferito(boolean preferito) {
        isPreferito = preferito;
    }       //settare il preferito


    public String getPortata() {
        return portata;
    }

    public void setPortata(String portata) {
        this.portata = portata;
    }
}