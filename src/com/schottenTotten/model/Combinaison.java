package com.schottenTotten.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.schottenTotten.model.Carte.Couleur;

public class Combinaison {

    private final List<Carte> cartes;
    private Type type;

    public Combinaison() {
        this.cartes = new ArrayList<>();
        this.type = calculate_type();
    }

    public void ajouterCarte(Carte carte) {
        if (cartes.size() >= 3) {
            throw new IllegalStateException("Une combinaison ne peut contenir que 3 cartes au maximum.");
        }
        cartes.add(carte);
        this.type = calculate_type();
    }

    public List<Carte> getCartes() {
        return new ArrayList<>(cartes); // Retourne une copie pour protéger la liste originale
    }

    public int nombreDeCartes() {
        return cartes.size();
    }

    // Renvoie le type de combinaison ()
    public Type getType(){
        return this.type;
    }


    public int getScore(){

        // Récupère les valeurs des cartes et les trie
        int valeur1 = cartes.get(0).getValeur();
        int valeur2 = cartes.get(1).getValeur();
        int valeur3 = cartes.get(2).getValeur();

        int somme = valeur1 + valeur2 + valeur3;

        return somme;
    }


    // ------------------------- FONCTIONS PRIVEES -------------------------

    // Fonctions pour checker le type de combinaision
    private boolean check_suite(){

        if (cartes.size() != 3) {
            return false; // La combinaison doit contenir exactement 3 cartes pour former une suite
        }

        // Récupère les valeurs des cartes et les trie
        int valeur1 = cartes.get(0).getValeur();
        int valeur2 = cartes.get(1).getValeur();
        int valeur3 = cartes.get(2).getValeur();

        int[] valeurs = {valeur1, valeur2, valeur3};
        Arrays.sort(valeurs);

        // Vérifie si les valeurs sont consécutives
        return (valeurs[1] == valeurs[0] + 1) && (valeurs[2] == valeurs[1] + 1);
    }

    private boolean check_couleur(){
        if (cartes.size() != 3) {
            return false; // La combinaison doit contenir exactement 3 cartes pour former une suite
        }

        Couleur color1 = cartes.get(0).getCouleur();
        Couleur color2 = cartes.get(1).getCouleur();
        Couleur color3 = cartes.get(2).getCouleur();

        return (color1 == color2) && (color2 == color3);
    }

    private boolean check_brelan(){

        if (cartes.size() != 3) {
            return false; // La combinaison doit contenir exactement 3 cartes pour former une suite
        }

        int valeur1 = cartes.get(0).getValeur();
        int valeur2 = cartes.get(1).getValeur();
        int valeur3 = cartes.get(2).getValeur();

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

    @Override
    public String toString() {
        return "Combinaison{" +
                "cartes=" + cartes +
                '}';
    }
}
