package com.schottenTotten.controller;

import com.schottenTotten.model.*;
import com.schottenTotten.view.Interface;


public class Jeu {
    public static void jeu(String args[]){
        
        // Mise en place

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

        while(gaming){

            // Gestion d'un tour
            nbr_tours += 1;

            // On définit l'id du joueur dont c'est le tour
            int id_joueur = nbr_tours % 2;
            if(id_joueur == 0){
                id_joueur = 2;
            }

            frontiere.toString();
            System.out.println("\n");
            if(id_joueur == 1){
                J1.toString();
            }
            else{
                J2.toString();
            }
            
        }
    }
}
