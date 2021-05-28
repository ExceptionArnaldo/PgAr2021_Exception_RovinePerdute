package it.unibs.fp.rovineperdute;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {

        int scelta = Utente.scegliFile();

        while(scelta !=0 ) {

            ArrayList<Citta> citta = new ArrayList<>();

            Xml.leggiCitta(String.format(Costante.FILE_INPUT, Costante.FILE_INPUT_INDICE[scelta-1]), citta); //Lettura dei documenti informativi

            Team team1 = new Team(Costante.TEAM_NOME1); // si puo mettere in un metodo
            Team team2 = new Team(Costante.TEAM_NOME2);

            Rovina rovina = new Rovina(citta);
            Citta.calcolaPesoPercorso(team1);
            team1.dijkstra(citta.get(0));

            Citta.calcolaPesoPercorso(team2);
            team2.dijkstra(citta.get(0));

            Xml.scriviPercorso(String.format(Costante.FILE_OUTPUT, Costante.FILE_INPUT_INDICE[scelta-1]), team1, team2); // Scrittura del documento finale
            Xml.formatXMLFile(String.format(Costante.FILE_OUTPUT, Costante.FILE_INPUT_INDICE[scelta-1])); // formattazione file

            scelta = Utente.scegliFile();
        }
    }
}
