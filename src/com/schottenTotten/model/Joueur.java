package com.schottenTotten.model;


public class Joueur{
    
    private int id_joueur;
    private Card_list pied;
    private int taille_pied;
    private static int taille_max_main = 6;

    public Joueur(int id_joueur){
        this.id_joueur = id_joueur;
        this.pied = new Card_list(taille_max_main);
        this.taille_pied = 0;
    }

    public int getId(){
        return this.id_joueur;
    }

    public int getTaillePied(){
        return this.taille_pied;
    }

    public void setId(int id_joueur){
        this.id_joueur = id_joueur;
    }

    public void ajouterCarte(Carte new_carte){
        if(getTaillePied() < taille_max_main){
            pied.ajouterCarte(new_carte);
            this.taille_pied = this.taille_pied + 1;
        }
        else{
            System.out.println("La main ne peut contenir que " + taille_max_main + " cartes maximum");
        }
    }

    
}
