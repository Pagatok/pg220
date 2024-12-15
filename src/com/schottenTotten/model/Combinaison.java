package com.schottenTotten.model;

import java.util.Arrays;

import com.schottenTotten.model.Carte.Couleur;

public class Combinaison extends Card_list{

    private Type type;
    private int maxCartes = 3;

    public void setMaxCartes(int maxCartes) {
        this.maxCartes = maxCartes;
    }
    
    public int getMaxCartes() {
        return this.maxCartes;
    }
    
    public Combinaison() {
        super(3);
        this.type = Type.SOMME;
    }


    @Override
    public boolean ajouterCarte(Carte carte) {
        if (nombreDeCartes() >= maxCartes) {
            System.out.println("Cette combinaison ne peut contenir que " + maxCartes + " cartes au maximum.");
            return false;
        }
    
        if (super.ajouterCarte(carte)) {
            this.type = calculate_type(); // Recalcule le type après ajout
            return true;
        }
        return false;
    }
    

    // Renvoie le type de combinaison ()
    public Type getType(){
        return this.type;
    }


    public int getScore(){
        if (nombreDeCartes() == 0) {
            System.out.println("erreur de calcul de score");
            return 0; // Aucun score si aucune carte
        }
        
        int somme = 0;

        for(int i = 0; i < nombreDeCartes(); i++){
            somme += getValeurCarte(i);
        }

        return somme;
    }

    // Recalcule le type de la combinaison
    public void recalculerType() {
        this.type = calculate_type();
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
    private Type calculate_type() {
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

    
    @Override
    public String toString() {
        return "Cbn: " + super.toString() + ", type=" + type + ", maxCartes=" + maxCartes + "}";
    }
    

}
