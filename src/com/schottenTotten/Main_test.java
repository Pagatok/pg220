package com.schottenTotten;

import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Combinaison;

public class Main_test {

    public static void main(String[] args){
        Carte carte_1 = new Carte(Carte.Couleur.ROUGE, 1);
        Combinaison combinaison = new Combinaison();
        combinaison.ajouterCarte(carte_1);

        System.out.println(combinaison.toString());

    }

    
}
