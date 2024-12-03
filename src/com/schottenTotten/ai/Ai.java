package com.schottenTotten.ai;

import com.schottenTotten.model.*;

public interface Ai {

    int getLvlIA();
    Carte select_card(Joueur J);
    int select_borne(Joueur J, Frontiere F);
    int select_revendication(Frontiere F);

    
} 
