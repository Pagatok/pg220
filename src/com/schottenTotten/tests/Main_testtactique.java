package com.schottenTotten.tests;

import com.schottenTotten.model.*;

public class Main_testtactique{
    public static void main(String[] args) {
        // Création d'une Borne
        Borne borne = new Borne(1);

        // Création d'une carte Joker
        Carte_Tactique joker = new Carte_Tactique("Joker",
                "Peut prendre n'importe quelle couleur et valeur au moment de la revendication.",
                Carte_Tactique.Type.TROUPES_ELITE);

        EffetTactique effet = new EffetTactique();
        effet.ajouterCarteTactique(1, joker, borne, null);

        System.out.println("revvendic");
        // Revendication
        borne.determinerRevendication();

        // Affichage
        System.out.println(borne);
    }
}

