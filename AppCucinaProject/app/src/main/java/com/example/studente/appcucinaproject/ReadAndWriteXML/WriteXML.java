package com.example.studente.appcucinaproject.ReadAndWriteXML;

/**
 * Created by Mattia on 30/06/2016.
 */

import android.os.Environment;

import java.io.File;
import java.io.FileReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class WriteXML {

    Document doc;

    public boolean CheckFolderAndFile(){ //da richiamare sempre quando si fa operazione su file

        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "AppCucina");
        boolean success_folder;
        boolean success_file;
        if (!folder.exists()) {
            success_folder = folder.mkdir();

            File fileXML = new File(Environment.getExternalStorageDirectory() +
                    File.separator + "AppCucina"+File.separator + "preferiti.xml");
            try {
                success_file=fileXML.createNewFile();
            }
            catch (Exception e){
                e.printStackTrace();
                success_file=false;
            }
        }else{
            success_folder=true;

            File fileXML = new File(Environment.getExternalStorageDirectory() +
                    File.separator + "AppCucina"+File.separator + "preferiti.xml");

            if(!fileXML.exists()){
                try {
                    success_file=fileXML.createNewFile();
                }
                catch (Exception e){
                    e.printStackTrace();
                    success_file=false;
                }
            }else{
                success_file=true;
            }

        }
        if (success_folder && success_file) {
            return true;
        }else{
            return false;
        }
    }


    public boolean WriteObjectToXML(String titolo){
        try {

            boolean check = CheckFolderAndFile();

            if(check){

                File fXmlFile = new File(Environment.getExternalStorageDirectory()+File.separator+"AppCucina"+File.separator+"preferiti.xml");
                FileReader fr = new FileReader(fXmlFile);
                if (fr.read()==-1){//il file è vuoto
                    fr.close();

                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                    // root elements
                    doc = docBuilder.newDocument();
                    Element rootElement = doc.createElement("preferiti");
                    doc.appendChild(rootElement);

                    // staff elements
                    Element ricetta = doc.createElement("ricetta");
                    rootElement.appendChild(ricetta);

                    // set attribute to staff element
            /*Attr attr = doc.createAttribute("title");
            attr.setValue("1");
            ricetta.setAttributeNode(attr);*/

                    // shorten way
                    // staff.setAttribute("id", "1");

                    // firstname elements
                    Element firstname = doc.createElement("titolo");
                    firstname.appendChild(doc.createTextNode(titolo));
                    ricetta.appendChild(firstname);


                    // write the content into xml file
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(new File(Environment.getExternalStorageDirectory()+File.separator+"AppCucina"+File.separator+"preferiti.xml")); //per mettere il file nella cartella di default di Android

                    // Output to console for testing
                    // StreamResult result = new StreamResult(System.out);

                    transformer.transform(source, result);

                    return true;

                } else{//il file è pieno
                    //fr.close();
                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                    // root elements
                    doc = docBuilder.parse(fXmlFile);
                    Element rootElement = doc.getDocumentElement();
                            //doc.createElement("preferiti");
                    //doc.appendChild(rootElement);

                    // staff elements
                    Element ricetta = doc.createElement("ricetta");
                    rootElement.appendChild(ricetta);

                    // set attribute to staff element
                    /*Attr attr = doc.createAttribute("title");
                    attr.setValue("1");
                    ricetta.setAttributeNode(attr);*/

                    // shorten way
                    // staff.setAttribute("id", "1");

                    // firstname elements
                    Element firstname = doc.createElement("titolo");
                    firstname.appendChild(doc.createTextNode(titolo));
                    ricetta.appendChild(firstname);


                    // write the content into xml file
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(new File(Environment.getExternalStorageDirectory()+File.separator+"AppCucina"+File.separator+"preferiti.xml")); //per mettere il file nella cartella di default di Android

                    // Output to console for testing
                    // StreamResult result = new StreamResult(System.out);

                    transformer.transform(source, result);

                    return true;
                }
            }
             else{
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



    public boolean DeleteObjectToXML(String titolo){
        boolean check=false;

        try {

            if(CheckFolderAndFile()){

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
                               Element e = (Element) doc.getElementsByTagName("ricetta").item(0);
                                       //doc.getElementsByTagName("link").item(0);
                                e.getParentNode().removeChild(e);
                                //doc.removeChild(e);
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
