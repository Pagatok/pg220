package com.schottenTotten.model;


public class Borne {
    private Combinaison J1;
    private Combinaison J2;
    private boolean revendique;
    private int id_joueur; // 1 pour J1, 2 pour J2, 0 si neutre
    private int id_borne;


    // ------------------------- CONSTRUCTEURS -------------------------
    

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



    // ------------------------- GETTERS -------------------------


    // Renvoie la combinaison sur cette borne du joueur spécifié
    public Combinaison getCombinaison(int id_joueur){
        if(id_joueur == 1){
            return this.J1;
        }
        else{
            return this.J2;
        }
    }


    // Renvoie l'id de la borne
    public int getId(){
        return this.id_borne;
    }


    // Vérifie si la borne est revendiquable, cad si les 2 combinaisons sont pleines
    public boolean isRevendiquable(){
        return (J1.isFull() & J2.isFull());
    }


    // Renvoie l'Id du joueur qui possède la borne, 0 si personne
    public int getIdJoueur() {
        return id_joueur;
    }


    // Indique si la borne est revendiqué par un joueur ou non
    public boolean isRevendique() {
        return revendique;
    }
    


    // ------------------------- SETTERS -------------------------


    // Verrouille type de combianison des deux côtés de la borne
    public void setLockType(boolean lock){
        this.J1.lockType(lock);
        this.J1.lockType(lock);
    }


    // augmente de 1 le nombre de cartes pouvant être posées des deux côtés de la borne
    public void raiseMaxNbrCard(){
        this.J1.raiseMaxNbrCard();
        this.J2.raiseMaxNbrCard();
    }


    // ajoute une carte à la combinaison spécifiée
    public boolean ajouterCarte(int id_joueur, Carte carte){
        if(id_joueur == 1){
            if(J1.ajouterCarte(carte)){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if(J2.ajouterCarte(carte)){
                return true;
            }
            else{
                return false;
            }
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


    // ------------------------- FONCTIONS PRIVEES -------------------------


    private int comparerTypes(){
        Combinaison.Type typeJ1 = J1.getType();
        Combinaison.Type typeJ2 = J2.getType();

        return typeJ1.compareTo(typeJ2);
    }



    // ------------------------- TOSTRING -------------------------


    public String toString(){
        if(!isRevendique()){
            return "Borne " + this.id_borne + ": {" + this.J1.toString() + " vs " + this.J2.toString() + "}";
        }
        else{
            return "Borne " + this.id_borne + " REVENDIQUE --- JOUEUR " + this.id_joueur;
        }
    }
}
