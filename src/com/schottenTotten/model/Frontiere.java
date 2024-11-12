package com.schottenTotten.model;

import java.util.ArrayList;
import java.util.List;

public class Frontiere {

    private final List<Borne> liste_bornes;
    private static int nbr_bornes = 9;


    // Constructeur de Base d'une Frontière intialisée avec 9 bornes vides
    public Frontiere(){
        this.liste_bornes = new ArrayList<>();
        for(int i =1; i <= nbr_bornes; i++){
            Borne borne = new Borne((i));
            liste_bornes.add(borne);
        }
    }

    // Renvoie la borne d'id donnée en paramètre
    public Borne getBorne(int id_borne){
        return liste_bornes.get(id_borne-1);
    }

    // renvoie le nombre de bornes possedées par le joueur id_joueur
    public int getNbrBornes(int id_joueur){
        int score = 0;
        for(Borne borne : liste_bornes){
            if(borne.getIdJoueur() == id_joueur){
                score += 1;
            }
        }
        return score;
    }


    // renvoi le nombre de bornes successives possédées par le joueur
    public int getSuccessifs(int id_joueur){
        int successifs = 0;
        int score = 0;
        for(Borne borne : liste_bornes){
            if(borne.getIdJoueur() == id_joueur){
                score += 1;
            }
            else{
                if(score > successifs){
                    successifs = score;
                }
                score = 0;
            }
        }
        return successifs;
    }


    public int checkVictoire(){
        for(int i=1; i<=2; i++){
            if(getNbrBornes(i) >= 5 || getSuccessifs(i) >= 3){
                return i;
            }
        }
        return 0;
    }

    public String toString(){
        String answer = "Frontière: \n";
        for(Borne borne : liste_bornes){
            answer += borne.toString();
            answer += "\n";
        }
        return answer;
    }

}
