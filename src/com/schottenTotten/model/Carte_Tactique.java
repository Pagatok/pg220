package com.schottenTotten.model;

import java.util.List;

public class Carte_Tactique extends Carte {
    private String nom;
    private String description;
    private Type type;
    private String effet;
    private List<Integer> valeurs_possibles;
    private int nbr_exemplaires = 1;

    
    // ------------------------- CONSTRUCTEURS -------------------------


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


    // ------------------------- GETTERS -------------------------


    // Renvoie le nom de la carte
    @Override
    public String getNom() {
        return nom;
    }


    // Renvoie le nombre d'exemplaires de la carte dans la pioche initale
    // (Càd ne se met pas à jour si elles sont piochées)
    public int getNbrexemplaires(){
        return this.nbr_exemplaires;
    }


    // Renvoie la description de la carte
    @Override
    public String getDescription() {
        return description;
    }


    // Renvoie le type de carte tactique de la carte (TROUPE_ELITE etc..)
    public Type getType() {
        return type;
    }


    // Renvoie la liste des valeurs que peut prendre la carte quand elle se transforme
    public List<Integer> getValeursPoss(){
        return this.valeurs_possibles;
    }


    // Décrit l'effet de la carte
    public String getEffet(){
        return this.effet;
    }


    // ------------------------- SETTERS -------------------------

    // Setter pour 'nom'
    public void setNom(String nom) {
        this.nom = nom;
    }


    // Met à jour le nombre d'exemplaires de la carte à mettre dans la pioche initiale
    public void setNbrExemplaires(int nbr_exemplaires){
        this.nbr_exemplaires = nbr_exemplaires;
    }


    // Change la description de la carte
    public void setDescription(String description) {
        this.description = description;
    }


    // Setter pour le type de la carte
    public void setType(Type type) {
        this.type = type;
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



    // ------------------------- TYPES CUSTOMS -------------------------

    public enum Type {
        TROUPE_ELITE,
        MODE_COMBAT,
        RUSES
    }
}

