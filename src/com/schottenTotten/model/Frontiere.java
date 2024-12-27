package com.schottenTotten.model;

import java.util.ArrayList;
import java.util.List;

public class Frontiere {

    private final List<Borne> liste_bornes;
    private static int nbr_bornes = 9;
    private boolean gameover = false;


    // ------------------------- CONSTRUCTEUR -------------------------


    // Constructeur de Base d'une Frontière intialisée avec 9 bornes vides
    public Frontiere(){
        this.liste_bornes = new ArrayList<>();
        for(int i =1; i <= nbr_bornes; i++){
            Borne borne = new Borne((i));
            liste_bornes.add(borne);
        }
    }


    // ------------------------- GETTERS -------------------------

    
    // Renvoie la borne d'id donnée en paramètre
    public Borne getBorne(int id_borne){
        return liste_bornes.get(id_borne-1);
    }


    // Indique si la frontière considère que la partie est terminée ou non
    public boolean is_gameover(){
        return this.gameover;
    }


    // Renvoi le nombre de bornes qu'il ya sur la frontière
    public int getNbrBornesTotal(){
        return nbr_bornes;
    }


    // Vérifie si la frontiere est pleine
    // Càd si il reste ou non de la place pour des cartes à poser sur la frontière
    public boolean isFrontiereFull(){
        for(Borne borne: liste_bornes){
            if(!borne.isRevendiquable() || !borne.isRevendique()){
                return false;
            }
        }
        return true;
    }


    // Vérifie que les conditions de victoires sont remplises par l'un des 2 jouers
    // Renvoie l'id du gagnant ou 0 par défaut si pas de vainquers
    public int checkVictoire(){
        for(int i=1; i<=2; i++){
            if(getNbrBornes(i) >= 5 || getSuccessifs(i) >= 3){
                this.gameover = true;
                return i;
            }
        }
        return 0;
    }


    // Renvoi la liste des bornes dites "revendiquables"
    // C'est à dire max cartes de chaques côté et non déjà revendiquées
    public List<Integer> getRevendiquables(){
        List<Integer> answer = new ArrayList<>();
        for(Borne borne : this.liste_bornes){
            //Combinaison C1 = borne.getCombinaison(1);
            //Combinaison C2 = borne.getCombinaison(2);
            if(borne.isRevendiquable() & !borne.isRevendique()){
                answer.add(borne.getId());
            }
            //System.out.println("Borne N°" + borne.getId() + " revendiquée: " + borne.isRevendique() +
                                //"\n C1: nbr_cartes: " + C1.nombreDeCartes() + " getMaxTaille: " + C1.getMaxTaille() +
                                //"\n C1: nbr_cartes: " + C2.nombreDeCartes() + " getMaxTaille: " + C2.getMaxTaille());
        }
        return answer;
    }



    // ------------------------- SETTERS -------------------------


    // Indique à la frontiere que la partie est terminée
    public void setGameOver(){
        this.gameover = true;
    }



    // ------------------------- FONCTIONS PRIVEES -------------------------


    // Renvoi le nombre de bornes successives possédées par le joueur
    private int getSuccessifs(int id_joueur){
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


    // Renvoie le nombre de bornes possedées par le joueur id_joueur
    private int getNbrBornes(int id_joueur){
        int score = 0;
        for(Borne borne : liste_bornes){
            if(borne.getIdJoueur() == id_joueur){
                score += 1;
            }
        }
        return score;
    }


    // ------------------------- TOSTRING -------------------------


    public String toString(){
        String answer = "Frontière: \n";
        for(Borne borne : liste_bornes){
            answer += borne.toString();
            answer += "\n";
        }
        return answer;
    }
}
