package it.unibs.fp.rovineperdute;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Xml {
    //legge un file xml e salva i dati delle citta in un ArrayList di tipo persona
    public static void leggiCitta(String nome_file, ArrayList<Citta> citta) {

        XMLInputFactory xmlif;
        XMLStreamReader xmlr;

        String nome = null;
        Punto coordinata = null;
        int id = 0;
        HashMap<Integer, Integer> percorso = null;

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(nome_file, new FileInputStream(nome_file));

            while (xmlr.hasNext()) {
                if (xmlr.getEventType() == XMLStreamConstants.START_ELEMENT) { //interessano solo i dati relativi alle citta
                    if(xmlr.getLocalName().equals("city")) {
                        percorso = new HashMap<>();
                        coordinata = new Punto();
                        for (int i = 0; i < xmlr.getAttributeCount(); i++) {
                            //System.out.printf(" => attributo %s->%s%n", xmlr.getAttributeLocalName(i), xmlr.getAttributeValue(i));
                            switch (xmlr.getAttributeLocalName(i)) {
                                case Costante.ID:
                                    id = Integer.parseInt(xmlr.getAttributeValue(i));
                                    break;
                                case Costante.NOME:
                                    //xmlr.next();
                                    nome = xmlr.getAttributeValue(i);
                                    break;
                                case Costante.COORDINATAX:
                                    //xmlr.next();
                                    coordinata.setX(Integer.parseInt(xmlr.getAttributeValue(i)));
                                    break;
                                case Costante.COORDINATAY:
                                    //xmlr.next();
                                    coordinata.setY(Integer.parseInt(xmlr.getAttributeValue(i)));
                                    break;
                                case Costante.COORDINATAH:
                                    //xmlr.next();
                                    coordinata.setZ(Integer.parseInt(xmlr.getAttributeValue(i)));
                                    citta.add(new Citta(id, nome, coordinata)); //ottenuti tutti i valori dell'xml di una citta. Creazione citta senza link
                                    break;
                            }
                        }
                    }
                    else if(xmlr.getLocalName().equals("link")){
                        //xmlr.next();
                        percorso.put(Integer.parseInt(xmlr.getAttributeValue(0)),0);
                        citta.get(citta.size()-1).setPercorsi(percorso);

                    }
                }
                xmlr.next();
            }
        } catch (Exception e) {
            System.out.println(Costante.ERRORE_LETTURA);
            System.out.println(e.getMessage());
        }
    }

    /*
    public static void scriviPercorso(String nome_file, ArrayList<Persona> persone, ArrayList<codiceFiscale> codici_invalidi, ArrayList<codiceFiscale> codici_spaiati) {

        XMLOutputFactory xmlof;
        XMLStreamWriter xmlw;

        try { // blocco try per raccogliere eccezioni
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(nome_file), Costante.ENCODING);
            xmlw.writeStartDocument(Costante.ENCODING, Costante.VERSION);
            xmlw.writeStartElement(Costante.ROUTES); // scrittura del tag radice output

            stampaCodici();
            stampaCodici();

            xmlw.writeStartElement(Costante.CODICI); // scrittura del tag <codici>
            stampaCodici(xmlw, Costante.INVALIDI, codici_invalidi);
            stampaCodici(xmlw, Costante.SPAIATI, codici_spaiati);
            xmlw.writeEndElement(); // chiusura di </codici>

            xmlw.writeEndElement(); // chiusura di </output>
            xmlw.writeEndDocument(); // scrittura della fine del documento

            xmlw.flush(); // svuota il buffer e procede alla scrittura
            xmlw.close(); // chiusura del documento e delle risorse impiegate
        } catch (Exception e) { // se c’è un errore viene eseguita questa parte
            System.out.println(Costante.ERRORE_SCRITTURA);
            System.out.println(e.getMessage());
        }
    }

    private static void stampaCodici(){
        xmlw.writeStartElement(Costante.ROUTE);
        xmlw.writeAttribute(Costante.TEAM, team.getNome());
        xmlw.writeAttribute(Costante.COSTO, Integer.toString(team.getCarburante()));
        xmlw.writeAttribute(Costante.NUMERO_CITTA, Integer.toString(boh));

        for (int i = 0; i < boh; i++) {
            xmlw.writeStartElement(Costante.CITTA); // apertura del tag <city>
            xmlw.writeAttribute(Costante.ID, boh); // attributo id
            xmlw.writeAttribute(Costante.NOME, boh);
            xmlw.writeEndElement(); // chiusura di </city>
        }
        xmlw.writeEndElement(); // chiusura di </route>
    }

    //stampa CF
    private static void stampaCodici(XMLStreamWriter xmlw, String tag, ArrayList<codiceFiscale> codici) throws XMLStreamException {

        xmlw.writeStartElement(tag); // scrittura del tag <...>
        xmlw.writeAttribute(Costante.NUMERO, Integer.toString(codici.size())); // attributo numero

        for (int i = 0; i < codici.size(); i++)   // scrittura tutti CF
            scriviTag(xmlw, Costante.CODICE, codici.get(i).toString());

        xmlw.writeEndElement(); // chiusura di </...>
    }

    //scrive un tag completo
    private static void scriviTag(XMLStreamWriter xmlw, String tag, String valore) throws XMLStreamException {
        xmlw.writeStartElement(tag);
        xmlw.writeCharacters(valore);
        xmlw.writeEndElement();
    }

    //prende il comune di nascita della persona e restituisce il relativo codice se trovato nel file xml
    public static String leggiComune(String nome_file, String comune) {

        XMLInputFactory xmlif;
        XMLStreamReader xmlr;

        String codice = "";

        boolean trovato = false;

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(nome_file, new FileInputStream(nome_file));

            while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
                if (xmlr.getEventType() == XMLStreamConstants.CHARACTERS) { // interessa solo il nome dei comuni
                    if (xmlr.getText().trim().length() > 0) { // controlla se il testo non contiene solo spazi
                        if (trovato) {
                            codice = xmlr.getText();
                            return codice;
                        }
                        if (xmlr.getText().equals(comune)) trovato = true;
                    }
                }
                xmlr.next();
            }
        } catch (Exception e) {
            System.out.println(Costante.ERRORE_LETTURA);
            System.out.println(e.getMessage());
        }
        return codice;
    }

    //legge xml e riempie un ArrayList di codici fiscali se questi risultano corretti
    public static void leggiCodiceFiscale(String nome_file, ArrayList<codiceFiscale> codici_corretti, ArrayList<codiceFiscale> codici_sbagliati) {

        XMLInputFactory xmlif;
        XMLStreamReader xmlr;

        String cod_fis;

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(nome_file, new FileInputStream(nome_file));

            while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
                if (xmlr.getEventType() == XMLStreamConstants.CHARACTERS) {
                    if (xmlr.getText().trim().length() > 0) { // controlla se il testo non contiene solo spazi
                        cod_fis = xmlr.getText();
                        if (new codiceFiscale(cod_fis).validitaCodice()) // crea codice fiscale e verifica se e' corretto
                            codici_corretti.add(new codiceFiscale(cod_fis)); // se corretto lo aggiunge all'ArrayList CF corretti
                        else
                            codici_sbagliati.add(new codiceFiscale(cod_fis)); // se sbagliato lo aggiunge all'ArrayList CF sbagliati
                    }
                }
                xmlr.next();
            }
        } catch (Exception e) {
            System.out.println(Costante.ERRORE_LETTURA);
            System.out.println(e.getMessage());
        }
    }

    public static void formatXMLFile(String file) throws Exception { //prende un xml non formattato e lo formatta

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new InputStreamReader(new FileInputStream(file))));

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, Costante.METODO_FORMATTAZIONE); //tipo di file generato
        transformer.setOutputProperty(OutputKeys.INDENT, Costante.INDENT_FORMATTAZIONE); //indentazione
        transformer.setOutputProperty(Costante.HTTPS_FORMATTAZIONE, Costante.LIVELLO_INDENTAZIONE); //formattazione
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, Costante.DICHIARAZIONE_FORMATTAZIONE);
        Source source = new DOMSource(document);
        Result result = new StreamResult(new File(file));
        transformer.transform(source, result);
    }

*/
}
