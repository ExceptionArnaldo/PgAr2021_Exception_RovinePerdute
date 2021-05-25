package it.unibs.fp.rovineperdute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Team {

    private String nome;
    private String veicolo;
    private int carburante;
    private ArrayList<Citta> percorso = new ArrayList<>();

    public Team(String nome, String veicolo) {
        this.nome = nome;
        this.veicolo = veicolo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVeicolo() {
        return veicolo;
    }

    public void setVeicolo(String veicolo) {
        this.veicolo = veicolo;
    }

    public void calcolaPercorsoMinimo(ArrayList<Citta> citta) {

        Citta citta_partenza = citta.get(0);
        Citta citta_arrivo = citta.get(citta.size() - 1);

        HashMap<Citta, Integer> percorso_minimo = new HashMap<>();

        for (Citta nodo : citta) {
            percorso_minimo.put(nodo, 0);
        }

        for (int i = 0; i < citta_partenza.getPercorsi().size(); i++) {
        }
    }

    public int getCarburante() {
        return carburante;
    }

    public void setCarburante(int carburante) {
        this.carburante = carburante;
    }

    public ArrayList<Citta> getPercorso() {
        return percorso;
    }

    public void setPercorso(ArrayList<Citta> percorso) {
        this.percorso = percorso;
    }
}
