package com.schottenTotten.view;

import java.util.Scanner;

import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Carte.Couleur;
import com.schottenTotten.model.Frontiere;
import com.schottenTotten.model.Joueur;
import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Carte_Tactique;

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


    private Carte_Tactique selectCarteTactiqueParNom(Joueur joueur, String nom) {
        // Parcourir les cartes tactiques pour trouver celle qui correspond au nom
        for (Carte_Tactique carteTactique : joueur.getPiedTactique()) {
            if (carteTactique.getNom().equalsIgnoreCase(nom)) {
                return carteTactique;
            }
        }
        return null; // Retourne null si aucune carte correspond
    }

    @Override
    public Carte select_card(Joueur J){

        System.out.print("<nombre> <COULEUR> ou nom de la carte tactique: ");
        String input = scanner.nextLine();

        Carte carte = null;

        try {
            carte = selectCarteTactiqueParNom(J, input);

            if (carte == null){
                carte = parseCarte(input);
                System.out.println("Carte entrée : " + carte);
                if(J.appartientCarte(carte)){
                    return carte;
                }
                else{
                    System.out.println("Erreur: Veuillez sélectionner une carte de votre main");
                    return select_card(J);
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
            return select_card(J);
        }
        
        System.out.println("Carte sélectionnée : " + carte);
        return carte;
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

        // Vérifier si l'effet Combat de Boue est actif
        if (borne_selected.isCombatDeBoueActive() && borne_selected.nbr_cartes(J.getId()) >= 4) {
            System.out.println("Erreur: Vous avez déjà posé 4 cartes sur cette borne en raison de l'effet Combat de Boue");
            return select_borne(J, F);
        }

        // Vérifier que le joueur n'a pas déjà posé 3 cartes sur une borne normale
        if (!borne_selected.isCombatDeBoueActive() && borne_selected.nbr_cartes(J.getId()) >= 3) {
            System.out.println("Erreur: Vous avez déjà posé 3 cartes sur cette borne");
            return select_borne(J, F);
        }

        return borne_selected;
    }


    @Override
    public int select_revendication(Frontiere F){

        System.out.println("Quelle borne voulez-vous revendiquer? (0 pour aucune): ");
        int valeur = scanner.nextInt();
        scanner.nextLine();

        // Vérifier que la valeur est entre 0 et 9
        if (valeur < 0 || valeur > 9) {
            System.out.println("Veuillez rentrer une valeur entre 1 et 9 pour revendiquer une borne (0 pour aucune)");
            return select_revendication(F);
        }

        if(valeur == 0){
            return -1;
        }

        Borne borne_selected = F.getBorne(valeur);

<<<<<<< HEAD
        if(borne_selected.isRevendique() == true){
            this.afficherMessage("Cette borne est déjà revendiqué, veuiez sélectionner une borne valide");
            return select_revendication(F);
        }

        // Vérifier qu'il y a 3 cartes des 2 côtés de la borne
        if(borne_selected.nbr_cartes(1) == 3 && borne_selected.nbr_cartes(2) == 3){
            return valeur;
        }
        else{
            System.out.println("Vous ne pouvez pas revendiquer cette borne, vous et votre adversaire devez avoir 3 cartes de poser sur celle-ci");
=======
        // Vérifier le nombre de cartes nécessaires pour revendiquer
        int cartesNecessaires = borne_selected.isCombatDeBoueActive() ? 4 : 3;

        if (borne_selected.nbr_cartes(1) == cartesNecessaires && borne_selected.nbr_cartes(2) == cartesNecessaires) {
            return borne_selected;
        } else {
            System.out.println("Vous ne pouvez pas revendiquer cette borne, chaque joueur doit avoir posé " 
                            + cartesNecessaires + " cartes.");
>>>>>>> origin/ines_tactique
            return select_revendication(F);
        }
    }


    @Override
    public Joueur select_ia(int id_joueur, int nivmax_ia){

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
            return select_ia(id_joueur, nivmax_ia);
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

        Joueur j = new Joueur(id_joueur, valeur, input);

        return j;
    }



    // ------------------------- FONCTIONS PRIVEES -------------------------

<<<<<<< HEAD
    private void afficherSpecialMessage(String special_message){
        System.out.println("------------------\n");
        System.out.println(special_message);
        System.out.println("------------------\n");
    }


=======
>>>>>>> origin/ines_tactique
    // Prend en entrée une chaine rentrée par l'utilisateur et la convertir en une carte
    // gére les exceptions en cas de mauvaise utilisation
    private static Carte parseCarte(String input) throws IllegalArgumentException {

        // Diviser la chaîne d'entrée en deux parties : nombre et couleur
        String[] parts = input.trim().split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Format invalide, attendu : <nombre> <couleur> ou nom de carte tactique.");
        }

        // Extraire et valider la valeur entière
        int valeur;
        try {
            valeur = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("La valeur doit être un nombre entier, si nom mettre les '-' et accents.");
        }

        // Vérifier que la valeur est entre 1 et 9
        if (valeur < 1 || valeur > 9) {
            throw new IllegalArgumentException("La valeur doit être entre 1 et 9.");
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
