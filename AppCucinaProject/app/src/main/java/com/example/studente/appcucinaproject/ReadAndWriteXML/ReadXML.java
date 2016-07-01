package com.example.studente.appcucinaproject.ReadAndWriteXML;

/**
 * Created by Mattia on 30/06/2016.
 */

import android.os.Environment;

import com.example.studente.appcucinaproject.Cards.RicettaDetails;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;


public class ReadXML {

    ArrayList<RicettaDetails> listPreferiti;
    RicettaDetails ricetta= new RicettaDetails(null,null);

    public ArrayList<RicettaDetails> ReadXMLtoObject(){
        try {

            File fXmlFile = new File(Environment.getExternalStorageDirectory()+"\\preferiti.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("ricetta");


            //CREO LA LISTA DI OGGETTI
            listPreferiti= new ArrayList<RicettaDetails>();

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    //QUI CREARE LA LISTA DI OGGETTI

                    ricetta.setTitle(eElement.getElementsByTagName("titolo").item(0).getTextContent());
                    listPreferiti.add(ricetta);

                    /*System.out.println("Staff id : " + eElement.getAttribute("id"));
                    System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
                    System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
                    System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
                    System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());*/

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listPreferiti;
    }


    public boolean ReadXML_Particular_Object(String titolo){    //metodo che ritorna TRUE se il titolo di ricetta immesso corrispondea quello trovato nel file XML

        boolean checkPreferito=false;

        try {

            File fXmlFile = new File(Environment.getExternalStorageDirectory()+"\\preferiti.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("ricetta");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    if(eElement.getElementsByTagName("titolo").item(0).getTextContent().equals(titolo)){ //controllo se l'elemento trovato è quello che ho immesso come parametro
                        checkPreferito = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return checkPreferito;
    }


}
