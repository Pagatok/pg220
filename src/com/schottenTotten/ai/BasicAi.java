package com.schottenTotten.ai;

import com.schottenTotten.model.*;

import java.util.Random;
import java.util.List;

public class BasicAi implements Ai{

    private int level_ia = 1;

    public BasicAi() {
    }
    
    @Override
    public int getLvlIA(){
        return level_ia;
    }

    @Override
    public Carte select_card(Joueur J){

        System.out.println("Selecting Carte..");

        // Génère un nombre aléatoire entre 0 et taillemainjoueur - 1
        int id_carte = random_return(0, J.getTaillePied() - 1);

        // Construction de la carte
        Card_list pied = J.getPied();
        int valeur = pied.getValeurCarte(id_carte);
        Carte.Couleur color = pied.getCouleurCarte(id_carte);
        Carte to_return = new Carte(color, valeur);

        System.out.println("Carte selected!");

        return to_return;
    }

    @Override
    public int select_borne(Joueur J, Frontiere F){

        System.out.println("Selecting borne..");

        // Génère un nombre aléatoire entre 0 et nbr_bornes
        int id_borne = random_return(1, F.getNbrBornesTotal());
        Borne borne_selected = F.getBorne(id_borne);

        // Vérifier que le joueur n'a pas déjà posé 3 cartes sur cette borne
        if(borne_selected.nbr_cartes(J.getId()) >=3){
            System.out.println("Erreur: Vous avez déjà posé 3 cartes sur cette borne");
            return select_borne(J, F);
        }

        System.out.println("Borne selected!");

        return id_borne;
    }


    @Override
    public int select_revendication(Frontiere F){

        System.out.println("Selecting borne to revendicate..");

        // Génère un nombre aléatoire entre 0 et nbr_bornes
        List<Integer> liste_bornes = F.getBornesDispo();
        System.out.println("Nbr de bornes à revendiquer dispo: " + liste_bornes.size() + " ");
        int id_borne = random_return(0, liste_bornes.size());

        if(id_borne == 0){
            System.out.println("Pas de bornes revendiquées!");
            return -1;
        }
        else{
            System.out.println("Borne à revendiquer sélectionnée");
            return id_borne;
        }
    }



    // ------------------------- FONCTIONS PRIVEES -------------------------

    // Renvoie une valeur aléatoire dans [|x, y|]
    private static int random_return(int x, int y){
        if (x > y) {
            throw new IllegalArgumentException("La borne inférieure (x) doit être inférieure ou égale à la borne supérieure (y).");
        }
        Random random = new Random();
        return random.nextInt(y - x + 1) + x;
    }
}
