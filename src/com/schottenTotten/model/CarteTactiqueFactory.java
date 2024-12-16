package com.schottenTotten.model;

import java.util.ArrayList;
import java.util.List;

public class CarteTactiqueFactory {
    public static List<Carte_Tactique> creerCartesTactiques() {
        List<Carte_Tactique> cartesTactiques = new ArrayList<>();

        // Troupes d’élite
        cartesTactiques.add(new Carte_Tactique("Joker",
                "Peut prendre n'importe quelle couleur et valeur au moment de la revendication.", Carte_Tactique.Type.TROUPE_ELITE)); //Chaque joueur ne peut avoir qu’un seul Joker de son côté de la frontière.
        cartesTactiques.add(new Carte_Tactique("Joker",
                "Peut prendre n'importe quelle couleur et valeur au moment de la revendication.", Carte_Tactique.Type.TROUPE_ELITE));
        cartesTactiques.add(new Carte_Tactique("Espion",
                "Carte de valeur 7 avec couleur choisie au moment de la revendication.", Carte_Tactique.Type.TROUPE_ELITE));
        cartesTactiques.add(new Carte_Tactique("Porte-Bouclier",
                "Carte de valeur 1, 2 ou 3 avec couleur choisie au moment de la revendication.", Carte_Tactique.Type.TROUPE_ELITE));

        // Modes de combat
        cartesTactiques.add(new Carte_Tactique("Colin-Maillard",
                "Seule la somme des cartes compte pour la revendication.", Carte_Tactique.Type.MODES_COMBAT));
        cartesTactiques.add(new Carte_Tactique("Combat de Boue",
                "Chaque joueur doit poser 4 cartes pour revendiquer la borne.", Carte_Tactique.Type.MODES_COMBAT));

/*         // Ruses
        cartesTactiques.add(new Carte_Tactique("Chasseur de Tête",
                "Piochez 3 cartes, défaussez 2.", Carte_Tactique.Type.RUSES));
        cartesTactiques.add(new Carte_Tactique("Stratège",
                "Déplacez ou défaussez une carte de votre côté.", Carte_Tactique.Type.RUSES)); //borne non revendiquée. défaussez-la face visible à côté de la pioche.
        cartesTactiques.add(new Carte_Tactique("Banshee",
                "Défaussez une carte adverse.", Carte_Tactique.Type.RUSES)); //borne non revendiquée. défaussez-la face visible à côté de la pioche.
        cartesTactiques.add(new Carte_Tactique("Traître",
                "Prenez une carte adverse et jouez-la de votre côté.", Carte_Tactique.Type.RUSES)); //borne non revendiquée à borne non revendiquée.
 */
        return cartesTactiques;
    }
}

