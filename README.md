# Schotten Totten

Ce projet est une implémentation du jeu Schotten Totten en Java, structurée en plusieurs packages pour organiser le code de manière claire et modulaire.

## Structure du projet

L'arborescence du projet est organisée comme suit :

src/
├── classes/
├── diagrams/
└── com/
    └── schottenTotten/ 
        ├── model/ 
        ├── controller/ 
        ├── view/ 
        └── ai/


### Détails des packages

- **com.schottenTotten.model**  
  Contient les classes de base du modèle de jeu.  
  Exemple de classes :
  - `Carte` : représente une carte de jeu.
  - `Joueur` : représente un joueur humain ou IA.
  - `Borne` : représente une borne à capturer dans le jeu.

- **com.schottenTotten.controller**  
  Gère la logique de jeu et les règles.
  Exemple de classes :
  - `Jeu` : classe principale qui initialise et gère la partie.
  - `TourManager` : gère les tours des joueurs.

- **com.schottenTotten.view**  
  Gère l'affichage et les interactions utilisateur.
  Exemple de classes :
  - `ConsoleView` : interface utilisateur en mode console pour afficher le jeu et les instructions.

- **com.schottenTotten.ai**  
  Implémente les stratégies d'intelligence artificielle pour les joueurs non humains.
  Exemple de classes :
  - `AIStrategy` : classe de base pour les différentes stratégies de l'IA.
  - `AggressiveStrategy`, `DefensiveStrategy` : classes pour des comportements spécifiqu