package com.schottenTotten.view;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import com.schottenTotten.model.Carte;
import com.schottenTotten.model.CarteTactiqueFactory;
import com.schottenTotten.model.Carte.Couleur;
import com.schottenTotten.model.Frontiere;
import com.schottenTotten.model.Joueur;
import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Card_list;

public class ConsoleView implements View{

    private Scanner scanner = new Scanner(System.in);


    // ------------------------- FONCTIONS AFFICHAGE -------------------------



    @Override
    public void afficherFrontiere(Frontiere frontiere){
        System.out.println(frontiere.toString());
    }

    @Override
    public void afficherJoueur(Joueur J){
        System.out.println(J.toString());
    }

    @Override
    public void afficherMessage(String message){
        System.out.println(message);
    }

    @Override
    public void afficherBorne(Borne borne){
        System.out.println(borne.toString());
    }

    @Override
    public void afficherWinner(Joueur winner){

        String line = "CONGRATULATION " + winner.getName() + "\n YOU WIN\n";

        afficherSpecialMessage(line);
    }


    @Override
    public void afficherTour(Joueur active_player){
        String line = "A toi de jouer " + active_player.getName() + "\n";

        afficherSpecialMessage(line);
    }

    @Override
    public void afficherDebut(){
        afficherSpecialMessage("Début de la partie..");
    }




    // ------------------------- FONCTIONS SELECTION -------------------------



    @Override
    public Carte select_card(Joueur J){

        System.out.print("<nombre> <COULEUR> OR <nom>: ");
        String input = scanner.nextLine();

        //ON construit le tableau de valeurs possibles
        List<Integer> valeursPossibles = new ArrayList<>();
        for(int i=1; i<=9; i++){
            valeursPossibles.add(i);
        }

        try{
            Carte carte = parseTactique(input);
            return carte;
        } 
        catch (IllegalArgumentException f){

            try {
                Carte carte = parseCarte(input, valeursPossibles);
                System.out.println("Carte entrée : " + carte);
                if(J.appartientCarte(carte)){
                    return carte;
                }
                else{
                    System.out.println("Erreur: Veuillez sélectionner une carte de votre main");
                    return select_card(J);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
                return select_card(J);
            }
        }
    }

    @Override
    public Borne select_borne(Joueur J, Frontiere F){
        System.out.print("Sur quelle Borne poser la carte ?: ");
        int valeur = scanner.nextInt();
        scanner.nextLine();

        // Vérifier que la valeur est entre 1 et 9
        if (valeur < 1 || valeur > 9) {
            System.out.println("Veuillez rentrer une valeur entre 1 et 9");
            return select_borne(J, F);
        }

        Borne borne_selected = F.getBorne(valeur);

        // Vérifier que le joueur n'a pas déjà posé 3 cartes sur cette borne
        if(borne_selected.nbr_cartes(J.getId()) >=3){
            System.out.println("Erreur: Vous avez déjà posé 3 cartes sur cette borne");
            return select_borne(J, F);
        }

        return borne_selected;
    }


    @Override
    public Borne select_revendication(Frontiere F, List <Integer> liste_revendiquables){

        // Affichage du message
        String question = "Quelle borne voulez-vous revendiquer? (0 pour aucune)\n0";
        for(int valeur: liste_revendiquables){
            question = question + " " + valeur;
        }


        System.out.println(question);
        int valeur = scanner.nextInt();
        scanner.nextLine();

        // Vérifier que la valeur est entre 0 et 9
        if (valeur < 0 || valeur > 9) {
            System.out.println("Veuillez rentrer une valeur entre 1 et 9 pour revendiquer une borne (0 pour aucune)");
            return select_revendication(F, liste_revendiquables);
        }

        if(valeur == 0){
            return null;
        }

        if(!liste_revendiquables.contains(valeur)){
            System.out.println("Cette borne n'est pas revendiquable, Veuillez en choisir une valide");
            return select_revendication(F, liste_revendiquables);
        }

        Borne borne_selected = F.getBorne(valeur);
        System.out.println(borne_selected.toString());
        return borne_selected;
    }


    @Override
    public Joueur select_ia(int id_joueur, int nivmax_ia, int taille_max_main){

        System.out.println("Le joueur " + id_joueur + " sera t-il un humain(0) ou une ia(Difficulté 1 à " + nivmax_ia + ")?");
        int valeur = 0;
        boolean isValid = false;

        while (!isValid) {
            System.out.print("Niveau de l'IA entre 0 et 1 : ");
            
            if (scanner.hasNextInt()) {
                valeur = scanner.nextInt(); // Récupère l'entier
                scanner.nextLine(); // Consomme le reste de la ligne pour éviter les problèmes
                isValid = true; // L'entrée est valide
            } else {
                System.out.println("Erreur : Vous devez entrer un entier entre 0 et "+nivmax_ia+".");
                scanner.nextLine(); // Consomme l'entrée non valide
            }
        }

        // Vérifier que la valeur est entre 0 et niv_max_ia
        if (valeur < 0 || valeur > nivmax_ia) {
            System.out.println("Veuillez rentrer une valeur entre 1 et " + nivmax_ia);
            return select_ia(id_joueur, nivmax_ia, taille_max_main);
        }

        boolean valid = false;
        String input = "null";
        while(!valid){
            System.out.println("Comment s'appelle-t'il?");
            input = scanner.nextLine();
            if(isValidPseudo(input)){
                valid = true;
            }
        }

        Joueur j = new Joueur(id_joueur, valeur, taille_max_main, input);

        return j;
    }


    @Override
    public boolean select_variante(){
        this.afficherMessage("Choisissez la variante du jeu :\n1. Basique\n2. Tactique");
        int valeur = 0;
        boolean isValid = false;

        while (!isValid) {
            System.out.print(": ");
            
            if (scanner.hasNextInt()) {
                valeur = scanner.nextInt(); // Récupère l'entier
                scanner.nextLine(); // Consomme le reste de la ligne pour éviter les problèmes
                isValid = true; // L'entrée est valide
            } else {
                System.out.println("Erreur : Vous devez entrer un entier entre 1 et 2");
                scanner.nextLine(); // Consomme l'entrée non valide
            }
        }

        // Vérifier que la valeur est entre 0 et niv_max_ia
        if (valeur < 1 || valeur > 2) {
            System.out.println("Veuillez rentrer une valeur entre 1 et 2");
            return select_variante();
        }

        if(valeur == 1){
            return false;
        }
        else{
            return true;
        }

    }


    @Override
    public int select_pioche(){
        this.afficherMessage("Sélectionnez la pioche dans laquelle piocher\n1. Normale\n2. Tactique");
        int valeur = 0;
        boolean isValid = false;

        while (!isValid) {
            System.out.print(": ");
            
            if (scanner.hasNextInt()) {
                valeur = scanner.nextInt(); // Récupère l'entier
                scanner.nextLine(); // Consomme le reste de la ligne pour éviter les problèmes
                isValid = true; // L'entrée est valide
            } else {
                System.out.println("Erreur : Vous devez entrer un entier entre 1 et 2");
                scanner.nextLine(); // Consomme l'entrée non valide
            }
        }

        // Vérifier que la valeur est entre 0 et niv_max_ia
        if (valeur < 1 || valeur > 2) {
            System.out.println("Veuillez rentrer une valeur entre 1 et 2");
            return select_pioche();
        }

        return valeur;
    }


    @Override
    // Fonction similaire à select_card mais qui permet de renvoyer n'importe quelle carte non tactique
    // Sert majoritairement pour appliquer les effets des Jokers
    public Carte create_card(List<Integer> valeursPossibles) throws IllegalArgumentException {

        // On crée la question en fonction des valeurs possibles
        String part1 = "<nombre " + valeursPossibles.get(0) + "-" + valeursPossibles.get(valeursPossibles.size() - 1) + ">";
        String part2 = "<COULEUR>";
        String question = part1 + " " + part2;


        // On gére la demande
        System.out.print(question);
        String input = scanner.nextLine();

        try{
            Carte carte = parseCarte(input, valeursPossibles);
            System.out.println("Carte entrée : " + carte);
            return carte;
        }
        catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
            return create_card(valeursPossibles);
        }
    }



