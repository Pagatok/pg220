package com.schottenTotten.model;


public class Joueur{
    
    private int id_joueur;
    private Card_list pied;
    private int taille_max_main = 6;
    private int niv_ia = 0;
    private String name;

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

    public Joueur(int id_joueur, int niv_ia, String playerName){
        this.id_joueur = id_joueur;
        this.pied = new Card_list(taille_max_main);
        this.niv_ia = niv_ia;
        this.name = playerName;
    }

    public Joueur(int id_joueur, int niv_ia, int nbr_cartes, String playerName){
        this.taille_max_main = nbr_cartes;
        this.id_joueur = id_joueur;
        this.pied = new Card_list(taille_max_main);
        this.niv_ia = niv_ia;
        this.name = playerName;
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
        if(niv_ia == 0){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean appartientCarte(Carte carte){
        return this.pied.carteIn(carte);
    }

    public String getName(){
        return this.name;
    }


    // ------------------------- SETTERS -------------------------


    public void setId(int id_joueur){
        this.id_joueur = id_joueur;
    }

    public void setTailleMaxMain(int taille_max){
        this.taille_max_main = taille_max;
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
        String answer;
        if(this.name == null){
            answer = "Joueur: " + id_joueur + ", Main: " + pied.toString();
        }
        else{
            answer = this.name + ", Main: " + pied.toString();
        }
        return answer;
    }

    
}
