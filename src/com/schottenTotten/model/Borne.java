package com.schottenTotten.model;


public class Borne {
    private Combinaison J1;
    private Combinaison J2;
    private boolean revendique;
    private int id_joueur; // 1 pour J1, 2 pour J2, 0 si neutre
    private int id_borne;

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

    public int getId(){
        return this.id_borne;
    }


    public Combinaison getJ1() {
        return J1;
    }

    public Combinaison getJ2() {
        return J2;
    }

    public boolean ajouterCarte(int id_joueur, Carte carte){
        if(id_joueur == 1){
            return J1.ajouterCarte(carte);
        }
        else{
            return J2.ajouterCarte(carte);
        }
    }


    public boolean ajouterCarteTactique(int id_joueur, Carte_Tactique carteTactique) {
        EffetTactique effet = new EffetTactique();
        return effet.ajouterCarteTactique(id_joueur, carteTactique, this);
    }
    


    public int nbr_cartes(int id_joueur){
        if(id_joueur == 1){
            return this.J1.nombreDeCartes();
        }
        else{
            return this.J2.nombreDeCartes();
        }
    }


    // Méthode pour comparer les combinaisons et déterminer le gagnant
    public void determinerRevendication() {
        if (this.revendique) {
            System.out.println("La borne " + this.id_borne + " a déjà été revendiquée.");
            return;
        }
    
        if (J1.nombreDeCartes() < 3 || J2.nombreDeCartes() < 3) {
            System.out.println("Vous ne pouvez pas revendiquer cette borne, vous et votre adversaire devez avoir 3 cartes de poser sur celle-ci");
            return;
        }

        configurerJokers();
        // Comparaison des types de combinaison
        int comparaisonType = comparerTypes();

        if (comparaisonType > 0) {
            // J1 a une meilleure combinaison
            this.id_joueur = 1;
        } else if (comparaisonType < 0) {
            // J2 a une meilleure combinaison
            this.id_joueur = 2;
        } else {
            System.out.println(this.toString());
            // Types égaux, comparaison des scores
            int scoreJ1 = J1.getScore();
            int scoreJ2 = J2.getScore();

            if (scoreJ1 > scoreJ2) {
                this.id_joueur = 1;
            } else if (scoreJ1 < scoreJ2) {
                this.id_joueur = 2;
            } else {
                // Score égal, reste neutre
                this.id_joueur = 0;
            }
        }


        // Si un joueur l'a revendiquée, mettre à jour `revendique`
        this.revendique = (this.id_joueur != 0);
        if (this.revendique) {
            System.out.println("La borne " + this.id_borne + " a été revendiquée par le joueur " + this.id_joueur + ".");
        }
    }


    private void configurerJokers() {
        EffetTactique effet = new EffetTactique();

        // Configurer les Jokers dans la combinaison de J1
        for (Carte carte : J1.getCartes()) {
            if (carte.getValeur() == 0 && carte.getCouleur() == null) {
                effet.appeffetJoker(carte);
            }
        }

        // Configurer les Jokers dans la combinaison de J2
        for (Carte carte : J2.getCartes()) {
            if (carte.getValeur() == 0 && carte.getCouleur() == null) {
                effet.appeffetJoker(carte);
            }
        }
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
        return "Borne " + this.id_borne + ": {Cbn: " + this.J1.toString() + " vs " + this.J2.toString() + "}";
    }
    
    
}
