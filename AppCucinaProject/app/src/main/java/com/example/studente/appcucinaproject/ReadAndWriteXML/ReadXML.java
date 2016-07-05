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
import java.io.FileReader;
import java.util.ArrayList;


public class ReadXML {

    ArrayList<RicettaDetails> listPreferiti;
    RicettaDetails ricetta;
    WriteXML checkWrite;

    public ReadXML(){   //COSTRUTTORE
        ricetta= new RicettaDetails(null,null);
        checkWrite = new WriteXML();
        listPreferiti= new ArrayList<RicettaDetails>();
    }


    public ArrayList<RicettaDetails> ReadXMLtoObject(){
        try {

            if(checkWrite.CheckFolderAndFile()){

                File fXmlFile = new File(Environment.getExternalStorageDirectory()+File.separator + "AppCucina"+File.separator+"preferiti.xml"); //File fXmlFile = new File(Environment.getExternalStorageDirectory()+File.separator + "AppCucina"+File.separator+"preferiti.xml");

                FileReader fr = new FileReader(fXmlFile);
                if (fr.read()==-1){
                    fr.close();
                    return null;
                } else{
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(fXmlFile);

                    //optional, but recommended
                    //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
                    doc.getDocumentElement().normalize();

                    NodeList nList = doc.getElementsByTagName("ricetta");

                    for (int temp = 0; temp < nList.getLength(); temp++) {

                        Node nNode = nList.item(temp);

                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                            Element eElement = (Element) nNode;

                            //QUI CREARE LA LISTA DI OGGETTI

                            ricetta.setTitle(eElement.getElementsByTagName("titolo").item(0).getTextContent());
                            listPreferiti.add(ricetta);

                        }
                    }

                    fr.close();
                    return listPreferiti;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public boolean ReadXML_Particular_Object(String titolo){    //metodo che ritorna TRUE se il titolo di ricetta immesso corrispondea quello trovato nel file XML
        boolean check=false;

        try {

            if(checkWrite.CheckFolderAndFile()){

                File fXmlFile = new File(Environment.getExternalStorageDirectory()+File.separator + "AppCucina"+File.separator+"preferiti.xml"); //File fXmlFile = new File(Environment.getExternalStorageDirectory()+File.separator + "AppCucina"+File.separator+"preferiti.xml");

                FileReader fr = new FileReader(fXmlFile);
                if (fr.read()==-1){
                    fr.close();
                    check=false;
                    return check;
                } else{
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(fXmlFile);

                    //optional, but recommended
                    //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
                    doc.getDocumentElement().normalize();

                    NodeList nList = doc.getElementsByTagName("ricetta");

                    for (int temp = 0; temp < nList.getLength(); temp++) {

                        Node nNode = nList.item(temp);

                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                            Element eElement = (Element) nNode;

                            //QUI CREARE LA LISTA DI OGGETTI

                            if(eElement.getElementsByTagName("titolo").item(0).getTextContent().equals(titolo)){
                                check=true;
                                break;
                            }

                        }
                    }

                    fr.close();
                    return check;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check;
    }


}
