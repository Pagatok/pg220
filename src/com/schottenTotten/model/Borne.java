package com.schottenTotten.model;

import java.util.ArrayList;
import java.util.List;

public class Borne {
    private Combinaison J1;
    private Combinaison J2;
    private boolean revendique;
    private int id_joueur; // 1 pour J1, 2 pour J2, 0 si neutre
    private int id_borne;
    private List<Carte_Tactique> effetsTactiques; // Liste des effets tactiques sur cette borne
    private boolean colinMaillardActive = false; // Flag pour l'effet Colin-Maillard
    private boolean combatDeBoueActive = false; // Flag pour l'effet Combat de Boue


    public Borne(int id_borne){
        this.J1 = new Combinaison();
        this.J2 = new Combinaison();
        this.revendique = false;
        this.id_joueur = 0;
        this.id_borne = id_borne;
        this.effetsTactiques = new ArrayList<>();
    }

    public Borne(Combinaison J1, Combinaison J2, int id_borne) {
        this.J1 = J1;
        this.J2 = J2;
        this.revendique = false; // Initialement non revendiquée
        this.id_joueur = 0;      // Neutre initialement
        this.id_borne = id_borne;
    }

    public int getId(){
        return this.id_borne;
    }


    public Combinaison getJ1() {
        return J1;
    }

    public Combinaison getJ2() {
        return J2;
    }


    
    public boolean ajouterCarte(int id_joueur, Carte carte) {
        if (carte instanceof Carte_Tactique) {
            Carte_Tactique tactique = (Carte_Tactique) carte;
            if (tactique.getNom().equals("Colin-Maillard")) {
                this.colinMaillardActive = true;
                this.J1.setMaxCartes(4);
                this.J2.setMaxCartes(4);
                return false; // Refuser l'ajout
            }
            if(tactique.getNom().equals("Combat de Boue")){
                this.activerCombatDeBoue();
                return false;
            }
        }
    
        if (id_joueur == 1) {
            return J1.ajouterCarte(carte);
        } else {
            return J2.ajouterCarte(carte);
        }
    }
    


    public boolean ajouterCarteTactique(int id_joueur, Carte_Tactique carteTactique, Joueur joueur) {
        EffetTactique effet = new EffetTactique();
        return effet.ajouterCarteTactique(id_joueur, carteTactique, this, joueur);
    }
    


    public int nbr_cartes(int id_joueur){
        if(id_joueur == 1){
            return this.J1.nombreDeCartes();
        }
        else{
            return this.J2.nombreDeCartes();
        }
    }

    public void ajouterEffetTactique(Carte_Tactique carteTactique) {
        effetsTactiques.add(carteTactique);
        if (carteTactique.getNom().equals("Colin-Maillard")) {
            this.colinMaillardActive = true;
        }
    }

    public void activerColinMaillard() {
        this.colinMaillardActive = true;
        System.out.println("L'effet Colin-Maillard est activé sur la borne " + this.id_borne + ".");
    }
    
    public void activerCombatDeBoue() {
        this.combatDeBoueActive = true;
        this.J1.setMaxCartes(4);
        this.J2.setMaxCartes(4);
        J1.setTailleMax(4);
        J2.setTailleMax(4);
        System.out.println("L'effet Combat de Boue est activé sur la borne " + this.id_borne + ".");
    }
    
    public boolean isCombatDeBoueActive() {
        return combatDeBoueActive;
    }

