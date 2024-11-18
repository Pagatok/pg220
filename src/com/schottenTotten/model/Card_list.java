package com.schottenTotten.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import com.schottenTotten.model.Carte.Couleur;

public class Card_list {

    private final List<Carte> liste_cartes;
    int taille_max;

    public Card_list(){
        this.liste_cartes = new ArrayList<>();
        this.taille_max = 1000;
    }

    public Card_list(int taille_max){
        this.liste_cartes = new ArrayList<>();
        this.taille_max = taille_max;
    }

    public boolean ajouterCarte(Carte carte) {

        if (liste_cartes.size() >= taille_max) {
            System.out.println("Cette liste de cartes ne peut contenir que " + taille_max + " cartes au maximum.");
            return false;
        }
        liste_cartes.add(carte);
        return true;
    }

    // Vérifie si une carte appartient à la liste et renvoie le booléun correspondant
    public boolean carteIn(Carte carte){
        return liste_cartes.contains(carte);
    }


    public void removeCarte(Carte carte){
        liste_cartes.remove(carte);
    }

    
    public Carte piocher(){
        Carte carte = liste_cartes.get(0);
        liste_cartes.remove(0);
        return carte;
    }


    public int nombreDeCartes(){
        return liste_cartes.size();
    }

    public List<Carte> getCartes() {
        return new ArrayList<>(liste_cartes); // Retourne une copie pour protéger la liste originale
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

    public void shuffle(){
        Collections.shuffle(liste_cartes);
    }

    @Override
    public String toString() {
        return "" + liste_cartes;
    }
}
