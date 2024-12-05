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




    public void setVariante(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez la variante du jeu :");
        System.out.println("1. Basique");
        System.out.println("2. Tactique");

        int choix = scanner.nextInt();
        scanner.nextLine(); 

        if(choix ==1){
            this.variante = Variante.BASIQUE;
        }
        else if(choix == 2){
            this.variante = Variante.TACTIQUE;
        }
        else{
            System.out.println("Veuillez choisir une variante valide");
            this.variante = Variante.BASIQUE;
        }
    }
    
    public static void main(String args[]){
        Jeu jeu = new Jeu();
        jeu.setVariante();
        View vue = new ConsoleView();

        // Mise en place

        vue.afficherMessage("Début de la mise en place du jeu..");

        boolean modeTactique = (jeu.variante == Variante.TACTIQUE);
        Frontiere frontiere = new Frontiere();
        Joueur J1 = new Joueur(1, modeTactique);
        Joueur J2 = new Joueur(2, modeTactique);

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
        vue.afficherMessage("Début de la partie..\n");

        while(gaming){

            // Gestion d'un tour
            nbr_tours += 1;


            // Choix du joueur actif
            Joueur joueur_actif;
            int id_joueur;
            if(nbr_tours % 2 == 0){
                joueur_actif = J2;
                id_joueur = 2;
            }
            else{
                joueur_actif = J1;
                id_joueur = 1;
            }

            vue.afficherMessage("Joueur " + id_joueur + ", C'est votre tour!");

            vue.afficherFrontiere(frontiere);


            if(modeTactique){
                if(nbr_tours>2){
                    Scanner scanner = new Scanner(System.in);
                    vue.afficherMessage("Choisissez la pioche :");
                    vue.afficherMessage("1. Pioche Clan");
                    vue.afficherMessage("2. Pioche Tactique");
                    int choixPioche = scanner.nextInt();
                    scanner.nextLine(); // Consommer la ligne

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
        }
    }
}
