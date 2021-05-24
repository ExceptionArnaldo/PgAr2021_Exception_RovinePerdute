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

    /**
     * legge un file xml e salva i dati delle citta in un ArrayList di tipo citta.
     *
     * @param nome_file nome del file
     * @param citta     ArrayList di citta da popolare
     */
    public static void leggiCitta(String nome_file, ArrayList<Citta> citta) {

        XMLInputFactory xmlif;
        XMLStreamReader xmlr;

        String nome = null;
        Punto coordinata;
        int id = Costante.C0;
        HashMap<Integer, Integer> percorso = null;

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(nome_file, new FileInputStream(nome_file));

            while (xmlr.hasNext()) {
                if (xmlr.getEventType() == XMLStreamConstants.START_ELEMENT) {
                    if (xmlr.getLocalName().equals(Costante.CITTA)) { //interessano solo i dati relativi alle citta
                        percorso = new HashMap<>();
                        coordinata = new Punto();
                        for (int i = Costante.C0; i < xmlr.getAttributeCount(); i++) {
                            switch (xmlr.getAttributeLocalName(i)) {
                                case Costante.ID:
                                    id = Integer.parseInt(xmlr.getAttributeValue(i));
                                    break;
                                case Costante.NOME:
                                    nome = xmlr.getAttributeValue(i);
                                    break;
                                case Costante.COORDINATAX:
                                    coordinata.setX(Integer.parseInt(xmlr.getAttributeValue(i)));
                                    break;
                                case Costante.COORDINATAY:
                                    coordinata.setY(Integer.parseInt(xmlr.getAttributeValue(i)));
                                    break;
                                case Costante.COORDINATAH:
                                    coordinata.setZ(Integer.parseInt(xmlr.getAttributeValue(i)));
                                    citta.add(new Citta(id, nome, coordinata)); //ottenuti tutti i valori dell'xml di una citta. Creazione citta senza link
                                    break;
                            }
                        }
                    } else if (xmlr.getLocalName().equals(Costante.LINK)) { // salvataggio link
                        percorso.put(Integer.parseInt(xmlr.getAttributeValue(Costante.C0)), Costante.C0);
                        citta.get(citta.size() - Costante.C1).setPercorsi(percorso);
                    }
                }
                xmlr.next();
            }
        } catch (Exception e) {
            System.out.println(Costante.ERRORE_LETTURA);
            System.out.println(e.getMessage());
        }
    }

    /**
     * Scrivi il percorso dei vari team.
     *
     * @param nome_file nome del file da scrivere
     * @param team1     team 1
     * @param team2     team 2
     */
    public static void scriviPercorso(String nome_file, Team team1, Team team2) {

        XMLOutputFactory xmlof;
        XMLStreamWriter xmlw;

        try { // blocco try per raccogliere eccezioni
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(nome_file), Costante.ENCODING);
            xmlw.writeStartDocument(Costante.ENCODING, Costante.VERSION);
            xmlw.writeStartElement(Costante.ROUTES); // scrittura del tag radice Routes

            stampaCodici(xmlw, Costante.TEAM_NOME1, team1); //scrittura team
            stampaCodici(xmlw, Costante.TEAM_NOME2, team2);

            xmlw.writeEndElement(); // chiusura di </routes>
            xmlw.writeEndDocument(); // scrittura della fine del documento

            xmlw.flush(); // svuota il buffer e procede alla scrittura
            xmlw.close(); // chiusura del documento e delle risorse impiegate

        } catch (Exception e) { // se c’è un errore viene eseguita questa parte
            System.out.println(Costante.ERRORE_SCRITTURA);
            System.out.println(e.getMessage());
        }
    }

    private static void stampaCodici(XMLStreamWriter xmlw, String nome, Team team) throws XMLStreamException {
        xmlw.writeStartElement(Costante.ROUTE);
        xmlw.writeAttribute(Costante.TEAM, nome);
        xmlw.writeAttribute(Costante.COSTO, Integer.toString(team.getCarburante()));
        xmlw.writeAttribute(Costante.NUMERO_CITTA, Integer.toString(team.getPercorso().size()));

        for (int i = Costante.C0; i < team.getPercorso().size(); i++) {
            xmlw.writeStartElement(Costante.CITTA); // apertura del tag <city>
            xmlw.writeAttribute(Costante.ID, Integer.toString(team.getPercorso().get(i).getId())); // attributo id
            xmlw.writeAttribute(Costante.NOME, team.getPercorso().get(i).getNome());
            xmlw.writeEndElement(); // chiusura di </city>
        }
        xmlw.writeEndElement(); // chiusura di </route>
    }

    /**
     * Formatta file xml.
     *
     * @param file the file
     * @throws Exception the exception
     */
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
}