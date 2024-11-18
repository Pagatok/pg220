package com.schottenTotten.view;

import com.schottenTotten.model.*;

public interface View {

    // Fonctions d'affichages
    void afficherFrontiere(Frontiere frontiere);
    void afficherJoueur(Joueur J);
    void afficherMessage(String message);

    // Fonctions d'input
    Carte select_card(Joueur J);
    Borne select_borne(Joueur J, Frontiere F);
} 
