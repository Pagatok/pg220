package com.schottenTotten.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.schottenTotten.model.Carte_Tactique.Type;

public class CarteTactiqueFactory {
    public static void main(String[] args) {
        creerCartesTactiques();
    }


    public static Card_list creerCartesTactiques() {

        Card_list liste_cartes = new Card_list(10);

        try (BufferedReader reader = new BufferedReader(new FileReader("src/com/schottenTotten/resources/cartes_tactiques.json"))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }

            String json = jsonBuilder.toString();
            // Conversion du JSON en objets Java
            tacticJsonToList(json, liste_cartes);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return liste_cartes;
    }


    public static void tacticJsonToList(String json, Card_list liste_cartes){

        Pattern pattern = Pattern.compile("\\{(.*?)\\}");
        Matcher matcher = pattern.matcher(json);

        while(matcher.find()){
            String data = matcher.group(1);
            Carte_Tactique carte = jsonToTacticCard(data);
            for(int i=0; i<carte.getNbrexemplaires(); i++){
                liste_cartes.ajouterCarte(carte);
            }
        }
    }

    public static Carte_Tactique jsonToTacticCard(String json) {

        String carteData = json.replace("\"", "");
        String[] lignes = carteData.split(",");

        String nom = "";
        String description = "";
        Carte_Tactique.Type type = Type.TROUPE_ELITE;
        String effet = "";
        List<Integer> valeursPossibles = null;
        int nbr_exemplaires = 0;


        for(String ligne: lignes){

            // On sépare le nom du champs et sa valeur
            String[] elements = ligne.split(":");

            // On agit selon la valeur du champ
            switch (elements[0].trim()){
                case "nom":
                    nom = elements[1].trim();
                    break;
                case "nbr_dans_pioche":
                    nbr_exemplaires = Integer.parseInt(elements[1].trim());
                    break;
                case "type_tactique":
                    String typeString = elements[1].trim();  // Prend directement le champ type
                    type = Carte_Tactique.Type.valueOf(typeString.toUpperCase());
                    break;
                case "description":
                    description = elements[1].trim();
                    break;
                case "valeurs_possibles":
                    //System.out.println(elements[1].trim());
                    break;
                case "effet":
                    effet = elements[1].trim();
                    break;
                default:
                    //System.out.println("Type de descripteur JSON non pris en charge");
            }
        }


        // On gére les tableaux
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(json);
        if(matcher.find()){
            String tableau = matcher.group(1);
            valeursPossibles = stringToList(tableau);
        }
        
    
        // On crée la carte à renvoyer avec tout les paramètres
        Carte_Tactique carte = new Carte_Tactique(nom, description, type, effet, valeursPossibles);
        if(nbr_exemplaires != 1){
            carte.setNbrExemplaires(nbr_exemplaires);
        }

        return carte;
    }


    private static List<Integer> stringToList(String input){
        // Découper la chaîne en tableau de String
        String[] parts = input.split(",\\s*");
        
        // Convertir chaque élément en entier
        List<Integer> integers = new ArrayList<>();
        for (String part : parts) {
            integers.add(Integer.parseInt(part.trim()));
        }

        return integers;
    }   
}

