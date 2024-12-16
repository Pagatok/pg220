package com.schottenTotten.model;

public class Carte {

    private int valeur;
    private Couleur couleur;

    // Constructeur de base
    public Carte(Couleur couleur, int valeur) {
        if (valeur < 0 || valeur > 9) {
            throw new IllegalArgumentException("La valeur doit être comprise entre 1 et 9. 0 si carte Tactique.");
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
        if (valeur < 0 || valeur > 9) {
            throw new IllegalArgumentException("La valeur doit être comprise entre 1 et 9. 0 si carte Tactique.");
        }
        this.valeur = new_valeur;
    }

    public void modifierCarte(Couleur nouvelleCouleur, int nouvelleValeur) {
        if (nouvelleValeur < 1 || nouvelleValeur > 9) {
            throw new IllegalArgumentException("La valeur doit être entre 1 et 9.");
        }
        this.couleur = nouvelleCouleur;
        this.valeur = nouvelleValeur;
    }
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Carte carte2 = (Carte) obj;
        if(this.valeur == carte2.valeur && this.couleur == carte2.couleur){
            return true;
        }
        else{
            return false;
        }
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
