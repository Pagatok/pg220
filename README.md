# Schotten Totten

Ce projet est une implémentation du jeu Schotten Totten en Java, structurée en plusieurs packages pour organiser le code de manière claire et modulaire.
[Régle du Jeu:](PG220_Projet_1.pdf)

## Structure du projet

L'arborescence du projet est organisée comme suit :

src/<br>
├── classes/<br>
├── diagrams/<br>
└── com/<br>
.   └── schottenTotten/ <br>
.       ├── model/ <br>
.       ├── controller/ <br>
.       ├── view/ <br>
.       └── ai/<br>


## Détails des packages

Pour une vue général des packaes:
[Schéma:](src/diagrams/all.png)

Pour une description avancée des classes: 


- **com.schottenTotten.model**  
  Contient les classes de base du modèle de jeu.
  [Schéma:](src/diagrams/model.png)
  Liste des classes :
  - `Carte` : représente une carte de jeu.
  - `Joueur` : représente un joueur humain ou IA.
  - `Borne` : représente une borne à capturer dans le jeu.
  - `Frontière`: représente la liste des 9 bornes à capturer dans le jeu
  - `Combinaison`: représente une combinsaison de 3 cartes appartenant à un joueur et posée sur une borne
  - `Card_list`: représente une superclasse de liste de cartes
  - `Pioche`: représente la pioche de 54 cartes

- **com.schottenTotten.controller**  
  Gère la logique de jeu et les règles.
  Liste des classes :
  - `Jeu` : classe principale qui initialise et gère la partie.

- **com.schottenTotten.view**
  Gère l'affichage et les interactions utilisateur.
  [Schéma:](src/diagrams/view.png)
  Liste de classes :
  - `View` : Interface rassemblant les commandes d'affichages et de sélection. Les autres classes implémentent View
  - `ConsoleView` : interface utilisateur en mode console pour afficher le jeu et les instructions.


## Roadmap

Voici les fonctionnalités à implémenter dans le futur pour améliorer le jeu
- [ ] Ajouter une interface utilisateur graphique.
- [ ] Implémenter une Intelligence artificielle pour jouer.
- [ ] Corriger l'affichage terminal pour le rendre plus joli et empecher le joueur 2 de voir la main du joueur 1.
- [ ] Ajouter les cartes tactiques.
