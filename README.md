# MilleBornes - Grandeur Nature 🏎️

## Concept 🖊️
### 📌 Explication du projet
Basé sur le jeu original [Mille Bornes](https://www.jeuxdujardin.fr/produit/milles-bornes-pegboardable), le mode "grandeur nature", est un jeu qui est joué le plus souvent en ACM (colonie de vacances...).
Les joueurs (enfants) sont divisés en plusieurs équipes de couleurs, et choisissent un chef d'équipe. Le but des équipés étant d'atteindre les 1000 bornes. Pour ce faire, chaque joueur devra provoquer un joueur d'une équipe adverse en duel. Le joueur qui remporte le duel, gagne un point, qu'il ramène au banquier qui devra l'ajouter au butin de l'équipe.
Les points vont permettre d'acheter soit des bornes, soit des bonus, soit des malus pour ralentir les autres équipes. En effet, quand il le veut, les chefs d'équipes peuvent venir voir le banquier pour échanger les points contre des cartes (bornes, bonus ou malus).

### 💭 Problématique

 - Le banquier étant seul a la banque, doit effectuer **plusieurs tâches** en même temps (*ranger les points ramenés, les compter, les échanger avec des cartes...*), qui deviennent rapidement fastidieuses. De plus, plus il y a de joueurs, plus il y a d'équipes, plus le travail du banquier est multiplié.
 - L'affichage est souvent fait avec des feuilles... créé par les animateurs, se qui est des fois **pas très clair**.
 - Les animateurs postés sur les duels ont des fois besoin de **connaitre l'avancement des équipes**, et comme, ils ne peuvent pas quitter leurs postes, ils ont l'**incapacité de se rendre a la banque**.
 - Les animateurs postés sur les duels n'ont **pas la possibilité de retirer des points** a une équipe pour une raison quelconque (*mauvais comportement, anti-jeu...*)

### ✔️ Solution
L'idée du projet est donc de faciliter la **mise en place du jeu** ainsi que la "vie" du *banquier* et des *animateurs*. En mettant en place:

 - Une **interface claire** (compréhensible pour des enfants) et ludique.
 - Une **gestion rapide** des points des équipes, des malus et des bonus.
 - Une possibilité pour les animateurs qui sont sur les duels d'**ajouter ou retirer des points** aux équipes ainsi que de connaitre l'**avancement des équipes** dans leur objectif.

## Projet ⌨️
### 🌐 Web 
> **Langage(s) et Outils utilisé(s):** PHP, CSS, JavaScript & MySQL

L'interface a été faites en PHP afin d'avoir cette dernière en *multi plateforme*. En effet, on peut afficher l'interface depuis un ordinateur (*ex: pour la banque*) et sur des smartphones (*ex: pour les animateurs*).

 - Interface des équipes
	 - [x]  Avancement des bornes
	 - [x] Nombre de points
	 - [x] Bonus/Malus
 - Gestion des points
	 - [ ] Ajout des points
	 - [ ] Retraits de points
- Création des équipes (*petit bonus*)
	- [x] Pouvoir créer des équipes

### 🕹️ Console
> **Langage(s) et Outils utilisé(s):** Java & MySQL

Le projet contient aussi une console afin gérer la totalité des données de jeu. Les actions seront éffectuées via des commandes disponibles grâce a la commande `help`.

 - Commandes
	 - [x] Création des équipes
	 - [x]  Ajout/Retrait de points
	 - [ ] Ajout/Retrait des malus et bonus

#### > Utilisation de la console
Rédaction en cours⏳
