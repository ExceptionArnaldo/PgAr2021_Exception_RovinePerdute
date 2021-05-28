package it.unibs.fp.rovineperdute;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {

        //lettura tutti file
        for (int i = 0; i < Costante.FILE_INPUT_INDICE.length; i++) {

            ArrayList<Citta> citta = new ArrayList<>();

            Xml.leggiCitta(String.format(Costante.FILE_INPUT, Costante.FILE_INPUT_INDICE[i]), citta); //Lettura dei documenti informativi

            Team team1 = new Team(Costante.TEAM_NOME1); // si puo mettere in un metodo
            Team team2 = new Team(Costante.TEAM_NOME2);

            Rovina rovina = new Rovina(citta);
            Citta.calcolaPesoPercorso(team1);
            team1.dijkstra(citta.get(0));
            //System.out.println(team1.getCarburante_tot());

            Citta.calcolaPesoPercorso(team2);
            team2.dijkstra(citta.get(0));
            //System.out.println(team2.getCarburante_tot());

            Xml.scriviPercorso(String.format(Costante.FILE_OUTPUT, Costante.FILE_INPUT_INDICE[i]), team1, team2); // Scrittura del documento finale
            Xml.formatXMLFile(String.format(Costante.FILE_OUTPUT, Costante.FILE_INPUT_INDICE[i])); // formattazione file

            System.out.println("file"+ i+1);
        }

/*
        ArrayList<Citta> citta = new ArrayList<>();

        //lettura singolo file
        Xml.leggiCitta("./percorsi/file_input/PgAr_Map_5.xml", citta); //Lettura dei documenti informativi




        Team team1 = new Team(Costante.TEAM_NOME1); // si puo mettere in un metodo
        Team team2 = new Team(Costante.TEAM_NOME2);

        Rovina rovina = new Rovina(citta);
        Citta.calcolaPesoPercorso(team1);


        team1.dijkstra(citta.get(0));

        System.out.println(team1.getCarburante_tot());
*/
        /*for(int i = 0; i< 5; i++){
            System.out.println(citta.get(i).getPercorsi());
        }*/

        /*for(int i = 0; i < citta.size(); i++){
            System.out.println("distanza: " + team1.getDistanza()[i] + " citta pre: " + team1.getCitta_precedenti()[i]);
        }*/
/*
        Citta.calcolaPesoPercorso(team2);
        team2.dijkstra(citta.get(0));

        System.out.println(team2.getCarburante_tot());
*/
        /*for(int i = 0; i < citta.size(); i++){
            System.out.println("distanza: " + team2.getDistanza()[i] + " citta pre: " + team2.getCitta_precedenti()[i]);
        }*/

        //Citta.calcolaPesoPercorso(citta, team1); // Analisi dei dati sulle città
        //team1.calcolaPercorsoMinimo(citta);      // Calcolo percorso ottimale

        //Citta.calcolaPesoPercorso(citta, team2);
        //team2.calcolaPercorsoMinimo(citta);

        //Xml.scriviPercorso("./percorsi/file_output/PgAr_Map_5.xml", team1, team2); // Scrittura del documento finale
        //Xml.formatXMLFile("./percorsi/file_output/PgAr_Map_5.xml"); // formattazione file

/*
        HashMap<Integer, Integer> percorsi1 = new HashMap<>();
        percorsi1.put(1, 0);
        percorsi1.put(3, 0);
        Citta campo_base = new Citta(0, "campo base", new Punto(8279, 8338, 2745), percorsi1);

        HashMap<Integer, Integer> percorsi2 = new HashMap<>();
        percorsi2.put(0, 0);
        percorsi2.put(2, 0);
        percorsi2.put(3, 0);
        percorsi2.put(4, 0);
        Citta tikal = new Citta(1, "Tikal", new Punto(1898, 927, 1336), percorsi2);

        HashMap<Integer, Integer> percorsi3 = new HashMap<>();
        percorsi3.put(0, 0);
        percorsi3.put(1, 0);
        percorsi3.put(4, 0);
        Citta teotiguacan = new Citta(2, "Teotiguacan", new Punto(6317, 3345, 2090), percorsi3);

        HashMap<Integer, Integer> percorsi4 = new HashMap<>();
        percorsi4.put(1, 0);
        percorsi4.put(4, 0);
        Citta mixco_vieio = new Citta(3, "Mixco Vieio", new Punto(4798, 1462, 1022), percorsi4);

        HashMap<Integer, Integer> percorsi5 = new HashMap<>();
        percorsi5.put(0, 0);
        percorsi5.put(1, 0);
        percorsi5.put(2, 0);
        percorsi5.put(3, 0);
        Citta rovine_perdute = new Citta(4, "Teotiguacan", new Punto(4415, 4849, 954), percorsi5);

        citta.add(campo_base);
        citta.add(tikal);
        citta.add(teotiguacan);
        citta.add(mixco_vieio);
        citta.add(rovine_perdute);

        Team team1 = new Team("a", Costante.TEAM_NOME1);
        Team team2 = new Team("b", Costante.TEAM_NOME2);

        Citta.calcolaPesoPercorso(citta, team1);
        team1.calcolaPercorsoMinimo(citta);

        Citta.calcolaPesoPercorso(citta, team2);
        team2.calcolaPercorsoMinimo(citta);

        Xml.leggiCitta("./percorsi/file_input/PgAr_Map_5.xml", citta);

        for (int i = 0; i < citta.size(); i++)
            System.out.println(citta.get(i).toString());
 */
    }
}
