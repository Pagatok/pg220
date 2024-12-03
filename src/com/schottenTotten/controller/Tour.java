package com.schottenTotten.controller;

import com.schottenTotten.ai.*;
import com.schottenTotten.model.*;
import com.schottenTotten.view.*;


public class Tour {

    private Tour(){
        throw new UnsupportedOperationException("Cette classe ne peut pas être instanciée.");
    }

    
    // Gere un tour de SchottenTotten pour un joueur réel
    // Renvoi 1 si la partie est terminée après ce tour, 0 sinon
    public static void gestion_tour_real(View vue, Frontiere frontiere, Joueur joueur_actif){

        int id_joueur = joueur_actif.getId();

        vue.afficherMessage("Joueur " + id_joueur + ", C'est votre tour!");

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

        // Il sélectionne les bornes qu'il veut revendiquer
        Borne borne_revend = vue.select_revendication(frontiere);
        if(borne_revend != null){
            borne.determinerRevendication();
            vue.afficherMessage("Le joueur " + borne.getIdJoueur() + " remporte la borne " + borne.getId());

            // On vérifie si après la revendication on a un gagnant
            // Si c'est le cas on arrete le jeu et célèbre le gagnant
            int victorious = frontiere.checkVictoire();
            if(victorious != 0){
                vue.afficherWinner(victorious);
            }
        }
    }


    public static void gestion_tour_ia(View vue, Frontiere F, Joueur J){

        // on récupère l'IA
        Ai ia;
        if(J.getNivIA() == 0){
            vue.afficherMessage("Le joueur n'est pas une IA, cette gestion de tour ne devrait pas être appellée");
            return;
        }
        else if(J.getNivIA() == 1){
            ia = new BasicAi();
        }
        else{
            vue.afficherMessage("Niveau d'AI non valide");
            return;
        }

        // Le joueur sélectionne une carte de la main
        Carte carte_jouee = ia.select_card(J);
        J.retirerCarte(carte_jouee);

        // Puis il sélectionne la borne sur laquelle il veut la poser
        Borne borne = ia.select_borne(J, F);
        borne.ajouterCarte(J.getId(), carte_jouee);

        // Il sélectionne les bornes qu'il veut revendiquer
        Borne borne_revend = ia.select_revendication(F);
        if(borne_revend != null){
            borne.determinerRevendication();
            vue.afficherMessage("Le joueur " + borne.getIdJoueur() + " remporte la borne " + borne.getId());

            // On vérifie si après la revendication on a un gagnant
            // Si c'est le cas on arrete le jeu et célèbre le gagnant
            int victorious = F.checkVictoire();
            if(victorious != 0){
                vue.afficherWinner(victorious);
            }
        }
    }
}
