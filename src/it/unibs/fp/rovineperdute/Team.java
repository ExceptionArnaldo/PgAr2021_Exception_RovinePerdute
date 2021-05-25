package it.unibs.fp.rovineperdute;

import java.util.*;

public class Team {

    private String nome;
    private String veicolo;
    private int carburante;
    private ArrayList<Citta> percorso = new ArrayList<>();

    HashMap<Integer, Citta> percorso_minimo = new HashMap<>();
    int id = -1;

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


    public void calcolaPercorsoMinimo(ArrayList<Citta> citta) {

        Citta citta_partenza = citta.get(0);
        Citta citta_arrivo = citta.get(citta.size() - 1);

        for (Citta nodo : citta) {
            percorso_minimo.put(nodo.getId(), new Citta());
        }

        Stack<Integer> figli = new Stack<>();
        dfs(citta_partenza, figli);

        System.out.println(figli);

    }

    public void dfs(Citta citta_partenza, Stack<Integer> figli){

        if(Citta.getCittaById(id).getNome().equals("Rovine Perdute")){
            dfs(Citta.getCittaById(figli.peek()), figli);
        }
        else {
            citta_partenza.getPercorsi().forEach((key, value) -> {
                if (key != id) figli.push(key);
            });
            id = citta_partenza.getId();
            dfs(Citta.getCittaById(figli.peek()), figli);
        }
    }
}
