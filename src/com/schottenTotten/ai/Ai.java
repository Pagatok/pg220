package com.schottenTotten.ai;

import java.util.List;

import com.schottenTotten.model.*;

public interface Ai {

    int getLvlIA();
    Carte select_card(Joueur J);
    Borne select_borne(Joueur J, Frontiere F);
    int select_revendication(Frontiere F);
    Carte create_card(List<Integer> valeursPossibles);
    
} 
