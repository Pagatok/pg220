package com.schottenTotten.model;


public class Joueur{
    
    private int id_joueur;
    private Card_list pied;
    private int taille_max_main = 6;
    private int niv_ia = 0;
    private String name;
    private boolean joker_played = false;

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


    // Renvoie l'Id du joueur
    public int getId(){
        return this.id_joueur;
    }


    // Renvoie le nombre de cartes dans la main du joueur
    public int getTaillePied(){
        return pied.nombreDeCartes();
    }


    // Renvoie la main du joueur
    public Card_list getPied(){
        return this.pied;
    }


    // Renvoie le niveau IA du joueur (0 si humain)
    public int getNivIA(){
        return this.niv_ia;
    }


    // indique si le joueur est une IA ou non
    public boolean isIA(){
        if(niv_ia == 0){
            return false;
        }
        else{
            return true;
        }
    }


    // Indique si une carte donnée en paramètres est dans la main du joueur
    public boolean appartientCarte(Carte carte){
        return this.pied.carteIn(carte);
    }


    // Renvoie le nom du joueur
    public String getName(){
        return this.name;
    }


    // Indique si le joueur a déjà joué un joker durant cette partie
    public boolean hasPlayedJoker(){
        return this.joker_played;
    }


    // ------------------------- SETTERS -------------------------


    // Change l'Id du joueur
    public void setId(int id_joueur){
        this.id_joueur = id_joueur;
    }


    // Change le nombre de cartes maximal pouvant être dans la main du joueur
    public void setTailleMaxMain(int taille_max){
        this.taille_max_main = taille_max;
    }


    // Change le fait que le joueur ai déjà joué un joker ou non
    public void setPlayedJoker(boolean bool){
        this.joker_played = bool;
    }


    // Permet d'ajouter une carte à la main du joueur
    public void ajouterCarte(Carte new_carte){
        if(getTaillePied() < taille_max_main){
            pied.ajouterCarte(new_carte);
        }
        else{
            System.out.println("La main ne peut contenir que " + taille_max_main + " cartes maximum");
        }
    }


    // Permet de retirer une carte de la main du joueur
    public void retirerCarte(Carte old_carte){
        pied.removeCarte(old_carte);
    }



    // ------------------------- TOSTRING -------------------------


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
