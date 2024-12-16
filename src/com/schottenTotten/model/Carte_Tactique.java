package com.schottenTotten.model;

public class Carte_Tactique extends Carte {
    private String nom;
    private String description;
    private Type type;

    public Carte_Tactique(String nom, String description, Type type) {
        super(null, 0);
        this.nom = nom;
        this.description = description;
        this.type = type;
    }

    // Getter pour 'nom'
    public String getNom() {
        return nom;
    }

    // Setter pour 'nom'
    public void setNom(String nom) {
        this.nom = nom;
    }

    // Getter pour 'description'
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

    // Méthode toString
    @Override
    public String toString() {
        String part1 = "Carte_Tactique {" +
        "nom='" + nom + '\'' +
        ", description='" + description + '\'' +
        ", type=" + type;
        if(type == Type.TROUPE_ELITE){
            return part1 + "couleur=" + getCouleur() + "\"" + "valeur=" + getValeur() + "}";
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

