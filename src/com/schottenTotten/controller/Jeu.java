package com.schottenTotten.controller;

import java.util.Scanner;

import com.schottenTotten.model.*;
import com.schottenTotten.view.ConsoleView;
import com.schottenTotten.view.View;


public class Jeu {   
    
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

    public static void main(String args[]){

        View vue = new ConsoleView();

        
        // Mise en place

        vue.afficherMessage("Début de la mise en place du jeu..");

        Frontiere frontiere = new Frontiere();
        Joueur J1 = new Joueur(1);
        Joueur J2 = new Joueur(2);

        Pioche pioche = new Pioche();
        pioche.shuffle();

        for(int i = 0; i<6; i++){
            J1.ajouterCarte(pioche.piocher());
            J2.ajouterCarte(pioche.piocher());
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

            vue.afficherFrontiere(frontiere);

            // Le joueur sélectionne une carte de la main
            vue.afficherJoueur(joueur_actif);
            Carte carte_jouee = vue.select_card(joueur_actif);
            joueur_actif.retirerCarte(carte_jouee);

            // Puis il sélectionne la borne sur laquelle il veut la poser
            Borne borne = vue.select_borne(joueur_actif, frontiere);
            borne.ajouterCarte(id_joueur, carte_jouee);
            vue.afficherMessage("Carte ajoutée sur la borne" + borne.getId());

            vue.afficherFrontiere(frontiere);
            

            gaming = false;
            
        }
    }
}
