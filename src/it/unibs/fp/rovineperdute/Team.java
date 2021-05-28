package it.unibs.fp.rovineperdute;

import java.util.*;

public class Team {

    private String veicolo;
    private Stack<Integer> percorso_minimo = new Stack<>();
    private double carburante_tot;

    public Team(String veicolo) {
        this.veicolo = veicolo;
    }

    public String getVeicolo() {
        return veicolo;
    }

    public void setVeicolo(String veicolo) {
        this.veicolo = veicolo;
    }

    public Stack<Integer> getPercorso_minimo() {
        return percorso_minimo;
    }

    public void setPercorso_minimo(Stack<Integer> percorso_minimo) {
        this.percorso_minimo = percorso_minimo;
    }

    public void setCarburanteTotale(double distanza[]){
        this.carburante_tot = distanza[distanza.length-1];
    }

    public double getCarburante_tot() {
        return carburante_tot;
    }

    public void dijkstra(Citta citta_partenza) {

        int numero_citta = Rovina.getRovina().size();
        double [] distanza = new double[numero_citta];
        int [] citta_precedenti = new int[numero_citta];
        boolean [] visitato = new boolean[numero_citta];

        ArrayList <Citta> citta = new ArrayList(Rovina.getRovina());

        for (int i = 0; i < numero_citta; i++) {
            distanza[i] = Double.POSITIVE_INFINITY;
            citta_precedenti[i] = -1;
            visitato[i] = false;
        }

        distanza[citta_partenza.getId()] = 0;

        for(int i = 0; i < numero_citta; i++){
            int id_attuale = distanzaPiuBreve(distanza, visitato);

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

        setPercorsoMinimo(citta_precedenti);
        setCarburanteTotale(distanza);
    }

    public int distanzaPiuBreve(double distanza[], boolean visitato[]) {

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

    public void setPercorsoMinimo(int [] citta_precedenti){

        int indice = citta_precedenti.length - 1;

        percorso_minimo.push(indice);

        while(citta_precedenti[indice] != -1){
            percorso_minimo.push(citta_precedenti[indice]);
            indice = citta_precedenti[indice];
        }

        System.out.println(percorso_minimo);
    }

}
