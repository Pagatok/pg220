package com.schottenTotten.model;

public class Carte {

    private int valeur;
    private Couleur couleur;

    // Constructeur de base
    public Carte(Couleur couleur, int valeur) {
        if (valeur < 1 || valeur > 9) {
            throw new IllegalArgumentException("La valeur doit être comprise entre 1 et 9.");
        }
        this.couleur = couleur;
        this.valeur = valeur;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public int getValeur() {
        return valeur;
    }

    public void setCouleur(Couleur new_color){
        this.couleur = new_color;
    }

    public void setValeur(int new_valeur){
        if (valeur < 1 || valeur > 9) {
            throw new IllegalArgumentException("La valeur doit être comprise entre 1 et 9.");
        }
        this.valeur = new_valeur;
    }

    // Définition des couleurs
    public enum Couleur {
        ROUGE,
        BLEU,
        VERT,
        JAUNE,
        VIOLET,
        ROSE
    }

    @Override
    public String toString() {
        return "{" +
                "couleur=" + couleur +
                ", valeur=" + valeur +
                '}';
    }

}
