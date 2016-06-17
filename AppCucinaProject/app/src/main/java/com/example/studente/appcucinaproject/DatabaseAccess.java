package com.example.studente.appcucinaproject;

/**
 * Created by Andrea Cavazzini on 10/06/2016.
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
        //se il ; alla fine della query viene messo, funziona ugualmente
        Cursor cursor = database.rawQuery("SELECT tempo FROM ricetta where nome ='" + nomeRicetta +"';", null);
        cursor.moveToFirst();
        tempoRicetta = cursor.getString(0);

        cursor.close();
        return tempoRicetta;
    }

    /*public List<String> getQuotes() {
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

    public ArrayList<String> getQuotes() {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT tempo FROM ricetta", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

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

        int maxCal = -1;
        int tempMaxCal = -1;
        String calFromDB = "";

        Cursor cursor = database.rawQuery("SELECT calorie FROM ricetta", null);
        cursor.moveToFirst();

        //leggo la prima riga alla colonna 0 del cursore
        calFromDB = cursor.getString(0);
        tempMaxCal = Integer.parseInt(calFromDB);

        //suppongo che il primo valore sia il massimo
        maxCal = tempMaxCal;

        while (!cursor.isAfterLast()) {
            //leggo ogni riga
            calFromDB = cursor.getString(0);
            tempMaxCal = Integer.parseInt(calFromDB);

            //se le calorie alla riga corrente sono maggiori del massimo allora settale come massimo
            if (tempMaxCal > maxCal)
                maxCal = tempMaxCal;

            cursor.moveToNext();
        }

        cursor.close();
        return maxCal;
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

        String[] parts;
        String oreFromDB;
        String minutiFromDB;
        String secondiFromDB;

        int oreConvertite = 0;
        int minutiConvertiti = 0;
        int secondiConvertiti = 0;

        int totale = 0;
        String tempoRicettaFromDB = "";

        Cursor cursor = database.rawQuery("SELECT tempo FROM ricetta", null);
        cursor.moveToFirst();

        //leggo il tempo dalla ricetta
        tempoRicettaFromDB = cursor.getString(0);

        //separo la stringa in ore, minuti e secondi
        parts = tempoRicettaFromDB.split(":");
        oreFromDB = parts[0];
        minutiFromDB = parts[1];
        secondiFromDB = parts[2];

        //converto ore, minuti e secondi da string a int
        oreConvertite = Integer.parseInt(oreFromDB);
        minutiConvertiti = Integer.parseInt(minutiFromDB);
        secondiConvertiti = Integer.parseInt(secondiFromDB);

        //se le ore non sono 0 le trasformo in minuti
        if(oreConvertite > 0)
            totale = oreConvertite * 60; //converto in minuti

        totale += minutiConvertiti;

        //suppongo che il primo valore di tempo sia il minimo
        minTempo = totale;

        while (!cursor.isAfterLast()) {

            totale = 0;

            tempoRicettaFromDB = cursor.getString(0);
            parts = tempoRicettaFromDB.split(":");
            oreFromDB = parts[0];
            minutiFromDB = parts[1];
            secondiFromDB = parts[2];

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


    public ArrayList<String> getAllResults(String nomeRicettaParam, String ingrediente1Param, String ingrediente2Param, String ingrediente3Param,
                                      boolean isAntipastoCheckedParam, boolean isPrimoCBCheckedParam, boolean isSecondoCBCheckedParam, boolean isDolceCBCheckedParam,
                                      int calorieMINValueSelectedParam, int calorieMAXValueSelectedParam,
                                      int tempoMINValueSelectedParam, int tempoMAXValueSelectedParam,
                                      int ID_DifficoltaParam){

        ArrayList<String> listResults = new ArrayList<>();
        String mySQLQuery = "";

        String minCalorieSelectedConv = Integer.toString(calorieMINValueSelectedParam);
        String maxCalorieSelectedConv = Integer.toString(calorieMAXValueSelectedParam);

        String minTempoSelectedConv =  Integer.toString(tempoMINValueSelectedParam);
        String maxTempoSelectedConv =  Integer.toString(tempoMAXValueSelectedParam);

        //CONTROLLARE QUESTA QUERY
        mySQLQuery = "SELECT nome" +
                     "FROM ricetta r, ingredienti i, appartiene a, difficoltà d, portata p" +
                     "WHERE " +
                     "AND r.calorie BETWEEN " + minCalorieSelectedConv +" AND " + maxCalorieSelectedConv;

        if(!nomeRicettaParam.isEmpty()){
            mySQLQuery = mySQLQuery.concat("AND r.nome ='" + nomeRicettaParam +"';");
        }

        //String ingrediente1Param, String ingrediente2Param, String ingrediente3Param

        //controllare indice
        if(ID_DifficoltaParam != 4){
            String idDiffConvert = Integer.toString(ID_DifficoltaParam);
            mySQLQuery = mySQLQuery.concat("AND r.id_difficolta=" + idDiffConvert);
        }

        //se voglio un antipasto
        if(isAntipastoCheckedParam){
            //confronto l'id della tabella ricetta con l'id della tabella portata
            //nella query nidificata cerco l'id che corrisponda ad antipasto
            mySQLQuery = mySQLQuery.concat("AND r.id_portata = (SELECT id_portata " +
                                                                "FROM portata " +
                                                                "WHERE tipologia='Antipasto';");
        }

        //se voglio un primo
        if(isPrimoCBCheckedParam){
            //nella query nidificata cerco l'id che corrisponda a primo
            mySQLQuery = mySQLQuery.concat("AND r.id_portata = (SELECT id_portata " +
                                                                    "FROM portata " +
                                                                    "WHERE tipologia='Primo';");
        }

        if(isSecondoCBCheckedParam){
            //nella query nidificata cerco l'id che corrisponda a secondo
            mySQLQuery = mySQLQuery.concat("AND r.id_portata = (SELECT id_portata " +
                                                                "FROM portata " +
                                                                "WHERE tipologia='Secondo';");
        }

        if(isDolceCBCheckedParam){
            //nella query nidificata cerco l'id che corrisponda a dolce
            mySQLQuery = mySQLQuery.concat("AND r.id_portata = (SELECT id_portata " +
                                                                "FROM portata " +
                                                                "WHERE tipologia='Dolce';");
        }

        /*!!---- SELECT id_ricetta, nome_ricetta
        FROM ricetta r, ingredienti i, appartiene a, difficoltà d, portata p
        WHERE a.id_ingrediente = i.id_ingrediente
        AND a.id_ricetta = r.id_ricetta

        AND i.id_ingrediente = values_textview1
		AND p.id_ingrediente = in (values_textview1, values_textview2, values_textview3) ---!!*/

        String tempoRicettaFromDB = "";
        int tempoRicettaMinuti = 0;
        String[] parts;
        String oreFromDB;
        String minutiFromDB;
        String secondiFromDB;

        int oreConvertite = 0;
        int minutiConvertiti = 0;
        int secondiConvertiti = 0;

        Cursor cursorTempo = database.rawQuery("SELECT tempo FROM ricetta", null);
        cursorTempo.moveToFirst();

        //leggo il tempo dalla ricetta
        tempoRicettaFromDB = cursorTempo.getString(0);

        //separo la stringa in ore, minuti e secondi
        /*parts = tempoRicettaFromDB.split(":");
        oreFromDB = parts[0];
        minutiFromDB = parts[1];
        secondiFromDB = parts[2];

        //converto ore, minuti e secondi da string a int
        oreConvertite = Integer.parseInt(oreFromDB);
        minutiConvertiti = Integer.parseInt(minutiFromDB);
        secondiConvertiti = Integer.parseInt(secondiFromDB);

        //se le ore non sono 0 le trasformo in minuti
        if(oreConvertite > 0)
            tempoRicettaMinuti = oreConvertite * 60; //converto in minuti

        tempoRicettaMinuti += minutiConvertiti;*/


        Cursor cursor = database.rawQuery(mySQLQuery, null);
        cursor.moveToFirst();

        //leggo tutti i risultati e metto in una lista
        while (!cursor.isAfterLast() && !cursorTempo.isLast()) {

            tempoRicettaFromDB = cursor.getString(0);
            parts = tempoRicettaFromDB.split(":");
            oreFromDB = parts[0];
            minutiFromDB = parts[1];
            secondiFromDB = parts[2];

            oreConvertite = Integer.parseInt(oreFromDB);
            minutiConvertiti = Integer.parseInt(minutiFromDB);
            secondiConvertiti = Integer.parseInt(secondiFromDB);

            if(oreConvertite > 0)
                tempoRicettaMinuti = oreConvertite * 60; //converto in minuti

            tempoRicettaMinuti += minutiConvertiti;

            //se il tempo della riga è compreso tra i valori passati come parametro allora aggiungo la ricetta alla lista dei risultati
            if((tempoRicettaMinuti < tempoMAXValueSelectedParam) && (tempoRicettaMinuti > tempoMINValueSelectedParam ))
                listResults.add(cursor.getString(0));

            cursor.moveToNext();
            cursorTempo.moveToNext();
        }
        cursor.close();
        cursorTempo.close();

        return listResults;
    }

    //METODI CON OPERATORI NELLE QUERY
    /*public int getMINCalorie(){
        int minCalorie = -1;
        String minFromDB = "";
        Cursor cursor = database.rawQuery("SELECT min(calorie) FROM ricetta", null);
        cursor.moveToFirst();
        minFromDB = cursor.getString(0);
        if((minFromDB != null) || (!minFromDB.isEmpty()))
            minCalorie = Integer.parseInt(minFromDB);

        cursor.close();
        return minCalorie;
    }*/

    /*public int getMAXCalorie(){
        int maxCalorie = 0;
        Cursor cursor = database.rawQuery("SELECT max(calorie) FROM ricetta", null);
        String max = cursor.getString(0);
        maxCalorie = Integer.parseInt(max);

        cursor.close();
        return maxCalorie;
    }*/

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
