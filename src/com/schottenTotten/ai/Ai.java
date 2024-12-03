package com.schottenTotten.ai;

import com.schottenTotten.model.*;

public interface Ai {

    int getLvlIA();
    Carte select_card(Joueur J);
    Borne select_borne(Joueur J, Frontiere F);
    Borne select_revendication(Frontiere F);

    
} 
