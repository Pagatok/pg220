package com.schottenTotten.model;

public class Carte_Tactique extends Carte{
    private String pouvoir;

    //constructeur initialisation
    public Carte_Tactique(String effet){
        super(null, 0);
        this.effet=effet;
    }

    public String getEffet(){
        return effet;
    }


    @Override 
    public String toString(){
        return "Carte Tactique{" + "effet='" + effet + '\'' + '}';
    }
}