    // ------------------------- FONCTIONS PRIVEES -------------------------

    private void afficherSpecialMessage(String special_message){
        System.out.println("------------------\n");
        System.out.println(special_message);
        System.out.println("------------------\n");
    }


    // Prend en entrée une chaine rentrée par l'utilisateur et la convertir en une carte
    // gére les exceptions en cas de mauvaise utilisation
    private static Carte parseCarte(String input, List<Integer> valeursPossibles) throws IllegalArgumentException {

        // Diviser la chaîne d'entrée en deux parties : nombre et couleur
        String[] parts = input.trim().split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Format invalide, attendu : <nombre> <couleur> OR <nom>");
        }

        // Extraire et valider la valeur entière
        int valeur;
        try {
            valeur = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("La valeur doit être un nombre entier.");
        }

        // Vérifier que la valeur est entre 1 et 9
        if (!valeursPossibles.contains(valeur)) {
            throw new IllegalArgumentException("La valeur doit être dans " + valeursPossibles.toString());
        }

        // Extraire et valider la couleur
        Carte.Couleur couleur;
        try {
            couleur = Couleur.valueOf(parts[1].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Couleur invalide. Valeurs possibles : ROUGE, BLEU, VERT, JAUNE, VIOLET, ROSE.");
        }

        // Retourner une carte valide
        Carte carte = new Carte(couleur, valeur);
        return carte;
    }


    private static Carte parseTactique(String input)throws IllegalArgumentException{
        Card_list liste_tactique = CarteTactiqueFactory.creerCartesTactiques();
        List<Carte> liste = liste_tactique.getCartes();
        for(Carte carte : liste){
            if(carte.getNom().equalsIgnoreCase(input)){
                return carte;
            }
        }
        throw new IllegalArgumentException("Nom invalide chef");
    }




    private boolean isValidPseudo(String pseudo) {
        // Vérifie que la chaîne n'est pas nulle ou vide
        if (pseudo == null || pseudo.isEmpty()) {
            afficherMessage("Erreur: Veuillez rentrer un pseudo");
            return false;
        }
        
        // Vérifie que la longueur est entre 3 et 20 caractères
        if (pseudo.length() < 3 || pseudo.length() > 20) {
            afficherMessage("Erreur: Le pseudo doit contenir entre 3 et 20 caractères");
            return false;
        }
        
        // Vérifie qu'il ne contient que des lettres, des chiffres, ou les caractères _ et -
        String regex = "^[a-zA-Z0-9_-]+$";
        if (!pseudo.matches(regex)) {
            afficherMessage("erreur: Le pseudo ne doit contenir que des caractères alphanumériques");
            return false;
        }

        // Si toutes les vérifications passent, le pseudo est valide
        return true;
    }




}
