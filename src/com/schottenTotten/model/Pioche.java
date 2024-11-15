package com.schottenTotten.model;

public class Pioche {
    private Card_list liste_carte;
    private int nbr_cartes = 54; // Au début la pioche est plein et contient 54 cartes


    // Intialise la pioche avec toutes les cartes dedans (pas triées)
    public Pioche(){
        
        Card_list liste_carte = new Card_list(nbr_cartes);
        for(Carte.Couleur couleur : Carte.Couleur.values()){
            for(int numero = 1; numero <= 9; numero++){
                Carte carte = new Carte(couleur, numero);
                liste_carte.ajouterCarte(carte);
            }
        }
    }

    public void shuffle(){
        System.out.println("Début du mélange de la pioche..\n");
        liste_carte.shuffle();
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
}
