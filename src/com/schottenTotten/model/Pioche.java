package com.schottenTotten.model;

import java.util.List;

public class Pioche {
    private Card_list liste_carte;
    private int nbr_cartes = 54; // Au début la pioche est plein et contient 54 cartes
    
    private Carte_Tactique_List liste_cartes_tactiques;
    private int nbr_cartes_tactiques = 10; // Au début la pioche est plein et contient 10 cartes


    // Intialise la pioche avec toutes les cartes dedans (pas triées)
    public Pioche(){
        
        Card_list liste_carte = new Card_list(nbr_cartes);
        for(Carte.Couleur couleur : Carte.Couleur.values()){
            for(int numero = 1; numero <= 9; numero++){
                Carte carte = new Carte(couleur, numero);
                liste_carte.ajouterCarte(carte);
            }
        }
        this.liste_carte = liste_carte;

        // Initialiser les cartes Tactiques via le factory
        liste_cartes_tactiques = new Carte_Tactique_List(nbr_cartes_tactiques);
        List<Carte_Tactique> tactiques = CarteTactiqueFactory.creerCartesTactiques();
        for (Carte_Tactique carteTactique : tactiques) {
            liste_cartes_tactiques.ajouterCarte(carteTactique);
        }

    }

    public void shuffle(){
        liste_carte.shuffle();
        liste_cartes_tactiques.shuffle();
    }

    public Carte piocher(){
        if(nbr_cartes != 0){
            Carte carte = liste_carte.piocher();
            nbr_cartes = nbr_cartes - 1;
            return carte;
        }
        else{
            System.out.println("Pioche impossible: la pioche est vide");
            return null;
        }
    }

    public Carte_Tactique piocher_Tactique(){
        if(nbr_cartes_tactiques != 0){
            Carte_Tactique carte = liste_cartes_tactiques.piocher();
            nbr_cartes_tactiques = nbr_cartes_tactiques - 1;
            return carte;
        }
        else{
            System.out.println("Pioche impossible: la pioche tactique est vide");
            return null;
        }
    }

    @Override
    public String toString() {
        return "Pioche Clan : " + liste_carte.toString() + "\n" +
               "Pioche Tactique : " + liste_cartes_tactiques.toString();
    }
}
