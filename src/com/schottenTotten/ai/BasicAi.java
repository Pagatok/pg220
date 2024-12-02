package com.schottenTotten.ai;

import com.schottenTotten.model.*;

import java.util.Random;

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

        // Génère un nombre aléatoire entre 1 et taillemainjoueur
        int id_carte = random_return(1, J.getTaillePied());

        // Construction de la carte
        Card_list pied = J.getPied();
        int valeur = pied.getValeurCarte(id_carte);
        Carte.Couleur color = pied.getCouleurCarte(id_carte);
        Carte to_return = new Carte(color, valeur);

        return to_return;
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
