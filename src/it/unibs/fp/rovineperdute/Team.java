package it.unibs.fp.rovineperdute;

import java.util.ArrayList;

public class Team {

    private String nome;
    private String veicolo;

    public Team(String nome, String veicolo){
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

    public void calcolaPercorsoMinimo(ArrayList<Citta> citta){

        Citta citta_partenza = citta.get(0);
        Citta citta_arrivo = citta.get(citta.size()-1);

        int carburante_attuale = 0;

        for(int i = 0; i < citta_partenza.getPercorsi().size(); i++){



        }

    }
}
