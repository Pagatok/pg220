package com.schottenTotten.model;

import java.util.ArrayList;
import java.util.List;

public class Frontiere {

    private final List<Borne> liste_bornes;
    private static int nbr_bornes = 9;
    private boolean gameover = false;


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

    public boolean is_gameover(){
        return this.gameover;
    }

    public int getNbrBornesTotal(){
        return nbr_bornes;
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
                this.gameover = true;
                return i;
            }
        }
        return 0;
    }

    public void setGameOver(){
        this.gameover = true;
    }

    // Renvoie la liste des id de bornes revendiquables
    public List<Integer> getBornesDispo(){
        List<Integer> intList = new ArrayList<>();

        for(int i = 0; i<nbr_bornes; i++){
            Borne borne_selected = this.getBorne(i+1);

            // Vérifier qu'il y a 3 cartes des 2 côtés de la borne et qu'elle n'est pas revendiquée
            if(borne_selected.nbr_cartes(1) == 3 && borne_selected.nbr_cartes(2) == 3 && borne_selected.isRevendique() == false){
                intList.add(i);
            }
        }

        return intList;
    }

    // Renvoi la liste des bornes dites "revendiquables"
    // C'est à dire max cartes de chaques côté et non déjà revendiquées
    public List<Integer> getRevendiquables(){
        List<Integer> answer = new ArrayList<>();
        for(Borne borne : this.liste_bornes){
            Combinaison C1 = borne.getCombinaison(1);
            Combinaison C2 = borne.getCombinaison(2);
            if(!borne.isRevendique() & (C1.nombreDeCartes() == C1.getMaxTaille()) & (C2.nombreDeCartes() == C2.getMaxTaille())){
                answer.add(borne.getId());
            }
            System.out.println("Borne N°" + borne.getId() + " revendiquée: " + borne.isRevendique() +
                                "\n C1: nbr_cartes: " + C1.nombreDeCartes() + " getMaxTaille: " + C1.getMaxTaille() +
                                "\n C1: nbr_cartes: " + C2.nombreDeCartes() + " getMaxTaille: " + C2.getMaxTaille());
        }
        return answer;
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
