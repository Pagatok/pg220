package com.schottenTotten.controller;

import java.util.Scanner;

import com.schottenTotten.model.*;
import com.schottenTotten.view.ConsoleView;
import com.schottenTotten.view.View;
import com.schottenTotten.ai.*;


public class Jeu {   

    private static boolean variante = false;
    private static int nbr_cartes = 6;
    
    public View select_view(){
        // Choix du mode d'affichage
        // Demander à l'utilisateur quel mode choisir
        System.out.println("Choisissez le mode d'affichage :");
        System.out.println("1. Console");

        Scanner scanner = new Scanner(System.in);
        int choix = scanner.nextInt();
        scanner.nextLine(); // Consommer la ligne

        // Instancier l'objet `View` dynamiquement
        View vue;
        if (choix == 1) {
            vue = new ConsoleView();
        } else {
            System.out.println("Veuillez chossir un mode d'affichage valide\n");
            vue = new ConsoleView();
        }

        scanner.close();

        return vue;
    }


    public static boolean gestion_variante(View vue){
        variante = vue.select_variante();
        if(variante){
            nbr_cartes = 7;
        }
        return variante;
    }



    public static void main(String args[]){

        View vue = new ConsoleView();

        boolean variante = gestion_variante(vue);
        
        // Mise en place

        vue.afficherMessage("Début de la mise en place du jeu..");

        Frontiere frontiere = new Frontiere();

        // Création des joueurs
        Joueur J1 = vue.select_ia(1, 1, nbr_cartes);
        Joueur J2 = vue.select_ia(2, 1, nbr_cartes);

        // Initialisation de la pioche ou des pioches
        Pioche pioche = new Pioche();
        pioche.shuffle();
        boolean pioche_vide = false;

        // Initialisation de la pioche tactique
        Pioche pioche_tactique = new Pioche(true);
        pioche_tactique.shuffle();
        boolean pioche_tactique_vide = !variante;



        for(int i = 0; i<nbr_cartes; i++){
            J1.ajouterCarte(pioche.piocher());
            J2.ajouterCarte(pioche.piocher());
        }

        boolean gaming = true;
        int nbr_tours = 0;

        vue.afficherMessage("Fin de la mise en place du jeu!");
        vue.afficherDebut();

        while(gaming){

            // Gestion d'un tour
            nbr_tours += 1;


            // Choix du joueur actif
            Joueur joueur_actif;
            Joueur passive_player;
            if(nbr_tours % 2 == 0){
                joueur_actif = J2;
                passive_player = J1;
            }
            else{
                joueur_actif = J1;
                passive_player = J2;
            }

            // On dit au joueur que c'est à lui de jouer
            vue.afficherTour(joueur_actif, nbr_tours);

            // on gére le tour du joueur
            if(joueur_actif.isIA() == false){
                Tour.gestion_tour_real(vue, frontiere, joueur_actif, passive_player);
            }
            else{
                Tour.gestion_tour_ia(vue, frontiere, joueur_actif, passive_player);
            }

            // On vérifie si la parte est gagnée
            if(frontiere.is_gameover() == true){
                gaming = false;
                break;
            }


            //---------------GESTION PIOCHE------------------

            // Si les 2 pioches sont vides, rien besoin de faire
            if(pioche_vide & pioche_tactique_vide){
                vue.afficherMessage("Aucune carte ne peut être piocher");
            }

            // On est en variante basique et la pioche normale n'est pas vide
            if(!variante & !pioche_vide){
                 pioche_vide = tentative_pioche(pioche, vue, joueur_actif);
            }

            // Mise à jour des états de spioches
            if(pioche.nombreDeCartes() == 0){
                pioche_vide = true;
            }
            if(pioche_tactique.nombreDeCartes() == 0){
                pioche_tactique_vide = true;
            }

            System.out.println("Pioche Normale: " + pioche_vide + "\nPioche Tactique: " + pioche_tactique_vide);

            // Variante Tactique et les deux pioches ont des cartes
            // Le joueur doit choisir dans quelle pioche piocher et si ca ne marche pas il tente l'autre
            if(variante & !pioche_vide & !pioche_tactique_vide){
                if(joueur_actif.getNivIA() == 0){
                    int valeur = vue.select_pioche();
                    if(valeur == 1){
                        pioche_vide = tentative_pioche(pioche, vue, joueur_actif);
                    }
                    else{
                        pioche_tactique_vide = tentative_pioche(pioche_tactique, vue, joueur_actif);
                    }
                }
                else{
                    Ai ia = new BasicAi();
                    int valeur = ia.select_pioche();
                    if(valeur == 1){
                        pioche_vide = tentative_pioche(pioche, vue, joueur_actif);
                    }
                    else{
                        pioche_tactique_vide = tentative_pioche(pioche_tactique, vue, joueur_actif);
                    }
                }
            }

            // Variante Tactique et une des deux pioches est vide (on peut s'en être rendu compte que juste au-dessus)
            // le joueur pioche automatiquement dans la non-vide
            if(variante & (pioche_vide ^ pioche_tactique_vide)){
                if(pioche_tactique_vide){
                    vue.afficherMessage("La pioche tactique est vide");
                    pioche_vide = tentative_pioche(pioche, vue, joueur_actif);
                    if(!pioche_vide){
                        vue.afficherMessage("Carte clan piochée !");
                    }
                    else{
                        vue.afficherMessage("Pioche Normale vide aussi, on continue sans piocher");
                    }
                }
                else if(pioche_vide){
                    vue.afficherMessage("La pioche normale est vide");
                    pioche_tactique_vide = tentative_pioche(pioche_tactique, vue, joueur_actif);
                    if(!pioche_tactique_vide){
                        vue.afficherMessage("Carte tactique piochée !");
                    }
                    else{
                        vue.afficherMessage("Pioche tactique vide aussi, on continue sans piocher");
                    }
                }
            }

            if(nbr_tours % 2 == 0){
                vue.afficherFrontiere(frontiere);
            }
            if(frontiere.isFrontiereFull()){
                vue.afficherMessage("La frontière est pleine, on arrête le jeu");
                vue.afficherWinner(passive_player);
            }
        }
    }


    // Renvoi si la pioche est vide
    private static boolean tentative_pioche(Pioche piochez, View vue, Joueur J){
        try{
            Carte carte = piochez.piocher();
            J.ajouterCarte(carte);
            return false;
        }
        catch(Exception e){
            vue.afficherMessage("Il n'y a plus de cartes dans cette pioche: " + piochez.isPiocheTactique());
            return true;
        } 
    }
}
