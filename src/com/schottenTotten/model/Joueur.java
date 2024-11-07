package com.schottenTotten.model;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
    
    private int id_joueur;
    private final List<Carte> pied;

    public Joueur(int id_joueur){
        this.id_joueur = id_joueur;
        this.pied = new ArrayList<>();
    }

    public int getId(){
        return this.id_joueur;
    }

    public void setId(int id_joueur){
        this.id_joueur = id_joueur;
    }



    public List<Carte> getCartes() {
        return new ArrayList<>(pied); // Retourne une copie pour protéger la liste originale
    }

    
}
