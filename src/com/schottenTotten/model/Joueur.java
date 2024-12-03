package com.schottenTotten.model;

public class Joueur{
    
    private int id_joueur;
    private Card_list pied;
    private static int taille_max_main = 6;
    private int niv_ia = 0;

    // ------------------------- CONSTRUCTEURS-------------------------

    public Joueur(int id_joueur){
        this.id_joueur = id_joueur;
        this.pied = new Card_list(taille_max_main);
    }

    public Joueur(int id_joueur, int niv_ia){
        this.id_joueur = id_joueur;
        this.pied = new Card_list(taille_max_main);
        this.niv_ia = niv_ia;
    }

    // ------------------------- GETTERS -------------------------

    public int getId(){
        return this.id_joueur;
    }

    public int getTaillePied(){
        return pied.nombreDeCartes();
    }

    public Card_list getPied(){
        return this.pied;
    }

    public int getNivIA(){
        return this.niv_ia;
    }

    public boolean isIA(){
        return !(niv_ia == 0);
    }

    public boolean appartientCarte(Carte carte){
        return this.pied.carteIn(carte);
    }


    // ------------------------- SETTERS -------------------------


    public void setId(int id_joueur){
        this.id_joueur = id_joueur;
    }

    public void ajouterCarte(Carte new_carte){
        if(getTaillePied() < taille_max_main){
            pied.ajouterCarte(new_carte);
        }
        else{
            System.out.println("La main ne peut contenir que " + taille_max_main + " cartes maximum");
        }
    }

    public void retirerCarte(Carte old_carte){
        pied.removeCarte(old_carte);
    }

    @Override
    public String toString(){
        return "Joueur: " + id_joueur + ", Main: " + pied.toString();
    }

    
}
