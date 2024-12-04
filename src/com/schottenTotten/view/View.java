package com.schottenTotten.view;

import com.schottenTotten.model.*;

public interface View {

    // Fonctions d'affichages
    void afficherFrontiere(Frontiere frontiere);
    void afficherJoueur(Joueur J);
    void afficherMessage(String message);
    void afficherBorne(Borne borne);
    void afficherWinner(Joueur winner);
    void afficherTour(Joueur active_player);
    void afficherDebut();

    // Fonctions d'input
    Carte select_card(Joueur J);
    Borne select_borne(Joueur J, Frontiere F);
    int select_revendication(Frontiere F);
    Joueur select_ia(int id_joueur, int nivmax_ia);
} 
