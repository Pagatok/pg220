package com.schottenTotten.model;

import java.util.Arrays;

import com.schottenTotten.model.Carte.Couleur;

public class Combinaison extends Card_list{

    private Card_list cartes;
    private Type type;
    private int max_cartes = 3; // Initilaisé à 3 mais des cartes tactiques peuvent la monter à 4

    public Combinaison() {
        this.cartes = new Card_list(4);
        this.type = Type.SOMME;
    }


    @Override
    public boolean ajouterCarte(Carte carte){
        if(cartes.nombreDeCartes() == max_cartes){
            return false;
        }
        else{
            return cartes.ajouterCarte(carte);
        }
    }


    // Renvoie le type de combinaison ()
    public Type getType(){
        return this.type;
    }


    public void setType(Type type){
        this.type = type;
    }


    public int getScore(){

        int somme = 0;

        for(int i = 0; i < nombreDeCartes(); i++){
            somme = somme + super.getValeurCarte(i);
        }

        return somme;
    }

    public int getMaxTaille(){
        return this.max_cartes;
    }


    public void setMaxTaille(int nbr_max_cartes){
        this.max_cartes = nbr_max_cartes;
    }


    

    // ------------------------- FONCTIONS PRIVEES -------------------------

    // Fonctions pour checker le type de combinaision
    private boolean check_suite() {
        if (nombreDeCartes() < 3) {
            return false; // Pas assez de cartes pour une suite
        }
    
        // Récupère les valeurs des cartes et les trie
        int[] valeurs = new int[nombreDeCartes()];
        for (int i = 0; i < nombreDeCartes(); i++) {
            valeurs[i] = getValeurCarte(i);
        }
        Arrays.sort(valeurs);
    
        // Vérifie si toutes les cartes sont consécutives
        for (int i = 1; i < valeurs.length; i++) {
            if (valeurs[i] != valeurs[i - 1] + 1) {
                return false;
            }
        }
        return true;
    }
    

    private boolean check_couleur() {
        if (nombreDeCartes() < 3) {
            return false; // Pas assez de cartes pour une couleur
        }
    
        Couleur premiereCouleur = getCouleurCarte(0);
        for (int i = 1; i < nombreDeCartes(); i++) {
            if (getCouleurCarte(i) != premiereCouleur) {
                return false;
            }
        }
        return true;
    }
    

    private boolean check_brelan(){

        // Récupère les valeurs des cartes et les trie
        int valeur1 = getValeurCarte(0);
        int valeur2 = getValeurCarte(1);
        int valeur3 = getValeurCarte(2);

        return (valeur1 == valeur2) && (valeur2 == valeur3);
    }

    private boolean check_suite_couleur(){
        return check_suite() && check_couleur();
    }

    // Renvoie le type de combinaision de la combianiaison actuelle
    public Type calculate_type() {
        if (nombreDeCartes() < 3) {
            return Type.SOMME; // Pas assez de cartes pour former un type avancé
        }
    
        if (check_suite_couleur()) {
            return Type.SUITE_COULEUR;
        } else if (check_brelan() && nombreDeCartes() == 3) { // Brelan reste limité à 3 cartes
            return Type.BRELAN;
        } else if (check_couleur()) {
            return Type.COULEUR;
        } else if (check_suite()) {
            return Type.SUITE;
        }
    
        return Type.SOMME;
    }

    // ------------------------- DEFINTION ENUM TYPE -------------------------


    public enum Type{
        SOMME,
        SUITE,
        COULEUR,
        BRELAN,
        SUITE_COULEUR
    }

    
    public String toString(){
        return "Cbn: " + cartes.toString() + ", type=" + type +'}';
    }

}
