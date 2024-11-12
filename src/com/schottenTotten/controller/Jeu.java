package com.schottenTotten.controller;

import com.schottenTotten.model.*;


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

            // Selon que ce soit au J1 ou au J2 de joueru
            if(nbr_tours % 2 == 1){
                
            }
        }
    }
}
