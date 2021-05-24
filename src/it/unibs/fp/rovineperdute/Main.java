package it.unibs.fp.rovineperdute;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        ArrayList<Citta> citta = new ArrayList<>();

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

        Team team = new Team("b", "Tonatiuh");

        Citta.calcolaPesoPercorso(citta, team);

        Xml.leggiCitta("PgAr_Map_5.xml", citta);

        for(int i=0; i<citta.size();i++)
            System.out.println(citta.get(i).toString());


    }
}
