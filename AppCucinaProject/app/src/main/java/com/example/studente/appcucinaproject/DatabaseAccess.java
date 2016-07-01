package com.example.studente.appcucinaproject;

/**
 * Created by Andrea Cavazzini on 10/06/2016.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.studente.appcucinaproject.Cards.RicettaDetails;

import java.io.ByteArrayInputStream;
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

    public ArrayList<Bitmap> getImage() {
        ArrayList<Bitmap> data = new ArrayList<Bitmap>();
        Cursor cursor = database.rawQuery("SELECT immagini FROM ricetta", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            byte[] blob =(cursor.getBlob(0));
            ByteArrayInputStream inputStream = new ByteArrayInputStream(blob);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            data.add(bitmap);
            cursor.moveToNext();
            //break;  // Assumption: name is unique
        }
        cursor.close();
        return data;
    }

    public ArrayList<RicettaDetails> getRicettaAntipastoConImage(){
        ArrayList<RicettaDetails> list = new ArrayList<RicettaDetails>();
        RicettaDetails RICETTA;

        Cursor cursor = database.rawQuery("SELECT nome,immagini,descrizione FROM ricetta WHERE id_portata=1 ORDER BY nome", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            byte[] blob =(cursor.getBlob(1));
            ByteArrayInputStream inputStream = new ByteArrayInputStream(blob);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            RICETTA = new RicettaDetails(bitmap,cursor.getString(0));
            RICETTA.setDescrRicetta(cursor.getString(2));
            list.add(RICETTA);
            cursor.moveToNext();
        }
        cursor.close();

        return list;
    }

    public ArrayList<RicettaDetails> getRicettaPrimiConImage(){
        ArrayList<RicettaDetails> list = new ArrayList<RicettaDetails>();
        RicettaDetails RICETTA;

        Cursor cursor = database.rawQuery("SELECT nome,immagini,descrizione FROM ricetta WHERE id_portata=2 ORDER BY nome", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            byte[] blob =(cursor.getBlob(1));
            ByteArrayInputStream inputStream = new ByteArrayInputStream(blob);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            RICETTA = new RicettaDetails(bitmap,cursor.getString(0));

            RICETTA.setDescrRicetta(cursor.getString(2));
            list.add(RICETTA);
            cursor.moveToNext();
        }
        cursor.close();

        return list;
    }


    public ArrayList<RicettaDetails> getRicettaSecondiConImage(){
        ArrayList<RicettaDetails> list = new ArrayList<RicettaDetails>();
        RicettaDetails RICETTA;

        Cursor cursor = database.rawQuery("SELECT nome,immagini,descrizione FROM ricetta WHERE id_portata=3 ORDER BY nome", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            byte[] blob =(cursor.getBlob(1));
            ByteArrayInputStream inputStream = new ByteArrayInputStream(blob);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            RICETTA = new RicettaDetails(bitmap,cursor.getString(0));
            RICETTA.setDescrRicetta(cursor.getString(2));
            list.add(RICETTA);
            cursor.moveToNext();
        }
        cursor.close();

        return list;
    }


    public ArrayList<RicettaDetails> getRicettaDolciConImage(){
        ArrayList<RicettaDetails> list = new ArrayList<RicettaDetails>();
        RicettaDetails RICETTA;

        Cursor cursor = database.rawQuery("SELECT nome,immagini,descrizione FROM ricetta WHERE id_portata=4 ORDER BY nome", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            byte[] blob =(cursor.getBlob(1));
            ByteArrayInputStream inputStream = new ByteArrayInputStream(blob);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            RICETTA = new RicettaDetails(bitmap,cursor.getString(0));
            RICETTA.setDescrRicetta(cursor.getString(2));
            list.add(RICETTA);
            cursor.moveToNext();
        }
        cursor.close();

        return list;
    }






    public ArrayList<String> getRicettaAntipasto() {        //metodo per prendere tutti i nomi degli antipasti
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT nome FROM ricetta WHERE id_portata=1 ORDER BY nome", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();

        if(list.isEmpty()){
            list.add(0,"nessun risultato");
        }

        return list;
    }


    public ArrayList<String> getRicettaPrimo() {            //metodo per prendere tutti i nomi dei primi
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT nome FROM ricetta WHERE id_portata=2 ORDER BY nome", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();

        if(list.isEmpty()){
            list.add(0,"nessun risultato");
        }

        return list;
    }


    public ArrayList<String> getRicettaSeconda() {          //metodo per prendere tutti i nomi dei secondi
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT nome FROM ricetta WHERE id_portata=3 ORDER BY nome", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();

        if(list.isEmpty()){
            list.add(0,"nessun risultato");
        }

        return list;
    }


    public ArrayList<String> getRicettaDolce() {        //metodo per prendere tutti i nomi dei dolci
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT nome FROM ricetta WHERE id_portata=4 ORDER BY nome", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();

        if(list.isEmpty()){
            list.add(0,"nessun risultato");
        }

        return list;
    }


    //metodi per suddivisione ricette della visualizzazione ricerca av nelle varie tabs

    /*public String getRicettaAntipastoVisualizzazione(String nome_ricetta) {        //metodo per prendere tutti i nomi dei dolci
        String nRicetta = "";

        Cursor cursor = database.rawQuery("SELECT nome FROM ricetta WHERE id_portata=1 AND nome ='" + nome_ricetta +"' ", null);
        cursor.moveToFirst();

        nRicetta = cursor.getString(0);

        cursor.close();

        if(nRicetta.isEmpty()){
            nRicetta="nessun risultato";
        }

        return nRicetta;
    }


    public String getRicettaPrimiVisualizzazione(String nome_ricetta) {        //metodo per prendere tutti i nomi dei dolci
        String nRicetta = "";

        Cursor cursor = database.rawQuery("SELECT nome FROM ricetta WHERE id_portata=2 AND nome ='" + nome_ricetta +"' ", null);
        cursor.moveToFirst();

        nRicetta = cursor.getString(0);

        cursor.close();

        if(nRicetta.isEmpty()){
            nRicetta="nessun risultato";
        }

        return nRicetta;
    }

    public String getRicettaSecondiVisualizzazione(String nome_ricetta) {        //metodo per prendere tutti i nomi dei dolci
        String nRicetta = "";

        Cursor cursor = database.rawQuery("SELECT nome FROM ricetta WHERE id_portata=3 AND nome ='" + nome_ricetta +"';", null);
        cursor.moveToFirst();


        nRicetta = cursor.getString(0);

        cursor.close();

        if(nRicetta.isEmpty()){
            nRicetta="nessun risultato";
        }

        return nRicetta;
    }


    public String getRicettaDolciVisualizzazione(String nome_ricetta) {        //metodo per prendere tutti i nomi dei dolci
        String nRicetta = "";

        Cursor cursor = database.rawQuery("SELECT nome FROM ricetta WHERE id_portata=4 AND nome ='" + nome_ricetta +"';", null);
        cursor.moveToFirst();

        nRicetta = cursor.getString(0);

        cursor.close();

        if(nRicetta.isEmpty()){
            nRicetta="nessun risultato";
        }

        return nRicetta;
    }*/



    ////

    public String getNpersone(String nomeRicetta)
    {
        String nRicetta = "";
        //se il ; alla fine della query viene messo, funziona ugualmente
        Cursor cursor = database.rawQuery("SELECT nPersone FROM ricetta where nome ='" + nomeRicetta +"';", null);
        cursor.moveToFirst();
        nRicetta = cursor.getString(0);

        cursor.close();
        return nRicetta;
    }

    public String getCalorie(String nomeRicetta)
    {
        String calorie = "";
        //se il ; alla fine della query viene messo, funziona ugualmente
        Cursor cursor = database.rawQuery("SELECT calorie FROM ricetta where nome ='" + nomeRicetta +"';", null);
        cursor.moveToFirst();
        calorie = cursor.getString(0);

        cursor.close();
        return calorie;
    }

    public String getDifficolta(String nomeRicetta)
    {
        String difficolta = "";
        //se il ; alla fine della query viene messo, funziona ugualmente
        Cursor cursor = database.rawQuery("SELECT difficolta FROM ricetta,difficolta where ricetta.id_difficolta=difficolta.id_difficolta and ricetta.nome ='" + nomeRicetta +"';", null);
        cursor.moveToFirst();
        difficolta = cursor.getString(0);

        cursor.close();
        return difficolta;
    }

    public String getPortata(String nomeRicetta)
    {
        String portata = "";
        //se il ; alla fine della query viene messo, funziona ugualmente
        Cursor cursor = database.rawQuery("SELECT tipologia FROM ricetta,portata where ricetta.id_portata=portata.id_portata and ricetta.nome ='" + nomeRicetta +"';", null);
        cursor.moveToFirst();
        portata = cursor.getString(0);

        cursor.close();
        return portata;
    }

    public String getDescrizione(String nomeRicetta)
    {
        String descrizione = "";
        //se il ; alla fine della query viene messo, funziona ugualmente
        Cursor cursor = database.rawQuery("SELECT descrizione FROM ricetta where ricetta.nome ='" + nomeRicetta +"';", null);
        cursor.moveToFirst();
        descrizione = cursor.getString(0);

        cursor.close();
        return descrizione;
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

    public ArrayList<String> getIngredienti(String nomeRicetta) {          //metodo per prendere tutti i nomi dei secondi
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT a.quantita,i.nome FROM appartenere a,ricetta r,ingrediente i WHERE i.id_ingrediente=a.id_ingrediente and r.id_ricetta=a.id_ricetta and r.nome='" + nomeRicetta +"';", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String qi = cursor.getString(0) + " " + cursor.getString(1);
            list.add(qi);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
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

        mySQLQuery = "SELECT nome, tempo " +
                     "FROM ricetta r " +
                     "WHERE r.calorie BETWEEN " + minCalorieSelectedConv +" AND " + maxCalorieSelectedConv + " ";

        if(!nomeRicettaParam.isEmpty()){
            //se la prima lettera non è maiuscola la metto maiuscola
            if(!Character.isUpperCase(nomeRicettaParam.charAt(0)))
                nomeRicettaParam = nomeRicettaParam.substring(0, 1).toUpperCase() + nomeRicettaParam.substring(1);

            mySQLQuery = mySQLQuery.concat("AND r.nome ='" + nomeRicettaParam +"' ");
        }

        //String ingrediente1Param, String ingrediente2Param, String ingrediente3Param

        //se la difficoltà non è 4 ovvero qualsiasi
        if(ID_DifficoltaParam != 4){
            String idDiffConvert = Integer.toString(ID_DifficoltaParam);
            mySQLQuery = mySQLQuery.concat("AND r.id_difficolta=" + idDiffConvert + " ");
        }

        //se voglio un antipasto
        if(isAntipastoCheckedParam){
            //confronto l'id della tabella ricetta con l'id della tabella portata
            //nella query nidificata cerco l'id che corrisponda ad antipasto
            mySQLQuery = mySQLQuery.concat("AND r.id_portata IN (SELECT id_portata " +
                                                                "FROM portata " +
                                                                "WHERE tipologia='Antipasto') ");
        }

        //se voglio un primo
        if(isPrimoCBCheckedParam){
            //nella query nidificata cerco l'id che corrisponda a primo
            mySQLQuery = mySQLQuery.concat("AND r.id_portata IN (SELECT id_portata " +
                                                                    "FROM portata " +
                                                                    "WHERE tipologia='Primo') ");
        }

        if(isSecondoCBCheckedParam){
            //nella query nidificata cerco l'id che corrisponda a secondo
            mySQLQuery = mySQLQuery.concat("AND r.id_portata IN (SELECT id_portata " +
                                                                "FROM portata " +
                                                                "WHERE tipologia='Secondo') ");
        }

        if(isDolceCBCheckedParam){
            //nella query nidificata cerco l'id che corrisponda a dolce
            mySQLQuery = mySQLQuery.concat("AND r.id_portata IN (SELECT id_portata " +
                                                                "FROM portata " +
                                                                "WHERE tipologia='Dolce') ");
        }

        //restituisce tutte le ricette con ingrediente 1
        if(!ingrediente1Param.isEmpty()){
            //se la prima lettera non è maiuscola la metto maiuscola
            if(!Character.isUpperCase(ingrediente1Param.charAt(0)))
                ingrediente1Param = ingrediente1Param.substring(0, 1).toUpperCase() + ingrediente1Param.substring(1);

            mySQLQuery = mySQLQuery.concat("AND r.id_ricetta IN (SELECT a.id_ricetta " +
                                            "FROM appartenere a, ingrediente i " +
                                            "WHERE a.id_ingrediente = i.id_ingrediente " +
                                            "AND i.nome= '" + ingrediente1Param + "') ");
        }

        //restituisce tutte le ricette con ingrediente 2
        if(!ingrediente2Param.isEmpty()){
            //se la prima lettera non è maiuscola la metto maiuscola
            if(!Character.isUpperCase(ingrediente2Param.charAt(0)))
                ingrediente2Param = ingrediente2Param.substring(0, 1).toUpperCase() + ingrediente2Param.substring(1);

            mySQLQuery = mySQLQuery.concat("AND r.id_ricetta IN (SELECT a.id_ricetta " +
                                            "FROM appartenere a, ingrediente i " +
                                            "WHERE a.id_ingrediente = i.id_ingrediente " +
                                            "AND i.nome= '" + ingrediente2Param + "') ");
        }


        //restituisce tutte le ricette con ingrediente 3
        if(!ingrediente3Param.isEmpty()){
            //se la prima lettera non è maiuscola la metto maiuscola
            if(!Character.isUpperCase(ingrediente3Param.charAt(0)))
                ingrediente3Param = ingrediente3Param.substring(0, 1).toUpperCase() + ingrediente3Param.substring(1);

            mySQLQuery = mySQLQuery.concat("AND r.id_ricetta IN (SELECT a.id_ricetta " +
                                        "FROM appartenere a, ingrediente i " +
                                        "WHERE a.id_ingrediente = i.id_ingrediente " +
                                        "AND i.nome= '" + ingrediente3Param + "') ");
        }

        mySQLQuery = mySQLQuery.concat("ORDER BY r.calorie, r.nome ");

        ArrayList<String> listTempQuery = new ArrayList<>();
        ArrayList<String> listTempTempo = new ArrayList<>();

        Cursor cursor = database.rawQuery(mySQLQuery, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            listTempQuery.add(cursor.getString(0));
            listTempTempo.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();

        if(!listTempQuery.isEmpty())
        {
            String tempoRicettaFromDB = "";
            int tempoRicettaMinuti;
            String[] parts;
            String oreFromDB;
            String minutiFromDB;
            String secondiFromDB;

            int oreConvertite = 0;
            int minutiConvertiti = 0;
            int secondiConvertiti = 0;

            for(int i =0; i<listTempQuery.size(); i++) {

                tempoRicettaMinuti = 0;
                tempoRicettaFromDB = listTempTempo.get(i);
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
                if((tempoRicettaMinuti <= tempoMAXValueSelectedParam) && (tempoRicettaMinuti >= tempoMINValueSelectedParam )){
                    listResults.add(listTempQuery.get(i));
                }
            }
        }
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

     /*
        Cursor cursorTempo = database.rawQuery("SELECT tempo FROM ricetta", null);
        cursorTempo.moveToFirst();

        //leggo il tempo dalla ricetta
        tempoRicettaFromDB = cursorTempo.getString(0);

        //leggo tutti i risultati e metto in una lista
        while (!cursor.isAfterLast() && !cursorTempo.isLast()) {

            tempoRicettaFromDB = cursorTempo.getString(0);
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
            if((tempoRicettaMinuti <= tempoMAXValueSelectedParam) && (tempoRicettaMinuti >= tempoMINValueSelectedParam )){
                listResults.add(cursor.getString(0));
            }

            cursor.moveToNext();
            cursorTempo.moveToNext();
        }
        cursor.close();
        cursorTempo.close();*/

}
