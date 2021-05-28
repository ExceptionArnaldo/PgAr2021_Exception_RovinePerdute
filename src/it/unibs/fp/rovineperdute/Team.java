package it.unibs.fp.rovineperdute;

import java.util.*;

/**
 * Il Team.
 */
public class Team {

    private String veicolo;
    private Stack<Citta> percorso_minimo = new Stack<>();
    private double carburante_tot;

    /**
     * Instantiates a new Team.
     *
     * @param veicolo the veicolo
     */
    public Team(String veicolo) {
        this.veicolo = veicolo;
    }

    /**
     * Gets veicolo.
     *
     * @return the veicolo
     */
    public String getVeicolo() {
        return veicolo;
    }

    /**
     * Gets percorso minimo.
     *
     * @return the percorso minimo
     */
    public Stack<Citta> getPercorso_minimo() {
        return percorso_minimo;
    }

    /**
     * Set carburante totale.
     *
     * @param distanza the distanza
     */
    public void setCarburanteTotale(double[] distanza) {
        this.carburante_tot = distanza[distanza.length - 1];
    }

    /**
     * Gets carburante tot.
     *
     * @return the carburante tot
     */
    public double getCarburante_tot() {
        return carburante_tot;
    }

    /**
     * Algoritmo di Dijkstra.
     *
     * @param citta_partenza citta di partenza
     */
    public void dijkstra(Citta citta_partenza) {

        int numero_citta = Rovina.getRovina().size();
        double[] distanza = new double[numero_citta];
        int[] citta_precedenti = new int[numero_citta];
        boolean[] visitato = new boolean[numero_citta];

        ArrayList<Citta> citta = new ArrayList(Rovina.getRovina());

        for (int i = 0; i < numero_citta; i++) {
            distanza[i] = Double.POSITIVE_INFINITY;
            citta_precedenti[i] = -1;
            visitato[i] = false;
        }

        distanza[citta_partenza.getId()] = 0;

        for (int i = 0; i < numero_citta; i++) {
            int id_attuale = distanzaPiuBreve(distanza, visitato);

            visitato[id_attuale] = true;
            for (int j = 0; j < numero_citta; j++) {
                if (!visitato[j] &&
                        citta.get(id_attuale).getPercorsi().containsKey(j) &&
                        citta.get(id_attuale).getPercorsi().get(j) != 0 &&
                        !Double.isInfinite(distanza[id_attuale]) &&
                        (distanza[id_attuale] + citta.get(id_attuale).getPercorsi().get(j)) < distanza[j]) {

                    distanza[j] = distanza[id_attuale] + citta.get(id_attuale).getPercorsi().get(j);
                    citta_precedenti[j] = id_attuale;
                }
            }
        }
        setPercorsoMinimo(citta_precedenti);
        setCarburanteTotale(distanza);
    }

    /**
     * Calcolo della Distanza piu breve.
     *
     * @param distanza the distanza
     * @param visitato the visitato
     * @return posizione del nodo con distanza minore
     */
    public int distanzaPiuBreve(double[] distanza, boolean[] visitato) {

        double min = Double.POSITIVE_INFINITY;
        int pos = -1;

        for (int i = 0; i < distanza.length; i++) {
            if (distanza[i] <= min && !visitato[i]) {
                min = distanza[i];
                pos = i;
            }
            /*else if(distanza[i] == min){
                if(i >= pos){
                    min = distanza[i];
                    pos = i;
                }
            }*/
        }
        return pos;
    }

    /**
     * Set percorso minimo.
     *
     * @param citta_precedenti citta precedenti
     */
    public void setPercorsoMinimo(int[] citta_precedenti) {

        int indice = citta_precedenti.length - 1;

        percorso_minimo.push(Citta.getCittaById(indice));

        while (citta_precedenti[indice] != -1) {
            percorso_minimo.push(Citta.getCittaById(citta_precedenti[indice]));
            indice = citta_precedenti[indice];
        }
    }
}