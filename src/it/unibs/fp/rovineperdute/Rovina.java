package it.unibs.fp.rovineperdute;

import java.util.ArrayList;

// grafo
public class Rovina {

    private static ArrayList<Citta> rovina = new ArrayList<>();

    public Rovina() {
    }

    public Rovina(ArrayList<Citta> rovina) {
        this.rovina = rovina;
    }

    public static ArrayList<Citta> getRovina() {
        return rovina;
    }

    public void setRovina(ArrayList<Citta> rovina) {
        this.rovina = rovina;
    }

    @Override
    public String toString() {
        return "Rovina{}";
    }
}
