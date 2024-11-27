package com.schottenTotten.ai;

import java.util.Random;

public class BasicAi implements Ai{

    private int level_ia = 1;
    
    @Override
    public int getLvlIA(){
        return this.level_ia;
    }

    // ------------------------- FONCTIONS PRIVEES -------------------------

    // Renvoie une valeur aléatoire dans [|x, y|]
    private int random_return(int x, int y){
        if (x > y) {
            throw new IllegalArgumentException("La borne inférieure (x) doit être inférieure ou égale à la borne supérieure (y).");
        }
        Random random = new Random();
        return random.nextInt(y - x + 1) + x;
    }
}
