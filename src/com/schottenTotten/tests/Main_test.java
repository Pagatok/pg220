package com.schottenTotten.tests;

import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Combinaison;

public class Main_test {

    public static void main(String[] args){
        Carte carte_1 = new Carte(Carte.Couleur.ROUGE, 1);
        Carte carte_2 = new Carte(Carte.Couleur.ROUGE, 2);
        Carte carte_3 = new Carte(Carte.Couleur.ROUGE, 3);
        Combinaison combinaison = new Combinaison();
        combinaison.ajouterCarte(carte_1);
        combinaison.ajouterCarte(carte_2);
        combinaison.ajouterCarte(carte_3);

        System.out.println(combinaison.toString());

    }

    
}
