package com.schottenTotten.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import com.schottenTotten.model.Carte.Couleur;

public class Card_list {

    private final List<Carte> liste_cartes;
    int taille_max;


    // ------------------------- CONSTRUCTEURS -------------------------

    public Card_list(){
        this.liste_cartes = new ArrayList<>();
        this.taille_max = 1000;
    }

    public Card_list(int taille_max){
        this.liste_cartes = new ArrayList<>();
        this.taille_max = taille_max;
    }


    // ------------------------- GETTERS -------------------------


    // Renvoie une carte décrite par sa position dans la liste
    public Carte getCartePrecise(int indice_carte){
        return this.liste_cartes.get(indice_carte);
    }


    // Renvoie le nombre de cartes que contient cette liste de cartes
    public int nombreDeCartes(){
        return liste_cartes.size();
    }


    // Renvoie une copie de la liste des cartes que contient cette liste
    // (copie pour protéger l'originale)
    public List<Carte> getCartes() {
        return new ArrayList<>(liste_cartes);
    }


    // Renvoie la valeur de la carte identifée par sa position dans la liste
    public int getValeurCarte(int indice_carte){
        int valeur = liste_cartes.get(indice_carte).getValeur();
        return valeur;
    }


    // Renvoie la couleur de la carte identifée par sa position dans la liste
    public Couleur getCouleurCarte(int indice_carte){
        Couleur valeur = liste_cartes.get(indice_carte).getCouleur();
        return valeur;
    }


    // Vérifie si une carte appartient à la liste
    public boolean carteIn(Carte carte){
        return liste_cartes.contains(carte);
    }


    // ------------------------- FONCTIONS PUBLIQUES -------------------------

    // Ajoutee une carte à la liste
    public boolean ajouterCarte(Carte carte) {

        if (liste_cartes.size() >= taille_max) {
            System.out.println("Cette liste de cartes ne peut contenir que " + taille_max + " cartes au maximum.");
            return false;
        }
        liste_cartes.add(carte);
        return true;
    }


    // Retirer une carte de la liste
    public void removeCarte(Carte carte){
        liste_cartes.remove(carte);
    }

    
    // Renvoie la première carte de la liste et la retire de celle-ci
    public Carte piocher(){
        Carte carte = liste_cartes.get(0);
        liste_cartes.remove(0);
        System.out.println("Carte piochée ! Cartes restantes: " + liste_cartes.size());
        return carte;
    }


    // Mélange la liste de cartes
    public void shuffle(){
        Collections.shuffle(liste_cartes);
    }


    @Override
    public String toString() {
        return "" + liste_cartes;
    }
}
