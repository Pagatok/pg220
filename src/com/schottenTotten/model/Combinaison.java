package com.schottenTotten.model;

import java.util.Arrays;

import com.schottenTotten.model.Carte.Couleur;

public class Combinaison extends Card_list{

    private Card_list cartes;
    private Type type;

    public Combinaison() {
        this.cartes = new Card_list();
        this.type = Type.SOMME;
    }


    public int ajouterCarte(Carte carte){
        int taille_max = 3;
        if(super.ajouterCarte(carte, taille_max) == 1){
            calculate_type();
            return 1;
        }
        return -1;
    }


    // Renvoie le type de combinaison ()
    public Type getType(){
        return this.type;
    }


    

    // ------------------------- FONCTIONS PRIVEES -------------------------

    // Fonctions pour checker le type de combinaision
    private boolean check_suite(){

        if (cartes.size() != 3) {
            return false; // La combinaison doit contenir exactement 3 cartes pour former une suite
        }

        // Récupère les valeurs des cartes et les trie
        int valeur1 = getValeurCarte(0);
        int valeur2 = getValeurCarte(1);
        int valeur3 = getValeurCarte(2);

        int[] valeurs = {valeur1, valeur2, valeur3};
        Arrays.sort(valeurs);

        // Vérifie si les valeurs sont consécutives
        return (valeurs[1] == valeurs[0] + 1) && (valeurs[2] == valeurs[1] + 1);
    }

    private boolean check_couleur(){
        if (cartes.size() != 3) {
            return false; // La combinaison doit contenir exactement 3 cartes pour former une suite
        }

        Couleur color1 = getCouleurCarte(0);
        Couleur color2 = getCouleurCarte(1);
        Couleur color3 = getCouleurCarte(2);


        return (color1 == color2) && (color2 == color3);
    }

    private boolean check_brelan(){

        if (cartes.size() != 3) {
            return false; // La combinaison doit contenir exactement 3 cartes pour former une suite
        }

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
    private Type calculate_type(){
        if(nombreDeCartes() < 3){
            return Type.SOMME;
        }
        if(check_suite_couleur()){
            return Type.SUITE_COULEUR;
        }
        else if(check_brelan()){
            return Type.BRELAN;
        }
        else if(check_couleur()){
            return Type.COULEUR;
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
        return "Combinaison: " + super.toString() + ", type=" + type +'}';
    }

}
