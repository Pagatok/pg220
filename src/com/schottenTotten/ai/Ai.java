package com.schottenTotten.ai;

import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Joueur;

public interface Ai {

    int getLvlIA();
    Carte select_card(Joueur J);

    
} 
