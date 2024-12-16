package com.schottenTotten.controller;

import java.util.Scanner;

import com.schottenTotten.model.*;
import com.schottenTotten.view.ConsoleView;
import com.schottenTotten.view.View;


public class Jeu {   
    public enum Variante{
        BASIQUE,
        TACTIQUE,
    }

    private Variante variante;

    public View select_view(){
        // Choix du mode d'affichage
        // Demander à l'utilisateur quel mode choisir
        System.out.println("Choisissez le mode d'affichage :");
        System.out.println("1. Console");

        Scanner scanner = new Scanner(System.in);
        int choix = scanner.nextInt();
        scanner.nextLine(); // Consommer la ligne

        // Instancier l'objet `View` dynamiquement
        View vue;
        if (choix == 1) {
            vue = new ConsoleView();
        } else {
            System.out.println("Veuillez chossir un mode d'affichage valide\n");
            vue = new ConsoleView();
        }

        scanner.close();

        return vue;
    }




    public void setVariante(Scanner scanner) {
        System.out.println("Choisissez la variante du jeu :");
        System.out.println("1. Basique");
        System.out.println("2. Tactique");
    
        int choix = scanner.nextInt();
        scanner.nextLine();
    
        if (choix == 1) {
            this.variante = Variante.BASIQUE;
        } else if (choix == 2) {
            this.variante = Variante.TACTIQUE;
        } else {
            System.out.println("Veuillez choisir une variante valide");
            this.variante = Variante.BASIQUE;
        }
    }
    
    
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in); // Créer un scanner global pour tout le programme
        Jeu jeu = new Jeu();
        jeu.setVariante(scanner);
        View vue = new ConsoleView();

<<<<<<< HEAD

        
=======
>>>>>>> origin/ines_tactique
        // Mise en place

        vue.afficherMessage("Début de la mise en place du jeu..");

        boolean modeTactique = (jeu.variante == Variante.TACTIQUE);
        Frontiere frontiere = new Frontiere();
<<<<<<< HEAD
=======
        Joueur J1 = new Joueur(1, modeTactique);
        Joueur J2 = new Joueur(2, modeTactique);
>>>>>>> origin/ines_tactique

        // Création des joueurs
        Joueur J1 = vue.select_ia(1, 1);
        Joueur J2 = vue.select_ia(2, 1);

        // Initialisation de la pioche
        Pioche pioche = new Pioche();
        pioche.shuffle();
        
        if(modeTactique){
            for(int i = 0; i<7; i++){
                J1.ajouterCarte(pioche.piocher());
                J2.ajouterCarte(pioche.piocher());
            }
        }
        else{
            for(int i = 0; i<6; i++){
                J1.ajouterCarte(pioche.piocher());
                J2.ajouterCarte(pioche.piocher());
            }
        }

        

        boolean gaming = true;
        int nbr_tours = 0;

        vue.afficherMessage("Fin de la mise en place du jeu!");
        vue.afficherDebut();

        while(gaming){

            // Gestion d'un tour
            nbr_tours += 1;


            // Choix du joueur actif
            Joueur joueur_actif;
            Joueur passive_player;
            if(nbr_tours % 2 == 0){
                joueur_actif = J2;
                passive_player = J1;
            }
            else{
                joueur_actif = J1;
                passive_player = J2;
            }

            // On dit au joueur que c'est à lui de jouer
            vue.afficherTour(joueur_actif);

            // on gére le tour du joueur
            if(joueur_actif.isIA() == false){
                Tour.gestion_tour_real(vue, frontiere, joueur_actif, passive_player);
            }
            else{
                Tour.gestion_tour_ia(vue, frontiere, joueur_actif, passive_player);
            }

<<<<<<< HEAD
            // On vérifie si la parte est gagnée
            if(frontiere.is_gameover() == true){
                gaming = false;
                break;
            }

            // Quand il finit son tour le joueur pioche et on cache sa main
            Carte carte = pioche.piocher();
            if(carte != null){
                joueur_actif.ajouterCarte(carte);
            }
            else{
                vue.afficherMessage("On continue la partie sans piocher\n");
            }
=======

            if(modeTactique){
                if(nbr_tours>2){
                    
                    int choixPioche = -1;
                    while (choixPioche != 1 && choixPioche != 2) {
                        System.out.println("Choisissez la pioche :");
                        System.out.println("1. Pioche Clan");
                        System.out.println("2. Pioche Tactique");
                    
                        if (!scanner.hasNextInt()) {
                            System.out.println("Entrée invalide. Veuillez choisir 1 ou 2.");
                            scanner.next(); // Consomme l'entrée incorrecte
                            continue;
                        }
                    
                        choixPioche = scanner.nextInt();
                        scanner.nextLine(); // Consommer la ligne restante
                    
                        if (choixPioche != 1 && choixPioche != 2) {
                            System.out.println("Choix invalide. Veuillez sélectionner 1 pour Clan ou 2 pour Tactique.");
                        }
                    }
                    
                    

                    if (choixPioche == 1) {
                        joueur_actif.piocherCarte(pioche, false);
                    } else if (choixPioche == 2) {
                        joueur_actif.piocherCarte(pioche, true);
                    } else {
                        vue.afficherMessage("Choix invalide. Vous piochez dans la Pioche Clan.");
                        joueur_actif.piocherCarte(pioche, false);
                    }
                }
            }
            else{
                joueur_actif.piocherCarte(pioche, false);
            }

            // Le joueur sélectionne une carte de la main
            vue.afficherJoueur(joueur_actif);
            Carte carte_jouee = vue.select_card(joueur_actif);
            joueur_actif.retirerCarte(carte_jouee);

            // Puis il sélectionne la borne sur laquelle il veut la poser
            Borne borne = vue.select_borne(joueur_actif, frontiere);
            borne.ajouterCarte(id_joueur, carte_jouee);
            vue.afficherMessage("Carte ajoutée sur la borne" + borne.getId());

            vue.afficherFrontiere(frontiere);

            // Il sélectionne les bornes qu'il veut revendiquer
            Borne borne_revend = vue.select_revendication(frontiere);
            if (borne_revend != null) {
                borne_revend.determinerRevendication();
                if (borne_revend.isRevendique()) {
                    vue.afficherMessage("Le joueur " + borne_revend.getIdJoueur() + " remporte la borne " + borne_revend.getId());
                }
            
                // Vérifiez si un joueur a gagné
                int victorious = frontiere.checkVictoire();
                if (victorious != 0) {
                    vue.afficherWinner(victorious);
                    gaming = false;
                }
            }
>>>>>>> origin/ines_tactique
        }

        scanner.close();
    }
}
