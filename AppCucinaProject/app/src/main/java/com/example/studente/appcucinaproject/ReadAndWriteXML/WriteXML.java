package com.example.studente.appcucinaproject.ReadAndWriteXML;

/**
 * Created by Mattia on 30/06/2016.
 */

import android.os.Environment;

import java.io.File;
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


public class WriteXML {

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

                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                // root elements
                Document doc = docBuilder.newDocument();
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
            }
             else{
                return false;
            }

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
        return false;
    }

}
