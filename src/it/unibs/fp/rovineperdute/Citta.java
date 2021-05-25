package it.unibs.fp.rovineperdute;

import java.util.ArrayList;
import java.util.HashMap;

// nodo
public class Citta {

    private int id;
    private String nome;
    private Punto coordinata;
    private HashMap<Integer, Integer> percorsi;

    private int costo;
    private Citta citta_pre;
    private boolean verificato = false;

    public Citta() {
    }

    public Citta(int id, String nome, Punto coordinata) {
        this.id = id;
        this.nome = nome;
        this.coordinata = coordinata;
    }

    public Citta(int id, String nome, Punto coordinata, HashMap<Integer, Integer> percorsi) {
        this.id = id;
        this.nome = nome;
        this.coordinata = coordinata;
        this.percorsi = percorsi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Punto getCoordinata() {
        return coordinata;
    }

    public void setCoordinata(Punto coordinata) {
        this.coordinata = coordinata;
    }

    public HashMap<Integer, Integer> getPercorsi() {
        return percorsi;
    }

    public void setPercorsi(HashMap<Integer, Integer> percorsi) {
        this.percorsi = percorsi;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public Citta getCitta_pre() {
        return citta_pre;
    }

    public void setCitta_pre(Citta citta_pre) {
        this.citta_pre = citta_pre;
    }

    public static void calcolaPesoPercorso(ArrayList<Citta> citta, Team team) {
        switch (team.getVeicolo()) {

            case "Tonatiuh": {
                for (int i = 0; i < Rovina.getRovina().size(); i++) {
                    Citta citta_partenza = Rovina.getRovina().get(i);

                    citta_partenza.percorsi.forEach((key, value) -> {
                        int peso = 0;
                        Citta citta_arrivo = getCittaById(key);

                        peso = (int) Math.sqrt(Math.pow(citta_arrivo.coordinata.getX() - citta_partenza.coordinata.getX(), 2) + Math.pow(citta_arrivo.coordinata.getY() - citta_partenza.coordinata.getY(), 2));
                        citta_partenza.percorsi.replace(key, peso);
                        System.out.println("citta partenza: " + citta_partenza.nome + ", citta arrivo" + citta_arrivo.nome + "key: " + key + " value: " + peso);
                    });
                }
                break;
            }

            case "Metztli": {
                for (int i = 0; i < Rovina.getRovina().size(); i++) {
                    Citta citta_partenza = Rovina.getRovina().get(i);
                    citta_partenza.percorsi.forEach((key, value) -> {
                        int peso;
                        Citta citta_arrivo = getCittaById(key);

                        peso = Math.abs(citta_partenza.coordinata.getZ() - citta_arrivo.coordinata.getZ());
                        citta_partenza.percorsi.replace(key, peso);
                    });
                }
                break;
            }
        }
    }

    public static Citta getCittaById(int id_da_cercare) {

        Citta citta_cercato = null;

        for (int i = 0; i < Rovina.getRovina().size(); i++) {
            Citta citta_attuale = Rovina.getRovina().get(i);
            if (citta_attuale.id == id_da_cercare) citta_cercato = citta_attuale;
        }

        return citta_cercato;
    }

    @Override
    public String toString() {
        return "Citta{" +
                "ID=" + id +
                ", nome='" + nome + '\'' +
                ", coordinata=" + coordinata +
                ", percorso=" + percorsi +
                '}';
    }
}
