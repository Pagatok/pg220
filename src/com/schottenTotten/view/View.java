package com.schottenTotten.view;

import java.util.List;

import com.schottenTotten.model.*;

public interface View {

    // Fonctions d'affichages
    void afficherFrontiere(Frontiere frontiere);
    void afficherJoueur(Joueur J);
    void afficherMessage(String message);
    void afficherBorne(Borne borne);
    void afficherWinner(Joueur winner);
    void afficherTour(Joueur active_player, int nbr_tours);
    void afficherDebut();

    // Fonctions d'input
    Carte select_card(Joueur J);
    Borne select_borne(Joueur J, Frontiere F);
    Borne select_revendication(Frontiere F, List <Integer> liste_revendiquation);
    Joueur select_ia(int id_joueur, int nivmax_ia, int taille_max_main);
    boolean select_variante();
    int select_pioche();

    // Focntions tactiques
    Carte create_card(List<Integer> valeursPossibles);
} 
