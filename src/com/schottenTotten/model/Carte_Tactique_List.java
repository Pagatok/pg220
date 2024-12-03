package com.schottenTotten.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carte_Tactique_List {

    private final List<Carte_Tactique> liste_cartes_tactiques;
    private int taille_max;

    // Constructeur par défaut
    public Carte_Tactique_List() {
        this.liste_cartes_tactiques = new ArrayList<>();
        this.taille_max = 1000; // Par défaut, taille maximum élevée
    }

    // Constructeur avec taille maximale
    public Carte_Tactique_List(int taille_max) {
        this.liste_cartes_tactiques = new ArrayList<>();
        this.taille_max = taille_max;
    }

    // Ajouter une carte tactique à la liste
    public boolean ajouterCarte(Carte_Tactique carte) {
        if (liste_cartes_tactiques.size() >= taille_max) {
            System.out.println("Cette liste de cartes ne peut contenir que " + taille_max + " cartes au maximum.");
            return false;
        }
        liste_cartes_tactiques.add(carte);
        return true;
    }

    // Vérifier si une carte tactique est dans la liste
    public boolean carteIn(Carte_Tactique carte) {
        return liste_cartes_tactiques.contains(carte);
    }

    // Supprimer une carte tactique
    public void removeCarte(Carte_Tactique carte) {
        liste_cartes_tactiques.remove(carte);
    }

    // Piocher une carte tactique (retirer la première carte)
    public Carte_Tactique piocher() {
        if (liste_cartes_tactiques.isEmpty()) {
            throw new IllegalStateException("La liste des cartes tactiques est vide.");
        }
        return liste_cartes_tactiques.remove(0);
    }

    // Obtenir le nombre de cartes tactiques
    public int nombreDeCartes() {
        return liste_cartes_tactiques.size();
    }

    // Obtenir une copie de la liste des cartes tactiques
    public List<Carte_Tactique> getCartes() {
        return new ArrayList<>(liste_cartes_tactiques);
    }

    // Mélanger les cartes tactiques
    public void shuffle() {
        Collections.shuffle(liste_cartes_tactiques);
    }

    @Override
    public String toString() {
        return "" + liste_cartes_tactiques;
    }
}
