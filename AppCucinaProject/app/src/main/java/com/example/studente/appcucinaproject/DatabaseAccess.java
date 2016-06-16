package com.example.studente.appcucinaproject;

/**
 * Created by Andrea Cavazzini DJ on 10/06/2016.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public String getTempoRicetta(String nomeRicetta){
        String tempoRicetta = "";
        Cursor cursor = database.rawQuery("SELECT tempo FROM ricetta where nome ='" + nomeRicetta +"';", null);
        cursor.moveToFirst();
        tempoRicetta = cursor.getString(0);

        cursor.close();
        return tempoRicetta;
    }

    public List<String> getQuotes() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT tempo FROM ricetta", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    /*public int getMINCalorie(){
        int minCalorie = -1;
        String minFromDB = "";
        Cursor cursor = database.rawQuery("SELECT min(calorie) FROM ricetta", null);
        cursor.moveToFirst();
        minFromDB = cursor.toString();
        if((minFromDB != null) || (!minFromDB.isEmpty()))
            minCalorie = Integer.parseInt(minFromDB);

        cursor.close();
        return minCalorie;
    }*/

    public int getMINCalorie(){
        int minTemp = -1;
        int minCalorie = -1;
        String minFromDB = "";

        //List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT calorie FROM ricetta", null);
        cursor.moveToFirst();

        //suppongo che il primo valore sia il minimo
        minFromDB = cursor.getString(0);
        if(minFromDB != null){
            if(!minFromDB.isEmpty()) {
                minTemp = Integer.parseInt(minFromDB);
                minCalorie = minTemp;

                while (!cursor.isAfterLast()) {

                    minFromDB = cursor.getString(0);
                    if ((minFromDB != null) || (!minFromDB.isEmpty()))
                        minTemp = Integer.parseInt(minFromDB);

                    if (minTemp < minCalorie)
                        minCalorie = minTemp;

                    cursor.moveToNext();
                }
            }
        }

        cursor.close();
        return minCalorie;
    }

    public int getMAXCalorie(){
        int maxCalorie = 0;
        Cursor cursor = database.rawQuery("SELECT max(calorie) FROM ricetta", null);
        String max = cursor.getString(0);
        maxCalorie = Integer.parseInt(max);

        cursor.close();
        return maxCalorie;
    }

    public int getMAXTempo(){
        int maxTempo = 0;

        int oreConvertite = 0;
        int minutiConvertiti = 0;
        int secondiConvertiti = 0;

        int totale;

        String tempoRicettaFromDB = "";

        Cursor cursor = database.rawQuery("SELECT tempo FROM ricetta", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            totale = 0;

            tempoRicettaFromDB = cursor.getString(0);
            String[] parts = tempoRicettaFromDB.split(":");
            String oreFromDB = parts[0];
            String minutiFromDB = parts[1];
            String secondiFromDB = parts[2];

            oreConvertite = Integer.parseInt(oreFromDB);
            minutiConvertiti = Integer.parseInt(minutiFromDB);
            secondiConvertiti = Integer.parseInt(secondiFromDB);

            if(oreConvertite > 0)
                totale = oreConvertite * 60; //converto in minuti

            totale += minutiConvertiti;

            if(totale > maxTempo)
                maxTempo = totale;

            cursor.moveToNext();
        }
        cursor.close();
        return maxTempo;
    }

    public int getMINTempo(){
        int minTempo = 0;

        int oreConvertite = 0;
        int minutiConvertiti = 0;
        int secondiConvertiti = 0;

        int totale;

        String tempoRicettaFromDB = "";

        Cursor cursor = database.rawQuery("SELECT tempo FROM ricetta", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            totale = 0;

            tempoRicettaFromDB = cursor.getString(0);
            String[] parts = tempoRicettaFromDB.split(":");
            String oreFromDB = parts[0];
            String minutiFromDB = parts[1];
            String secondiFromDB = parts[2];

            oreConvertite = Integer.parseInt(oreFromDB);
            minutiConvertiti = Integer.parseInt(minutiFromDB);
            secondiConvertiti = Integer.parseInt(secondiFromDB);

            if(oreConvertite > 0)
                totale = oreConvertite * 60; //converto in minuti

            totale += minutiConvertiti;

            if(totale < minTempo)
                minTempo = totale;

            cursor.moveToNext();
        }
        cursor.close();
        return minTempo;
    }


    /*public List<String> getResultFromRAV(String nomeRicetta, ) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT tempo FROM ricetta", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }*/

}