    // Méthode pour comparer les combinaisons et déterminer le gagnant
    public void determinerRevendication() {
        if (this.revendique) {
            System.out.println("La borne " + this.id_borne + " a déjà été revendiquée.");
            return;
        }
    
        if(combatDeBoueActive){
            if(J1.nombreDeCartes()<4 || J2.nombreDeCartes()<4){
                System.out.println("Vous ne pouvez pas revendiquer cette borne, vous et votre adversaire devez avoir 4 cartes de poser sur celle-ci.");
                return;
            }
        }
        else{
            if (J1.nombreDeCartes() < 3 || J2.nombreDeCartes() < 3) {
                System.out.println("Vous ne pouvez pas revendiquer cette borne, vous et votre adversaire devez avoir 3 cartes de poser sur celle-ci.");
                return;
            }
        }


    
        // Comparaison sous effet Colin-Maillard
        if (colinMaillardActive) {
            configurerTroupes();
            int sommeJ1 = J1.getScore();
            int sommeJ2 = J2.getScore();
    
            System.out.println("Comparaison sous Colin-Maillard : J1=" + sommeJ1 + ", J2=" + sommeJ2);
    
            if (sommeJ1 > sommeJ2) {
                this.id_joueur = 1;
            } else if (sommeJ1 < sommeJ2) {
                this.id_joueur = 2;
            } else {
                this.id_joueur = 0; // Égalité
            }
    
            this.revendique = (this.id_joueur != 0);
            if (this.revendique) {
                System.out.println("La borne " + this.id_borne + " a été revendiquée par le joueur " + this.id_joueur + ".");
            }
            return;
        }

    
        // Comparaison normale si Colin-Maillard n'est pas actif
        configurerTroupes();
        int comparaisonType = comparerTypes();
    
        if (comparaisonType > 0) {
            this.id_joueur = 1;
        } else if (comparaisonType < 0) {
            this.id_joueur = 2;
        } else {
            int scoreJ1 = J1.getScore();
            int scoreJ2 = J2.getScore();
    
            if (scoreJ1 > scoreJ2) {
                this.id_joueur = 1;
            } else if (scoreJ1 < scoreJ2) {
                this.id_joueur = 2;
            } else {
                this.id_joueur = 0;
            }
        }
    
        this.revendique = (this.id_joueur != 0);
        if (this.revendique) {
            System.out.println("La borne " + this.id_borne + " a été revendiquée par le joueur " + this.id_joueur + ".");
        }
    }
    
    private void configurerTroupes() {
        EffetTactique effet = new EffetTactique();
    
        // Configurer les Jokers dans la combinaison de J1
        for (Carte carte : J1.getCartes()) {
            if(carte instanceof Carte_Tactique){
                Carte_Tactique carteTactique = (Carte_Tactique) carte;
                switch (carteTactique.getNom()) {
                    case "Joker":
                        effet.appeffetJoker(carte);
                        break;
                    case "Espion":
                        effet.appeffetEspion(carte);
                        break;
                    case "Porte-Bouclier":
                        effet.appeffetPorteBouclier(carte);
                        break;
                    default:
                        break;
                }
            }
        }
        // Recalculer le type après configuration
        this.J1.recalculerType();
    
        // Configurer les Jokers dans la combinaison de J2
        for (Carte carte : J2.getCartes()) {
            if(carte instanceof Carte_Tactique){
                Carte_Tactique carteTactique = (Carte_Tactique) carte;
                switch (carteTactique.getNom()) {
                    case "Joker":
                        effet.appeffetJoker(carte);
                        break;
                    case "Espion":
                        effet.appeffetEspion(carte);
                        break;
                    case "Porte-Bouclier":
                        effet.appeffetPorteBouclier(carte);
                        break;
                    default:
                        break;
                }
            }
        }
        // Recalculer le type après configuration
        this.J2.recalculerType();
    }


    private int comparerTypes(){
        Combinaison.Type typeJ1 = J1.getType();
        Combinaison.Type typeJ2 = J2.getType();

        return typeJ1.compareTo(typeJ2);
    }

    // Getters pour accéder aux informations de la borne
    public boolean isRevendique() {
        return revendique;
    }

    public int getIdJoueur() {
        return id_joueur;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Borne ").append(this.id_borne).append(": ");
    
        // Afficher les effets tactiques actifs
        if (!effetsTactiques.isEmpty()) {
            sb.append("[Effets : ");
            for (Carte_Tactique effet : effetsTactiques) {
                sb.append(effet.getNom()).append(" ");
            }
            sb.append("] ");
        }
    
        // Mentionner si "Combat de Boue" ou "Colin-Maillard" est actif
        if (combatDeBoueActive) {
            sb.append("[Effet Actif : Combat de Boue] ");
        }
        if (colinMaillardActive) {
            sb.append("[Effet Actif : Colin-Maillard] ");
        }
    
        // Afficher les combinaisons des deux joueurs
        sb.append("{Cbn J1: ").append(J1.toString()).append("} vs {Cbn J2: ").append(J2.toString()).append("}");
    
        return sb.toString();
    }
    
    
}
