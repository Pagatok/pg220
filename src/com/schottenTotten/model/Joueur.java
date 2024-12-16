package com.schottenTotten.model;

import java.util.List;

public class Joueur{
    
    private int id_joueur;
    private Card_list pied;
    private Carte_Tactique_List pied_tactique;
    private static int taille_max_main = 6;
    private int niv_ia = 0;
    private String name;

    // ------------------------- CONSTRUCTEURS-------------------------

    public Joueur(int id_joueur, boolean mode_tactique){        
        if(mode_tactique){
            taille_max_main = 7;
        }
        this.id_joueur = id_joueur;
        this.pied = new Card_list(taille_max_main);
        this.pied_tactique = new Carte_Tactique_List();

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
    
    public int getTaillePiedTactique(){
        return pied_tactique.nombreDeCartes();
    }

    public int getTailleTotale(){
        return getTaillePied() + getTaillePiedTactique();
    }

    public boolean appartientCarte(Carte carte){
        return this.pied.carteIn(carte);
    }

    public String getName(){
        return this.name;
    }


    // ------------------------- SETTERS -------------------------

    public boolean appartientCarteTactique(Carte_Tactique carte){
        return this.pied_tactique.carteIn(carte);
    }


    public void setId(int id_joueur){
        this.id_joueur = id_joueur;
    }

    public void ajouterCarte(Carte new_carte){
        if(getTailleTotale() < taille_max_main){
            pied.ajouterCarte(new_carte);
        }
        else{
            System.out.println("La main ne peut contenir que " + taille_max_main + " cartes maximum");
        }
    }

    public void ajouterCarteTactique(Carte_Tactique new_carte){
        if(getTailleTotale() < taille_max_main){
            pied_tactique.ajouterCarte(new_carte);
        }
        else{
            System.out.println("La main ne peut contenir que " + taille_max_main + " cartes maximum");
        }
    }


    public void retirerCarte(Carte old_carte){
        if (old_carte instanceof Carte_Tactique){
            pied_tactique.removeCarte((Carte_Tactique) old_carte);
        }
        else{
            pied.removeCarte(old_carte);
        }

    }

    public void retirerCarteTactique(Carte_Tactique old_carte){
        pied_tactique.removeCarte(old_carte);
    }

    public void piocherCarte(Pioche pioche, boolean tactique){
        if(getTailleTotale() < taille_max_main){
            if(tactique){
                Carte_Tactique carte = pioche.piocher_Tactique();
                ajouterCarteTactique(carte);
            }
            else{
                Carte carte = pioche.piocher();
                ajouterCarte(carte);
            }
        }
        else{
            System.out.println("La main ne peut contenir que " + taille_max_main + " cartes maximum");
        }
    }

    public List<Carte_Tactique> getPiedTactique(){
        return pied_tactique.getCartes();
    }

    public String toString() {
        return "Joueur: " + id_joueur +
               "\nMain Clan: " + pied.toString() +
               "\nMain Tactique: " + pied_tactique.toString();
    }

    
}
