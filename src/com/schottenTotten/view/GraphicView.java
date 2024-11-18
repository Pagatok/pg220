package com.schottenTotten.view;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

import com.schottenTotten.model.Carte;
import com.schottenTotten.model.Combinaison;
import com.schottenTotten.model.Frontiere;
import com.schottenTotten.model.Joueur;
import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Card_list;


public class GraphicView {


    // Map statique pour associer Couleur à Color
    private static final EnumMap<Carte.Couleur, Color> COULEUR_TO_COLOR_MAP = new EnumMap<>(Carte.Couleur.class);

    static {
        // Initialisation des correspondances
        COULEUR_TO_COLOR_MAP.put(Carte.Couleur.ROUGE, Color.RED);
        COULEUR_TO_COLOR_MAP.put(Carte.Couleur.BLEU, Color.BLUE);
        COULEUR_TO_COLOR_MAP.put(Carte.Couleur.VERT, Color.GREEN);
        COULEUR_TO_COLOR_MAP.put(Carte.Couleur.JAUNE, Color.YELLOW);
        COULEUR_TO_COLOR_MAP.put(Carte.Couleur.VIOLET, new Color(128, 0, 128)); // Couleur personnalisée
        COULEUR_TO_COLOR_MAP.put(Carte.Couleur.ROSE, Color.PINK);
    }



    public static JLabel graphic_carte(Carte card){

        // Récupération de la valeur de la carte
        JLabel carte = new JLabel(String.valueOf(card.getValeur()));


        carte.setPreferredSize(new Dimension(100, 150)); // Taille des cartes
        carte.setOpaque(true);

        // Récupération de la couleur de la carte
        carte.setBackground(couleurToColor(card.getCouleur())); // Couleur de fond

        // Position du chiffre
        carte.setHorizontalAlignment(SwingConstants.CENTER); // Centrer le texte
        carte.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Bordure

        return carte;
    }


    public static JLabel reduced_card(Carte card){

        // Récupération de la valeur de la carte
        JLabel carte = new JLabel("            " + String.valueOf(card.getValeur()) + "             ");

        // Dimensions de la carte
        carte.setPreferredSize(new Dimension(100, 25)); // Taille des cartes
        carte.setMinimumSize(new Dimension(100, 25)); // Taille de la carte borne
        carte.setOpaque(true);

        // Récupération de la couleur de la carte
        carte.setBackground(couleurToColor(card.getCouleur())); // Couleur de fond

        // Position du chiffre
        carte.setHorizontalAlignment(SwingConstants.CENTER); // Centrer le texte
        carte.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Bordure

        return carte;
    }


    public static JPanel graphic_borne(Borne borne, int active_player){

        int passive_player = getPassivePlayer(active_player);

        // Création du panel correspondant à la borne
        JPanel panelBorne = new JPanel();
        panelBorne.setLayout(new BoxLayout(panelBorne, BoxLayout.Y_AXIS));
        panelBorne.setBackground(Color.DARK_GRAY);

        // Gestion de la carte Borne
        JLabel g_borne = new JLabel("    Borne n°" + String.valueOf(borne.getId()) + "    ");
        g_borne.setPreferredSize(new Dimension(100, 50)); // Taille de la carte borne
        g_borne.setMinimumSize(new Dimension(100, 50)); // Taille de la carte borne
        g_borne.setOpaque(true);
        g_borne.setBackground(Color.GRAY);
        g_borne.setHorizontalAlignment(SwingConstants.CENTER);
        g_borne.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Gestion des cartes réduites de l'adversaire
        Combinaison liste_cartes = borne.getCombinaison(passive_player);
        for(int i = 0; i<borne.nbr_cartes(passive_player); i++){
            Carte card = new Carte(liste_cartes.getCouleurCarte(i), liste_cartes.getValeurCarte(i));
            JLabel carte = reduced_card(card);
            panelBorne.add(carte);
        }

        // Ajout de la borne
        panelBorne.add(g_borne);

        // Gestion des cartes réduites de l'adversaire
        liste_cartes = borne.getCombinaison(active_player);
        for(int i = 0; i<borne.nbr_cartes(active_player); i++){
            Carte card = new Carte(liste_cartes.getCouleurCarte(i), liste_cartes.getValeurCarte(i));
            JLabel carte = reduced_card(card);
            panelBorne.add(carte);
        }


        return panelBorne;
    }


    public static JPanel graphic_frontiere(Frontiere frontiere, int active_player){

        // Créer un panneau pour représenter la frontière
        JPanel panelFrontiere = new JPanel();
        panelFrontiere.setLayout(new FlowLayout()); // Disposer les cartes en ligne
        panelFrontiere.setBackground(Color.DARK_GRAY);

        for(int i=0; i<9; i++){
            Borne borne = frontiere.getBorne(i);
            JPanel g_borne = graphic_borne(borne, active_player);
            panelFrontiere.add(g_borne);
        }

        return panelFrontiere;
    }


    public static JPanel playerHand(Joueur joueur){

        // Créer un panneau pour représenter la main du joueur
        JPanel panelMainJoueur = new JPanel();
        panelMainJoueur.setLayout(new FlowLayout()); // Disposer les cartes en ligne
        panelMainJoueur.setBackground(Color.DARK_GRAY);

        // Ajouter 6 "cartes" (ici des labels avec un fond coloré pour les représenter)
        Card_list pied = joueur.getPied();
        for (int i = 1; i <= 6; i++) {
            Carte card = new Carte(pied.getCouleurCarte(i), pied.getValeurCarte(i));
            JLabel carte = graphic_carte(card);
            panelMainJoueur.add(carte);
        }

        return panelMainJoueur;
    }


    public static void renderWindow(Frontiere frontiere, Joueur active_player){

        // Créer la fenêtre principale
        JFrame frame = new JFrame("SchottenTotten");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 900);
        frame.setLayout(new BorderLayout());

        JPanel g_front = graphic_frontiere(frontiere, active_player.getId());
        JPanel g_hand = playerHand(active_player);

        // Ajouter le panneau en bas de la fenêtre
        frame.add(g_hand, BorderLayout.SOUTH);
        frame.add(g_front, BorderLayout.NORTH);

        frame.setVisible(true);
    }



    // ------------------------- FONCTIONS PRIVEES -------------------------


    // Méthode optimisée
    private static Color couleurToColor(Carte.Couleur couleurCarte) {
        return COULEUR_TO_COLOR_MAP.getOrDefault(couleurCarte, Color.BLACK);
    }


    private static int getPassivePlayer(int active_player){
        if(active_player == 1){
            return 2;
        }
        else{
            return 1;
        }
    }
}
