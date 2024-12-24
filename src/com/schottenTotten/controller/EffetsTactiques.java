package com.schottenTotten.controller;

import com.schottenTotten.ai.Ai;
import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Carte_Tactique;
import com.schottenTotten.model.Combinaison;
import com.schottenTotten.model.Joueur;
import com.schottenTotten.view.View;

import java.lang.reflect.Method;

public class EffetsTactiques {
    

    // Décalaration d'un constructeur vide pour empecher l'instanciation
    private EffetsTactiques(){
        throw new UnsupportedOperationException("Cette classe ne peut pas être instanciée.");
    }


    // Cette fonction parcourt une borne qui veut être revendiqueée et lance les effets tactiques des cartes
    public static void gestionTactic(Joueur joueur, Combinaison C, View vue){

        for(int i=0; i<C.nombreDeCartes(); i++){
            Carte carte = C.getCartePrecise(i);
            if(carte instanceof Carte_Tactique){
                if(((Carte_Tactique)carte).getType() == Carte_Tactique.Type.TROUPE_ELITE){
                    vue.afficherMessage(joueur.getName() + " Veuillez sélectionner la carte en laquelle vous voulez transformer votre " + ((Carte_Tactique)carte).getNom());
                    String methodName = ((Carte_Tactique)carte).getEffet();
                    try {
                        Class<?> clazz = EffetsTactiques.class;
                        Method method = clazz.getMethod(methodName);
                        method.invoke(null, C, vue, carte);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public void appJoker(Combinaison comb, View vue, Carte_Tactique joker, Joueur J){
        // La carte se transforme en la carte désirée par le joueur
        // Le joker est initialisé

        System.out.println("Début de la sélection du joker..");

        Carte new_carte;
        if(J.getNivIA() == 0){
            new_carte = vue.create_card(joker.getValeursPoss());
        }
        else{
            Ai ia = Tour.getAi(J, vue);
            new_carte = ia.create_card(joker.getValeursPoss());
        }
        
        // Edition de la carte
        comb.removeCarte(joker);
        comb.ajouterCarte(new_carte);
        
    }
}
