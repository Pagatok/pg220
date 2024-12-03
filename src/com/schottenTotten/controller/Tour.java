package com.schottenTotten.controller;

import com.schottenTotten.ai.*;
import com.schottenTotten.model.*;
import com.schottenTotten.view.*;


public class Tour {

    // Décalaration d'un constructeur vide pour empecher l'instanciation
    private Tour(){
        throw new UnsupportedOperationException("Cette classe ne peut pas être instanciée.");
    }

    
    // Gere un tour de SchottenTotten pour un joueur réel
    // Renvoi 1 si la partie est terminée après ce tour, 0 sinon
    public static void gestion_tour_real(View vue, Frontiere frontiere, Joueur joueur_actif){

        int id_joueur = joueur_actif.getId();

        vue.afficherMessage("Joueur " + id_joueur + ", C'est votre tour!");

        vue.afficherFrontiere(frontiere);

        if(check_loose(joueur_actif, vue, frontiere)){
            return;
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
        int id_borne_revend = vue.select_revendication(frontiere);
        gestion_revend(id_borne_revend, frontiere, vue);
    }


    public static void gestion_tour_ia(View vue, Frontiere F, Joueur J){

        // on récupère l'IA
        Ai ia = getAi(J, vue);
        if(ia == null){
            System.err.println("Erreur: Récupération de l'IA");
            return;
        }

        
        // On vérifie si la main du joueur est vide, si c'est le cas, le joueur perd la partie
        if(check_loose(J, vue, F) == true){
            return;
        }

        // Le joueur sélectionne une carte de la main
        Carte carte_jouee = ia.select_card(J);
        J.retirerCarte(carte_jouee);

        // Puis il sélectionne la borne sur laquelle il veut la poser
        Borne borne = ia.select_borne(J, F);
        borne.ajouterCarte(J.getId(), carte_jouee);

        // Il sélectionne les bornes qu'il veut revendiquer
        int id_borne_revend = ia.select_revendication(F);
        gestion_revend(id_borne_revend, F, vue);
    }



    // ------------------------- FONCTIONS PRIVEES -------------------------


    private static boolean check_loose(Joueur J, View vue, Frontiere F){
        if(J.getTaillePied() == 0){
            vue.afficherMessage("Vous n'avez plus de cartes dans votre main et c'est à vous de jouer. Vous avez donc perdu");
            if(J.getId() == 1){
                vue.afficherWinner(2);
            }
            else{
                vue.afficherWinner(1);
            }
            F.setGameOver();
            return true;
        }
        else{
            return false;
        }     
    }


    // Gestion de la partie revendication du tour
    private static void gestion_revend(int id_borne_revend, Frontiere F, View vue){
        if(id_borne_revend != -1){
            Borne borne_revend = F.getBorne(id_borne_revend);
            borne_revend.determinerRevendication();
            vue.afficherMessage("Le joueur " + borne_revend.getIdJoueur() + " remporte la borne " + borne_revend.getId());

            // On vérifie si après la revendication on a un gagnant
            // Si c'est le cas on arrete le jeu et célèbre le gagnant
            int victorious = F.checkVictoire();
            if(victorious != 0){
                vue.afficherWinner(victorious);
            }
        }
    }


    private static Ai getAi(Joueur J, View vue){
        if(J.getNivIA() == 0){
            vue.afficherMessage("Le joueur n'est pas une IA, cette gestion de tour ne devrait pas être appellée");
            return null;
        }
        else if(J.getNivIA() == 1){
            Ai ia = new BasicAi();
            return ia;
        }
        else{
            vue.afficherMessage("Niveau d'AI non valide");
            return null;
        }
    }
}