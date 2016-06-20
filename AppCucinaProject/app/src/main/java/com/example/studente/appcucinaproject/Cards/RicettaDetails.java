package com.example.studente.appcucinaproject.Cards;

import android.media.Image;

/**
 * Created by Mattia on 22/04/2016.
 */
public class RicettaDetails {

    private String titleRicetta;
    private String descrRicetta;
    private int imgRicettaID;
    private boolean isPreferito;

    public RicettaDetails(int imgRicetta,String title) {
        this.setTitle(title);
        this.setImageRicetta(imgRicetta);
    }

    public String getTitle() {
        return titleRicetta;
    }

    public void setTitle(String msg) {
        this.titleRicetta = msg;
    }
    
    public int getImageRicetta(){
        return imgRicettaID;
    }

    public void setImageRicetta(int imgID){
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
}