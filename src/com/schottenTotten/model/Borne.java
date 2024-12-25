package com.schottenTotten.model;


public class Borne {
    private Combinaison J1;
    private Combinaison J2;
    private boolean revendique;
    private int id_joueur; // 1 pour J1, 2 pour J2, 0 si neutre
    private int id_borne;
    private boolean joker = false;
    private boolean mode_combat = false;
    private int nbrCartes1 = 0;
    private int nbrCartes2 = 0;

    public Borne(int id_borne){
        this.J1 = new Combinaison();
        this.J2 = new Combinaison();
        this.revendique = false;
        this.id_joueur = 0;
        this.id_borne = id_borne;
    }

    public Borne(Combinaison J1, Combinaison J2, int id_borne) {
        this.J1 = J1;
        this.J2 = J2;
        this.revendique = false; // Initialement non revendiquée
        this.id_joueur = 0;      // Neutre initialement
        this.id_borne = id_borne;
    }


    public Combinaison getCombinaison(int id_joueur){
        if(id_joueur == 1){
            return this.J1;
        }
        else{
            return this.J2;
        }
    }



    public int getId(){
        return this.id_borne;
    }

    public boolean getJoker(){
        return this.joker;
    }

    public boolean getModeCombat(){
        return this.mode_combat;
    }

    public void setJoker(boolean bool){
        this.joker = bool;
    }

    public void setModeCombat(boolean bool){
        this.mode_combat = bool;
    }

    public boolean ajouterCarte(int id_joueur, Carte carte){
        if(id_joueur == 1){
            if(J1.ajouterCarte(carte)){
                this.nbrCartes1 += 1;
                setTactic(carte);
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if(J2.ajouterCarte(carte)){
                this.nbrCartes2 += 1;
                setTactic(carte);
                return true;
            }
            else{
                return false;
            }
        }
    }

    private void setTactic(Carte carte){
        if(carte instanceof Carte_Tactique){
            switch(((Carte_Tactique)carte).getType()){
                case TROUPE_ELITE:
                    this.joker = true;
                    break;
                case MODES_COMBAT:
                    this.mode_combat = true;
                    break;
                case RUSES:
                    break;
                default:
                    System.err.println("Bizarre bizarre la carte tactique mais en fait nan");
            }
        }
    }




    public boolean isTactic(){
        return J1.isTacticIn() || J2.isTacticIn();
    }

    public int nbr_cartes(int id_joueur){
        if(id_joueur == 1){
            return this.nbrCartes1;
        }
        else{
            return this.nbrCartes2;
        }
    }


    // Méthode pour comparer les combinaisons et déterminer le gagnant
    public void determinerRevendication() {
        // Comparaison des types de combinaison
        int comparaisonType = comparerTypes();

        if (comparaisonType > 0) {
            // J1 a une meilleure combinaison
            System.out.println("J1 a une meilleure combinaison");
            this.id_joueur = 1;
        } else if (comparaisonType < 0) {
            // J2 a une meilleure combinaison
            System.out.println("J2 a une meilleure combinaison");
            this.id_joueur = 2;
        } else {
            // Types égaux, comparaison des scores
            int scoreJ1 = J1.getScore();
            int scoreJ2 = J2.getScore();

            if (scoreJ1 > scoreJ2) {
                System.out.println("J1 a un meilleur score");
                this.id_joueur = 1;
            } else if (scoreJ1 < scoreJ2) {
                System.out.println("J2 a un meilleur score");
                this.id_joueur = 2;
            } else {
                // Score égal, reste neutre
                this.id_joueur = 0;
            }
        }
        
        // Si un joueur l'a revendiquée, mettre à jour `revendique`
        this.revendique = (this.id_joueur != 0);
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


    public String toString(){
        if(!isRevendique()){
            return "Borne " + this.id_borne + ": {" + this.J1.toString() + " vs " + this.J2.toString() + "}";
        }
        else{
            return "Borne " + this.id_borne + " REVENDIQUE --- JOUEUR " + this.id_joueur;
        }
    }
}
