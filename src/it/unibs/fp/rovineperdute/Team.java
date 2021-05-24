package it.unibs.fp.rovineperdute;

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
}
