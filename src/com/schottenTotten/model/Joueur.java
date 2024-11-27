package com.schottenTotten.model;


public class Joueur{
    
    private int id_joueur;
    private Card_list pied;
    private static int taille_max_main = 6;
    private boolean is_ia = false;

    public Joueur(int id_joueur){
        this.id_joueur = id_joueur;
        this.pied = new Card_list(taille_max_main);
    }

    public Joueur(int id_joueur, boolean is_ia){
        this.id_joueur = id_joueur;
        this.pied = new Card_list(taille_max_main);
        this.is_ia = is_ia;
    }

    public int getId(){
        return this.id_joueur;
    }

    public int getTaillePied(){
        return pied.nombreDeCartes();
    }

    public boolean isIA(){
        return this.is_ia;
    }

    public boolean appartientCarte(Carte carte){
        return this.pied.carteIn(carte);
    }

    // // Prend en entrée une carte à jouer qui à prioiri appartient à la main du joueur
    // // La fonction vérifie si la carte appartient bien à la main du joueur et si c'est le cas la retire
    // public boolean jouerCarte(Carte carte){
    //     int nbr = getTaillePied();
    //     retirerCarte(carte);
    //     if(getTaillePied() == nbr){
    //         return false;
    //     }
    //     else{
    //         return true;
    //     }
    // }

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

    public String toString(){
        return "Joueur: " + id_joueur + ", Main: " + pied.toString();
    }

    
}
