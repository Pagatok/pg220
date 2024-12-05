package com.schottenTotten.model;

public class Carte_Tactique extends Carte{
    private String nom; 
    private String description;
    private Type type; //3 catégories de type: Troupes d’élite,  Modes de combat,  Ruses

    
    //constructeur initialisation
    public Carte_Tactique(String nom, String description, Type type){
        super(null, 0);
        this.nom=nom;
        this.description=description;
        this.type=type;
    }

    public String getNom(){
        return nom;
    }

    public String getDescription(){
        return description;
    }

    public Type getType(){
        return type;
    }


    @Override 
    public String toString() {
        return  "nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type;
    }

    // Enum pour catégoriser les effets des cartes tactiques
    public enum Type {
        TROUPES_ELITE, //Joker(2), Espion, Porte-Bouclier: Se joue comme une carte Clan
        MODES_COMBAT,   //Colin-Maillard, Combat de Boue: S'applique directement sur une Borne
        RUSES           //Chasseur de Tête, Stratège, Banshee, Traître: Utilisation temporaire avec effet unique
    }
}
