package com.schottenTotten.model;

import java.util.ArrayList;
import java.util.List;

import com.schottenTotten.model.Carte.Couleur;

public class Card_list {
    private final List<Carte> liste_cartes;

    public Card_list(){
        this.liste_cartes = new ArrayList<>();
    }

    public int ajouterCarte(Carte carte, int taille_max) {
        if (liste_cartes.size() >= taille_max) {
            System.out.println("Cette liste de cartes ne peut contenir que " + taille_max + " cartes au maximum.");
            return -1;
        }
        liste_cartes.add(carte);
        return 1;
        
    }

    public int size(){
        return liste_cartes.size();
    }

    public List<Carte> getCartes() {
        return new ArrayList<>(liste_cartes); // Retourne une copie pour protéger la liste originale
    }

    public int nombreDeCartes() {
        return liste_cartes.size();
    }

    public int getScore(){

        int somme = 0;

        for(int i = 0; i < nombreDeCartes(); i++){
            somme = somme + liste_cartes.get(i).getValeur();
        }

        return somme;
    }

        // Fonction qui renvoie la liste des valeurs des cartes
    public int getValeurCarte(int indice_carte){
        int valeur = liste_cartes.get(indice_carte).getValeur();
        return valeur;
    }

    public Couleur getCouleurCarte(int indice_carte){
        Couleur valeur = liste_cartes.get(indice_carte).getCouleur();
        return valeur;
    }

    @Override
    public String toString() {
        return "cartes=" + liste_cartes;
    }
}
