package com.schottenTotten.view;

import com.schottenTotten.model.*;

public interface View {

    // Fonctions d'affichages
    void afficherFrontiere(Frontiere frontiere);
    void afficherJoueur(Joueur J);
    void afficherMessage(String message);
    void afficherBorne(Borne borne);
    void afficherWinner(int id_joueur);

    // Fonctions d'input
    Carte select_card(Joueur J);
    int select_borne(Joueur J, Frontiere F);
    int select_revendication(Frontiere F);
    Joueur select_ia(int id_joueur, int nivmax_ia);
} 
