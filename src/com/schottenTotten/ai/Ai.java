package com.schottenTotten.ai;

public class Ai {

    private final int nivMaxAi = 1;

    private Ai(){
        throw new UnsupportedOperationException("Cette classe ne peut pas être instanciée.");
    }

    public int getMaxLvlIA(){
        return this.nivMaxAi;
    }

    
} 
