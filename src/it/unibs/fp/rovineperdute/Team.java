package it.unibs.fp.rovineperdute;

import java.util.*;

public class Team {

    private String nome;
    private String veicolo;
    private int carburante;
    private ArrayList<Citta> percorso = new ArrayList<>();

    private double distanza[];
    private int citta_precedenti[];
    boolean visitato[];

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

    public double[] getDistanza() {
        return distanza;
    }

    public void setDistanza(double[] distanza) {
        this.distanza = distanza;
    }

    public int[] getCitta_precedenti() {
        return citta_precedenti;
    }

    public void setCitta_precedenti(int[] citta_precedenti) {
        this.citta_precedenti = citta_precedenti;
    }

    public void algoritmo(Citta citta_partenza) {

        int numero_citta = Rovina.getRovina().size();
        distanza = new double[numero_citta];
        citta_precedenti = new int[numero_citta];
        visitato = new boolean[numero_citta];

        ArrayList <Citta> citta = new ArrayList(Rovina.getRovina());

        for (int i = 0; i < numero_citta; i++) {
            distanza[i] = Double.POSITIVE_INFINITY;
            citta_precedenti[i] = -1;
            visitato[i] = false;
        }

        distanza[citta_partenza.getId()] = 0;

        for(int i = 0; i < numero_citta; i++){
            int id_attuale = distanzaPiuBreve();

            visitato[id_attuale] = true;
            for(int j = 0; j < numero_citta; j++){
                if(!visitato[j] &&
                        citta.get(id_attuale).getPercorsi().containsKey(j) &&
                        citta.get(id_attuale).getPercorsi().get(j) !=0 &&
                        !Double.isInfinite(distanza[id_attuale]) &&
                        (distanza[id_attuale] + citta.get(id_attuale).getPercorsi().get(j)) < distanza[j]){

                    distanza[j] = distanza[id_attuale] + citta.get(id_attuale).getPercorsi().get(j);
                    citta_precedenti[j] = id_attuale;

                }
            }
        }
    }

    public int distanzaPiuBreve() {

        double min = Double.POSITIVE_INFINITY;
        int pos = -1;

        for (int i = 0; i < distanza.length; i++) {
            if (distanza[i] <= min && visitato[i] == false) {
                min = distanza[i];
                pos = i;
            }

        }
        return pos;
    }

}
