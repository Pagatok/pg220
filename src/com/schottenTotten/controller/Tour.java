package com.schottenTotten.controller;

import com.schottenTotten.ai.*;
import com.schottenTotten.model.*;
import com.schottenTotten.view.*;

import java.util.List;

public class Tour {

    // Décalaration d'un constructeur vide pour empecher l'instanciation
    private Tour(){
        throw new UnsupportedOperationException("Cette classe ne peut pas être instanciée.");
    }

    
    // Gere un tour de SchottenTotten pour un joueur réel
    // Renvoi 1 si la partie est terminée après ce tour, 0 sinon
    public static void gestion_tour_real(View vue, Frontiere frontiere, Joueur joueur_actif, Joueur autre_joueur){

        int id_joueur = joueur_actif.getId();

        vue.afficherFrontiere(frontiere);

        if(check_loose(joueur_actif, vue, frontiere)){
            return;
        }

        // Le joueur sélectionne une carte de la main
        vue.afficherJoueur(joueur_actif);
        Carte carte_jouee = vue.select_card(joueur_actif);
        joueur_actif.retirerCarte(carte_jouee);
        System.out.println(carte_jouee.toString());

        // Puis il sélectionne la borne sur laquelle il veut la poser
        Borne borne = vue.select_borne(joueur_actif, frontiere);
        borne.ajouterCarte(id_joueur, carte_jouee);
        vue.afficherMessage("Carte ajoutée sur la borne " + borne.getId());
        if(carte_jouee instanceof Carte_Tactique){
            EffetsTactiques.gestionTacticPostposeCarte(borne, (Carte_Tactique)carte_jouee, vue);
        }

        vue.afficherFrontiere(frontiere);

        // On regarde quelles bornes sont revendiquables
        List <Integer> listes_revendiquables = frontiere.getRevendiquables();
        if(listes_revendiquables.size() == 0){
            vue.afficherMessage("Aucunes bornes revendiquables ce tour-ci");
        }
        else{
            // Il sélectionne les bornes qu'il veut revendiquer
            Borne borne_revend = vue.select_revendication(frontiere, listes_revendiquables);
            if(borne_revend != null){
                gestion_revend(borne_revend, frontiere, vue, joueur_actif, autre_joueur);
            }
        } 
    }


    public static void gestion_tour_ia(View vue, Frontiere F, Joueur J, Joueur passive_player){

        // on récupère l'IA
        Ai ia = getAi(J, vue);
        if(ia == null){
            System.err.println("Erreur: Récupération de l'IA");
            return;
        }

        
        // On vérifie si la main du joueur est vide, si c'est le cas, le joueur perd la partie
        if(check_loose(J, vue, F) == true){
            vue.afficherWinner(passive_player);
            return;
        }

        // Le joueur sélectionne une carte de la main
        Carte carte_jouee = ia.select_card(J);
        J.retirerCarte(carte_jouee);

        // Puis il sélectionne la borne sur laquelle il veut la poser
        Borne borne = ia.select_borne(J, F);
        if(borne == null){
            System.out.println("Tour: L'IA est bloqué sur select_borne");
        }
        else{
            borne.ajouterCarte(J.getId(), carte_jouee);
            if(carte_jouee instanceof Carte_Tactique){
                EffetsTactiques.gestionTacticPostposeCarte(borne, (Carte_Tactique)carte_jouee, vue);
            }
        }

        // Il sélectionne les bornes qu'il veut revendiquer
        int id_borne = ia.select_revendication(F);
        if(id_borne != -1){
            Borne borne_revend = F.getBorne(id_borne+1);
            gestion_revend(borne_revend, F, vue, J, passive_player);
        }
    }


    public static Ai getAi(Joueur J, View vue){
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



    // ------------------------- FONCTIONS PRIVEES -------------------------


    private static boolean check_loose(Joueur J, View vue, Frontiere F){
        if(J.getTaillePied() == 0){
            vue.afficherMessage("Vous n'avez plus de cartes dans votre main et c'est à vous de jouer. Vous avez donc perdu");
            F.setGameOver();
            return true;
        }
        else{
            return false;
        }     
    }


    // Gestion de la partie revendication du tour
    private static void gestion_revend(Borne borne_revend, Frontiere F, View vue, Joueur J1, Joueur J2){

        EffetsTactiques.gestionTactic(J1, borne_revend.getCombinaison(1), vue);
        EffetsTactiques.gestionTactic(J2, borne_revend.getCombinaison(2), vue);
        borne_revend.determinerRevendication();

        String name_joueur;
        if(borne_revend.getIdJoueur() == 1){
            name_joueur = J2.getName();
        }
        else{
            name_joueur = J1.getName();
        }
        
        vue.afficherMessage("" + name_joueur + " remporte la borne " + borne_revend.getId());

        // On vérifie si après la revendication on a un gagnant
        // Si c'est le cas on arrete le jeu et célèbre le gagnant
        int victorious = F.checkVictoire();
        if(victorious == 1){
            vue.afficherWinner(J1);
        }
        else if(victorious == 2){
            vue.afficherWinner(J2);
        }  
    }


    // private static void configurerTroupes(Borne borne, View vue) {

    //     Combinaison J1 = borne.getCombinaison(1);
    //     Combinaison J2 = borne.getCombinaison(2);
    
    //     // Configurer les Jokers dans la combinaison de J1
    //     for (Carte carte : J1.getCartes()) {
    //         if(carte instanceof Carte_Tactique){
    //             Carte_Tactique carteTactique = (Carte_Tactique) carte;
    //             System.out.println("Carte Tactique trouvé dans la borne");
    //             switch (carteTactique.getNom()) {
    //                 case "Joker":
    //                     System.out.println("Joker trouvé !");
    //                     vue.appeffetJoker(carte);
    //                     break;
    //                 case "Espion":
    //                     System.out.println("Espion trouvé !");
    //                     vue.appeffetEspion(carte);
    //                     break;
    //                 case "Porte-Bouclier":
    //                     System.out.println("Porte-bouclier trouvé !");
    //                     vue.appeffetPorteBouclier(carte);
    //                     break;
    //                 default:
    //                     break;
    //             }
    //         }
    //     }
    //     // Recalculer le type après configuration
    //     J1.calculate_type();
    
    //     // Configurer les Jokers dans la combinaison de J2
    //     for (Carte carte : J2.getCartes()) {
    //         if(carte instanceof Carte_Tactique){
    //             Carte_Tactique carteTactique = (Carte_Tactique) carte;
    //             switch (carteTactique.getNom()) {
    //                 case "Joker":
    //                     vue.appeffetJoker(carte);
    //                     break;
    //                 case "Espion":
    //                     vue.appeffetEspion(carte);
    //                     break;
    //                 case "Porte-Bouclier":
    //                     vue.appeffetPorteBouclier(carte);
    //                     break;
    //                 default:
    //                     break;
    //             }
    //         }
    //     }
    //     // Recalculer le type après configuration
    //     J2.calculate_type();
    // }

}