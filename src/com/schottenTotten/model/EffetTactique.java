package com.schottenTotten.model;

import java.util.Scanner;

public class EffetTactique {
    public boolean ajouterCarteTactique(int id_joueur, Carte_Tactique carteTactique, Borne borne) {
        switch (carteTactique.getType()) {
            case TROUPES_ELITE:
                return jouerTroupesElite(id_joueur, carteTactique, borne);

            case MODES_COMBAT:
                return appliquerModeCombat(id_joueur, carteTactique, borne);

            case RUSES:
                return jouerRuse(id_joueur, carteTactique, borne);

            default:
                System.err.println("Type de carte Tactique inconnu : " + carteTactique.getType());
                return false;
        }
    }



    private boolean jouerTroupesElite(int id_joueur, Carte_Tactique carteTactique, Borne borne) {
        switch (carteTactique.getNom()) {
            case "Joker":
                System.out.println("Effet : Peut prendre n'importe quelle couleur et valeur au moment de la revendication.");
                return effetJoker(borne, id_joueur);

            case "Espion":
                System.out.println("Effet : Carte de valeur 7 avec couleur choisie au moment de la revendication.");
                return true;

            case "Porte-Bouclier":
                System.out.println("Effet : Carte de valeur 1, 2 ou 3 avec couleur choisie au moment de la revendication.");
                return true;

            default:
                System.err.println("Troupe d'élite non reconnue : " + carteTactique.getNom());
                return false;
        }
    }


    private boolean appliquerModeCombat(int id_joueur, Carte_Tactique carteTactique, Borne borne) {
        switch (carteTactique.getNom()) {
            case "Colin-Maillard":
                System.out.println("Effet : Seule la somme des cartes compte pour la revendication.");
                return true;

            case "Combat de Boue":
                System.out.println("Effet : Chaque joueur doit poser 4 cartes pour revendiquer la borne.");
                return true;

            default:
                System.err.println("Mode de combat non reconnu : " + carteTactique.getNom());
                return false;
        }
    }


    private boolean jouerRuse(int id_joueur, Carte_Tactique carteTactique, Borne borne) {
        switch (carteTactique.getNom()) {
            case "Chasseur de Tête":
                System.out.println("Effet : Piochez 3 cartes, défaussez 2.");
                return true;

            case "Stratège":
                System.out.println("Effet : Déplacez ou défaussez une carte de votre côté.");
                // L'effet doit être géré par le contrôle du tour dans la partie
                return true;

            case "Banshee":
                System.out.println("Effet : Défaussez une carte adverse.");
                // L'effet doit être géré par le contrôle du tour dans la partie
                return true;

            case "Traître":
                System.out.println("Effet : Prenez une carte adverse et jouez-la de votre côté.");
                // Cet effet nécessite une gestion avec la classe Joueur
                return true;

            default:
                System.err.println("Ruse non reconnue : " + carteTactique.getNom());
                return false;
        }
    }




    public boolean effetJoker(Borne borne, int id_joueur) {
        Carte joker = new Carte(null, 0); // Joker initialisé sans couleur/valeur
        return borne.ajouterCarte(id_joueur, joker);
    }


    public void appeffetJoker(Carte joker) {
        Scanner scanner = new Scanner(System.in);

        // Choix de la couleur
        System.out.println("Choisissez une couleur pour le Joker : (ROUGE, BLEU, VERT, JAUNE, VIOLET, ROSE)");
        Carte.Couleur couleurChoisie;
        try {
            couleurChoisie = Carte.Couleur.valueOf(scanner.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Couleur invalide. La couleur par défaut ROUGE est utilisée.");
            couleurChoisie = Carte.Couleur.ROUGE;
        }

        // Choix de la valeur
        System.out.println("Choisissez une valeur pour le Joker (entre 1 et 9) :");
        int valeurChoisie;
        try {
            valeurChoisie = Integer.parseInt(scanner.nextLine());
            if (valeurChoisie < 1 || valeurChoisie > 9) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.err.println("Valeur invalide. La valeur par défaut 7 est utilisée.");
            valeurChoisie = 7;
        }
        scanner.close();

        // Configuration du Joker
        joker.setCouleur(couleurChoisie);
        joker.setValeur(valeurChoisie);
        System.out.println("Le Joker est configuré avec la couleur " + couleurChoisie + " et la valeur " + valeurChoisie);
    }
}
