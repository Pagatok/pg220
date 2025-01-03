package com.schottenTotten.controller;

import com.schottenTotten.ai.Ai;
import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Carte_Tactique;
import com.schottenTotten.model.Combinaison;
import com.schottenTotten.model.Joueur;
import com.schottenTotten.model.Borne;
import com.schottenTotten.view.View;

import java.lang.reflect.Method;

public class EffetsTactiques {
    

    // Décalaration d'un constructeur vide pour empecher l'instanciation
    private EffetsTactiques(){
        throw new UnsupportedOperationException("Cette classe ne peut pas être instanciée.");
    }


    // Cette fonction parcourt une borne qui veut être revendiqueée et lance les effets tactiques des cartes de type joker
    public static void gestionTactic(Joueur joueur, Combinaison C, View vue){

        for(int i=0; i<C.nombreDeCartes(); i++){
            Carte carte = C.getCartePrecise(i);
            if(carte instanceof Carte_Tactique){
                if(((Carte_Tactique)carte).getType() == Carte_Tactique.Type.TROUPE_ELITE){
                    vue.afficherMessage(joueur.getName() + " Veuillez sélectionner la carte en laquelle vous voulez transformer votre " + ((Carte_Tactique)carte).getNom());
                    String methodName = ((Carte_Tactique)carte).getEffet();
                    try {
                        Class<?> clazz = EffetsTactiques.class;
                        Method method = clazz.getMethod(methodName, Combinaison.class, View.class, Carte_Tactique.class, Joueur.class);
                        method.invoke(null, C, vue, carte, joueur);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    // A lancer après avoir sélectionner la carte mais avnt de la jouer sur la borne qui vérifie la validité du play
    public static void gestionTacticPostSelectCard(Joueur active_player, Joueur passive_player, Carte_Tactique carte, View vue){

        // D'abord on vérifie si le joeuur a le droit de jouer une carte tactique (pas plus d'une de plus que l'adversaire)
        // if(active_player.getNbrTacticPlayed() > passive_player.getNbrTacticPlayed()){
        //     throw new IllegalStateException("Erreur: Vous ne pouvez pas jouer plus d'une carte tactique de plus que votre adversaire");
        // }
        // else{
        //     active_player.raisetacticPlayed();
        // }

        // on vérifie qu'il ne joue pas un deuxieme joker
        if(carte.getNom() == "Joker"){
            if(active_player.hasPlayedJoker()){
                throw new IllegalStateException("Erreur: Vous ne pouvez jouer qu'un seul joker dans une partie");
            }
            else{
                active_player.setPlayedJoker(true);
                vue.afficherMessage("Vous avez joué votre Joker, vous ne pourrez pas en jouer d'autres durant la partie");
            }
        }
    }


    // A lancer juste après avoir posé n'importe quelle carte tactique
    // ne fais rien pour les troupes d'élite car leur effet se déclenche à la revendication
    public static void gestionTacticPostposeCarte(Borne borne, Carte_Tactique carte, View vue, Joueur player) throws IllegalStateException{
        if(carte.getType() == Carte_Tactique.Type.MODE_COMBAT){
            String methodName = carte.getEffet();
            try {
                Class<?> clazz = EffetsTactiques.class;
                Method method = clazz.getMethod(methodName, Borne.class, View.class, Carte_Tactique.class, Joueur.class);
                method.invoke(null, borne, vue, carte, player);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    public static void appJoker(Combinaison comb, View vue, Carte_Tactique joker, Joueur J){
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


    public static void appColin(Borne borne, View vue, Joueur J, Carte_Tactique colin){
        borne.setLockType(true);
        vue.afficherMessage("Le type des Comb de la borne " + borne.getId() + " est bloqué sur SOMME");
        borne.getCombinaison(J.getId()).removeCarte(colin);
    }


    public static void appCombatBoue(Borne borne, View vue, Joueur J, Carte_Tactique combatboue){
        borne.raiseMaxNbrCard();
        int nbrmax1 = borne.getCombinaison(1).getMaxTaille();
        int nbrmax2 = borne.getCombinaison(2).getMaxTaille();
        vue.afficherMessage("Nombre de cartes à poser sur cette borne: J1: " + nbrmax1 + ", J2: " + nbrmax2);
        borne.getCombinaison(J.getId()).removeCarte(combatboue);
    }
}
