package com.schottenTotten.view;

import com.schottenTotten.ai.BasicAi;
import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Frontiere;
import com.schottenTotten.model.Joueur;

public class AiView implements View{

    // ------------------------- FONCTIONS INPUT -------------------------


    // Fonctions d'input
    public Carte select_card(Joueur J){
        if(J.getNivIA() == 0){
            System.out.println("Le joueur n'est pas une IA, cette view ne devrait pas être appellée");
        }
        else if(J.getNivIA() == 1){
            return BasicAi.select_card(J);
        }
        else{
            System.out.println("Niveau d'AI non valide");
        }
        
    }

    public Borne select_borne(Joueur J, Frontiere F)

    public Borne select_revendication(Frontiere F)


    // ------------------------- FONCTIONS INUTILES -------------------------

    @Override
    public Joueur select_ia(int id_joueur, int nivmax_ia){
        System.err.println("Cette fonction ne fait rien et ne devrais pas etre appellée");
        return null;
    }

    @Override
    public void afficherFrontiere(Frontiere frontiere){
        System.err.println("Cette fonction ne fait rien et ne devrais pas etre appellée");
    }

    @Override
    public void afficherJoueur(Joueur J){
        System.err.println("Cette fonction ne fait rien et ne devrais pas etre appellée");
    }

    @Override
    public void afficherMessage(String message){
        System.err.println("Cette fonction ne fait rien et ne devrais pas etre appellée");
    }

    @Override
    public void afficherBorne(Borne borne){
        System.err.println("Cette fonction ne fait rien et ne devrais pas etre appellée");
    }

    @Override
    public void afficherWinner(int id_joueur){
        System.err.println("Cette fonction ne fait rien et ne devrais pas etre appellée");
    }
}
