package com.schottenTotten.model;

import java.util.List;

public class Carte_Tactique extends Carte {
    private String nom;
    private String description;
    private Type type;
    private String effet;
    private List<Integer> valeurs_possibles;
    private int nbr_exemplaires = 1;

    public Carte_Tactique(String nom, String description, Type type) {
        super(null, 0);
        this.nom = nom;
        this.description = description;
        this.type = type;
        this.valeurs_possibles = null;
        this.effet = null;
    }

    public Carte_Tactique(String nom, String description, Type type, String effet, List<Integer> valeursPossibles){
        super(null, 0);
        this.nom = nom;
        this.description = description;
        this.type = type;
        this.valeurs_possibles = valeursPossibles;
        this.effet = effet;
    }

    // Getter pour 'nom'
    @Override
    public String getNom() {
        return nom;
    }

    // Setter pour 'nom'
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNbrExemplaires(int nbr_exemplaires){
        this.nbr_exemplaires = nbr_exemplaires;
    }

    public int getNbrexemplaires(){
        return this.nbr_exemplaires;
    }

    // Getter pour 'description'
    @Override
    public String getDescription() {
        return description;
    }

    // Setter pour 'description'
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter pour 'type'
    public Type getType() {
        return type;
    }

    // Setter pour 'type'
    public void setType(Type type) {
        this.type = type;
    }

    public List<Integer> getValeursPoss(){
        return this.valeurs_possibles;
    }

    public String getEffet(){
        return this.effet;
    }

    // Méthode toString
    @Override
    public String toString() {
        String part1 = "Carte_Tactique {" +
        "nom='" + nom + '\'' +
        ", description='" + description + '\'' +
        ", type=" + type +
        ", ";
        if(type == Type.TROUPE_ELITE){
            return part1 + "couleur=" + getCouleur() + ", "+ "valeur=" + getValeur() + "}";
        }
        else{
            return part1 + "}";
        }
    }

    public enum Type {
        TROUPE_ELITE,
        MODES_COMBAT,
        RUSES
    }
}

