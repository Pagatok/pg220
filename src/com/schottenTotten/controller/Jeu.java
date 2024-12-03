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

        // Création des joueurs
        Joueur J1 = vue.select_ia(1, 1);
        Joueur J2 = vue.select_ia(2, 1);

        // Initialisation de la pioche
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
            if(nbr_tours % 2 == 0){
                joueur_actif = J2;
            }
            else{
                joueur_actif = J1;
            }

            // on gére le tour du joueur
            if(joueur_actif.isIA() == false){
                Tour.gestion_tour_real(vue, frontiere, joueur_actif);
            }
            else{
                Tour.gestion_tour_ia(vue, frontiere, joueur_actif);
            }

            // On vérifie si la parte est gagnée
            if(frontiere.is_gameover() == true){
                gaming = false;
            }

            // Quand il finit son tour le joueur pioche et on cache sa main
            joueur_actif.ajouterCarte(pioche.piocher());
            System.out.println("Nbr cartes dans la main: " + joueur_actif.getTaillePied());

        }
    }
}
