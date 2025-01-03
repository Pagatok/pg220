package com.schottenTotten.ai;

import com.schottenTotten.model.*;
import com.schottenTotten.model.Carte.Couleur;

import java.util.Random;
import java.util.List;

public class BasicAi implements Ai{

    private int level_ia = 1;
    private int counter_select_borne = 0;

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
        Carte carte = pied.getCartePrecise(id_carte);

        //System.out.println("MAin: " + pied.toString());
        // int valeur = pied.getValeurCarte(id_carte);
        // Carte.Couleur color = pied.getCouleurCarte(id_carte);
        // Carte to_return = new Carte(color, valeur);
        //System.out.println(to_return.toString());
        //return to_return;

        System.out.println("Carte selected!");
        return carte;
    }

    @Override
    public Borne select_borne(Joueur J, Frontiere F) throws IllegalStateException{

        System.out.println("Selecting borne..");

        // Génère un nombre aléatoire entre 0 et nbr_bornes
        int id_borne = random_return(1, F.getNbrBornesTotal());
        Borne borne_selected = F.getBorne(id_borne);

        // Vérifier que le joueur n'a pas déjà posé 3 cartes sur cette borne
        if(borne_selected.getCombinaison(J.getId()).nombreDeCartes() >= borne_selected.getCombinaison(J.getId()).getMaxTaille()){
            counter_select_borne += 1;
            if(counter_select_borne <= 10){
                return select_borne(J, F);
            }
            else{
                return null;
            }

        }

        System.out.println("Borne selected!");
        counter_select_borne = 0;

        return borne_selected;
    }


    @Override
    public int select_revendication(Frontiere F){

        System.out.println("Selecting borne to revendicate..");
        List <Integer> liste_revendiquables = F.getRevendiquables();

        if(liste_revendiquables.size() == 0){
            System.out.println("Pas de bornes revendiquables!");
            return -1;
        }

        // Génère un nombre aléatoire entre 0 et nbr_bornes
        System.out.println("Taille: " + liste_revendiquables.size());
        int id_borne = random_return(0, liste_revendiquables.size() - 1);
        System.out.println("Borne à revendiquer sélectionnée");
        return id_borne;
    }


    @Override
    public Carte create_card(List<Integer> valeursPossibles){

        // Génération aléatoire de la valeur en focntion des valeurs possibles
        int id_valeur = random_return(0, valeursPossibles.size()-1);
        int valeur = valeursPossibles.get(id_valeur);

        // Génération aléatoire de la couleur
        Couleur[] colors = Couleur.values();
        int id_color = random_return(0, colors.length-1);
        Couleur couleur = colors[id_color];

        // Compilation de la carte
        Carte carte = new Carte(couleur, valeur);
        return carte;
    }

    @Override
    public int select_pioche(){
        int valeur = random_return(1, 2);
        return valeur;
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
