package com.schottenTotten.model;

import java.util.List;

public class Borne {
    private Combinaison J1;
    private Combinaison J2;
    private boolean revendique;
    private int id_joueur; // 1 pour J1, 2 pour J2, 0 si neutre

    public Borne(Combinaison J1, Combinaison J2) {
        this.J1 = J1;
        this.J2 = J2;
        this.revendique = false; // Initialement non revendiquée
        this.id_joueur = 0;      // Neutre initialement
    }

    // Méthode pour comparer les combinaisons et déterminer le gagnant
    public void determinerRevendication() {
        // Comparaison des types de combinaison
        int comparaisonType = comparerTypes(J1.getType(), J2.getType());

        if (comparaisonType > 0) {
            // J1 a une meilleure combinaison
            this.id_joueur = 1;
        } else if (comparaisonType < 0) {
            // J2 a une meilleure combinaison
            this.id_joueur = 2;
        } else {
            // Types égaux, comparaison des scores
            int scoreJ1 = J1.getScore();
            int scoreJ2 = J2.getScore();

            if (scoreJ1 > scoreJ2) {
                this.id_joueur = 1;
            } else if (scoreJ1 < scoreJ2) {
                this.id_joueur = 2;
            } else {
                // Score égal, reste neutre
                this.id_joueur = 0;
            }
        }
        
        // Si un joueur l'a revendiquée, mettre à jour `revendique`
        this.revendique = (this.id_joueur != 0);
    }

    // Méthode utilitaire pour comparer les types de combinaisons
    private int comparerTypes(String typeJ1, String typeJ2) {
        // Exemple d'ordre de puissance des types
        List<String> types = List.of("Suite de même couleur", "Brelan", "Couleur", "Suite", "Somme");

        int indexJ1 = types.indexOf(typeJ1);
        int indexJ2 = types.indexOf(typeJ2);

        return Integer.compare(indexJ2, indexJ1); // Plus petit index signifie une meilleure combinaison
    }

    // Getters pour accéder aux informations de la borne
    public boolean isRevendique() {
        return revendique;
    }

    public int getIdJoueur() {
        return id_joueur;
    }
}
