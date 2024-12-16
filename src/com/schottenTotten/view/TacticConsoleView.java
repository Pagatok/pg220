package com.schottenTotten.view;

import java.util.Scanner;
import com.schottenTotten.model.*;

public class TacticConsoleView extends ConsoleView{

    private Scanner scanner = new Scanner(System.in);



    // public boolean ajouterCarteTactique(int id_joueur, Carte_Tactique carteTactique, Borne borne, Joueur joueur) {
    //     boolean effetApplique = false;

    //     switch (carteTactique.getType()) {
    //         case TROUPE_ELITE:
    //             effetApplique = jouerTroupesElite(id_joueur, carteTactique, borne);
    //             break;
    //         case MODES_COMBAT:
    //             effetApplique = appliquerModeCombat(id_joueur, carteTactique, borne);
    //             break;
    //         default:
    //             System.err.println("Type de carte Tactique inconnu : " + carteTactique.getType());
    //             return false;
    //     }

    //     if (effetApplique) {
    //         joueur.retirerCarteTactique(carteTactique);
    //     }
    //     return effetApplique;
    // }


    // private boolean jouerTroupesElite(int id_joueur, Carte_Tactique carteTactique, Borne borne) {
    //     switch (carteTactique.getNom()) {
    //         case "Joker":
    //             System.out.println("Effet : Peut prendre n'importe quelle couleur et valeur au moment de la revendication.");
    //             return effetJoker(borne, id_joueur);

    //         case "Espion":
    //             System.out.println("Effet : Carte de valeur 7 avec couleur choisie au moment de la revendication.");
    //             return effetEspion(borne, id_joueur);

    //         case "Porte-Bouclier":
    //             System.out.println("Effet : Carte de valeur 1, 2 ou 3 avec couleur choisie au moment de la revendication.");
    //             return effetJoker(borne, id_joueur);

    //         default:
    //             System.err.println("Troupe d'élite non reconnue : " + carteTactique.getNom());
    //             return false;
    //     }
    // }


    // private boolean appliquerModeCombat(int id_joueur, Carte_Tactique carteTactique, Borne borne) {
    //     switch (carteTactique.getNom()) {
    //         case "Colin-Maillard":
    //             System.out.println("Effet : Seule la somme des cartes compte pour la revendication.");
    //             borne.ajouterEffetTactique(carteTactique);
    //             borne.activerColinMaillard();
    //             return true;

    //         case "Combat de Boue":
    //             System.out.println("Effet : Chaque joueur doit poser 4 cartes pour revendiquer la borne.");
    //             borne.ajouterEffetTactique(carteTactique);
    //             borne.activerCombatDeBoue();
    //             return true;

    //         default:
    //             System.err.println("Mode de combat non reconnu : " + carteTactique.getNom());
    //             return false;
    //     }
    // }


    // public boolean effetJoker(Borne borne, int id_joueur) {
    //     Carte joker = new Carte(null, 0); // Joker initialisé sans couleur/valeur
    //     return borne.ajouterCarte(id_joueur, joker);
    // }

    // public boolean effetEspion(Borne borne, int id_joueur) {
    //     Carte espion = new Carte(null, 7); // Espion initialisé avec valeur 7
    //     return borne.ajouterCarte(id_joueur, espion);
    // }



    // LAISSER LA




}

