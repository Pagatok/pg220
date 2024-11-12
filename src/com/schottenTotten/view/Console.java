package com.schottenTotten.view;

import java.util.Scanner;

import com.schottenTotten.model.Frontiere;
import com.schottenTotten.model.Joueur;

public class Console implements View{

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void afficherFrontiere(Frontiere frontiere){
        System.out.println(frontiere.toString());
    }

    @Override
    public void afficherJoueur(Joueur J){
        System.out.println(J.toString());
    }
}
