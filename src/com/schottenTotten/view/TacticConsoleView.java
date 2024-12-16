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



    public void appeffetJoker(Carte joker) {

        // Choix de la couleur
        Carte.Couleur couleurChoisie = null;
        while (couleurChoisie == null) {
            System.out.println("Choisissez une couleur pour le Joker : (ROUGE, BLEU, VERT, JAUNE, VIOLET, ROSE)");
            String inputCouleur = scanner.nextLine().toUpperCase();
            try {
                couleurChoisie = Carte.Couleur.valueOf(inputCouleur);
            } 
            catch (IllegalArgumentException e) {
                System.out.println("Couleur invalide. Veuillez réessayer.");
            }
        }
        

        // Choix de la valeur
        int valeurChoisie = -1;
        while (valeurChoisie < 1 || valeurChoisie > 9) {
            System.out.println("Choisissez une valeur pour le Joker (entre 1 et 9) :");
            if (!scanner.hasNextInt()) {
                System.out.println("Valeur invalide. Veuillez entrer un entier entre 1 et 9.");
                scanner.next(); // Consommer l'entrée incorrecte
                continue;
            }
        
            valeurChoisie = scanner.nextInt();
            scanner.nextLine(); // Consommer la ligne restante
        
            if (valeurChoisie < 1 || valeurChoisie > 9) {
                System.out.println("Valeur invalide. La valeur doit être entre 1 et 9.");
            }
        }
        

        // Configuration du Joker
        joker.setCouleur(couleurChoisie);
        joker.setValeur(valeurChoisie);
        System.out.println("Le Joker est configuré avec la couleur " + couleurChoisie + " et la valeur " + valeurChoisie);
    }


    public void appeffetEspion(Carte espion) {
    
        // Choix de la couleur
        Carte.Couleur couleurChoisie = null;
        while (couleurChoisie == null) {
            System.out.println("Choisissez une couleur pour l'Espion : (ROUGE, BLEU, VERT, JAUNE, VIOLET, ROSE)");
            String inputCouleur = scanner.nextLine().toUpperCase();
            try {
                couleurChoisie = Carte.Couleur.valueOf(inputCouleur);
            } catch (IllegalArgumentException e) {
                System.out.println("Couleur invalide. Veuillez réessayer.");
            }
        }
    
        // Configuration de la carte Espion
        espion.setCouleur(couleurChoisie);
        espion.setValeur(7);
        System.out.println("L'Espion est configuré avec la couleur " + couleurChoisie + " et la valeur 7.");
    }
    


    public void appeffetPorteBouclier(Carte porteBouclier) {
    
        // Choix de la couleur
        Carte.Couleur couleurChoisie = null;
        while (couleurChoisie == null) {
            System.out.println("Choisissez une couleur pour le Porte-Bouclier : (ROUGE, BLEU, VERT, JAUNE, VIOLET, ROSE)");
            String inputCouleur = scanner.nextLine().toUpperCase();
            try {
                couleurChoisie = Carte.Couleur.valueOf(inputCouleur);
            } catch (IllegalArgumentException e) {
                System.out.println("Couleur invalide. Veuillez réessayer.");
            }
        }
    
        // Choix de la valeur
        int valeurChoisie = -1;
        while (valeurChoisie < 1 || valeurChoisie > 3) {
            System.out.println("Choisissez une valeur pour le Porte-Bouclier (1, 2 ou 3) :");
            if (!scanner.hasNextInt()) {
                System.out.println("Valeur invalide. Veuillez entrer un entier entre 1 et 3.");
                scanner.next(); // Consomme l'entrée incorrecte
                continue;
            }
    
            valeurChoisie = scanner.nextInt();
            scanner.nextLine(); // Consommer la ligne restante
    
            if (valeurChoisie < 1 || valeurChoisie > 3) {
                System.out.println("Valeur invalide. La valeur doit être entre 1 et 3.");
            }
        }
    
        // Configuration de la carte Porte-Bouclier
        porteBouclier.setCouleur(couleurChoisie);
        porteBouclier.setValeur(valeurChoisie);
        System.out.println("Le Porte-Bouclier est configuré avec la couleur " + couleurChoisie + " et la valeur " + valeurChoisie + ".");
    }
    
}

