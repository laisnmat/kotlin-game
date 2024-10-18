#import "./template/0.2.1/lib.typ": *
#import "@preview/codelst:2.0.1": sourcecode
#import "@preview/quick-maths:0.1.0": shorthands
#import "@preview/cetz:0.2.2"
#import "@preview/gentle-clues:1.0.0": *
#import "@preview/pinit:0.2.0": *
#import "@preview/dvdtyp:1.0.0": *
#import "@preview/cetz:0.2.2": canvas, plot

#show: template.with(
  title: "Escape The Chicken Island",
  subtitle: "Rendu de projet ACVL",
  author: "CANAGUY Vincent, LAISNE Matthew, NGONGANG Gaston, WENDELS Jérémy",
  affiliation: "Ensimag",
  date: datetime.today(),
  year: "2024-2025",
  class: "2AA",
  main-color: rgb("#22A638"), //set the main color
  alpha: 10%,
  color-words: (), //set the color
) 
\
= Cahier des charges

*Titre du projet : Escape The Chicken Island*

Ce cahier des charges décrit le projet de jeu vidéo *Escape The Chicken Island* dans sa globalité. Il décrit donc plus que simplement l'incrément réalisé pour le rendu de projet.

== Objectif

Développer un jeu d’aventure en 2D, avec interface graphique. Le joueur incarne un aventurier qui doit s’échapper d’un archipel. L’archipel est composé de quatre îles. Pour s'échapper, l'aventurier doit se déplacer d'île en île, sur laquelle il doit trouver des trésor et collecter des objets afin de construire un radeau. Jeu accessible à tout le monde et simple à prendre en main.

== Scénario

Nous sommes en 1720. Joseph, un marin courageux mais malchanceux, se retrouve naufragé après une violente tempête. Il échoue sur une île étrange nommée Chicken Island, un lieu mystérieux peuplé d’animaux et d'habitants... avec une obsession inexplicable pour les poules! Joseph doit non seulement survivre, mais aussi comprendre pourquoi cette île entière semble être régie par les poules et leurs adorateurs.

== Mécanismes de jeu

=== Collecte d’objets
Le personnage principal peut collecter divers objets, comme du bois, des plantes, de la nourriture, et ouvrir des trésors pour collecter des potions, ficelles, clous, etc. Les objets permettent la survie du personnage et la construction d’un radeau pour s’échapper de l’île. 

=== Îles
Chaque île dispose d’un trésor unique. Les îles sont reliées entre elles par une passerelle. Nous pouvons accéder aux îles selon un ordre prédéfini, nous pouvons entrer sur une île seulement si on a collecté le trésor de l’île précédente. Chaque île a un taux d’apparition d’animaux différent.  

=== Évolution du Chicken-Raft
Le bateau de Joseph est présent sur l’île de départ, mais en piteux état. C’est en ramassant divers objets en grande quantité, que nous pourrons construire un nouveau radeau. Le radeau changera visuellement à chaque évolution.

=== Survie
Le personnage dispose d’une jauge de vie. Elle diminue s’il subit des attaques d’ennemis, où si sa jauge de faim est vide. Pour augmenter la jauge de vie, il faut boire des potions présentes dans les coffres.
Le personnage dispose également d’une jauge de faim, si la jauge est vide, ses vies diminuent rapidement entraînant sa mort. Chaque île dispose initialement d’un nombre d’animaux prédéfini, et à chaque fois qu’on tue un animal, il met un certain temps avant de réapparaître. Le taux de réapparition des animaux varient en fonction des îles. Les îles de départ ont un taux d’apparition plus bas que les îles finales. Si le joueur reste trop longtemps sur les îles, il n’aura plus assez de nourriture pour se nourrir et il mourra, car la jauge de faim descend plus vite que l’apparition de nouveaux animaux.

=== Déplacement

Le joueur peut marcher. Il ne peut pas sortir des limites de la map.

=== Actions

Le jeu dispose d’un panel d’action comprenant:
- Main: permet au joueur d’ouvrir des coffres.
- Epée: permet de tuer des animaux, ennemis
- Hache: permet de couper des arbres, pour obtenir du bois
- Chicken: manger du poulet, pour restaurer la jauge de faim
- Potion: boire une potion, pour restaurer la jauge de vie 

== Personnage non jouable

=== Animaux
Il existe différents animaux, qui offrent plus ou moins de nourriture en fonction du type d’animal. Par exemple, un lion offrira davantage de nourriture qu’une poule. Certains animaux sont inoffensifs, d’autres peuvent nous attaquer. 

=== Fermiers
Élève des animaux. Si on s’approche trop de leurs animaux, ils nous attaquent. Ils sont mis KO, s’ils encaissent trop de coups.

== Interfaces

=== Menu
Nous pouvons naviguer dans un menu qui permet de changer les paramètres, d’observer la carte, de quitter le jeu.

=== Paramètres
Permet de modifier le son du jeu et les touches du clavier.

=== Inventaire
L’inventaire est situé en haut à gauche de l’interface, via une icône représentant chaque objet à obtenir, la quantité dont on en dispose, et son nombre associé afin de terminer le jeu (construire le radeau). 

=== Map 
Le joueur se déplace sur la map, la caméra se déplace en temps réel. Une mini-carte sera présente en bas à gauche de l’écran.

=== Dialog box
Une dialog box sera présente afin d’indiquer le but du jeu, d’afficher les différentes étapes du jeu, les règles.

== Spécifications techniques 
Pour développer le jeu, nous utilisons le langage de programmation Kotlin. Nous utilisons également le framework JavaFX Game Library (FXGL), afin de faciliter le développement du jeu vidéo, et de disposer d’une interface graphique. Le jeu doit être compatible sur Windows, Mac, Linux.

== Contraintes
Nous devons respecter le délai de rendu, qui est le 20 octobre 2024.

= Document d'analyse
== Cas d'utilisation
=== Récolter du bois
Ci dessous la description du cas d'utilisation, récolter du bois:

1.Pour construire le radeau j'ai besoin de bois

1.1 Si je n'ai pas la hache équipée je dois l'équipée

1.1.1 Pour cela je peux défiler la molette ou appuyer sur *Tab* ou *Shift + Tab* pour naviguer à travers les outils en bas de l'écran.

1.2 Une fois la hache équipée je m'approche d'un arbre coupable

1.3 J'appuie sur *Space* pour couper l'arbre et récolter du bois qui se rajoute à mon bois affiché en haut à gauche.

Voici le diagramme de séquence système associé:
#figure(image("./Diagrammes/Diagramme_de_séquence_bois.png", width: 80%), caption: "Diagramme de séquence récolte bois")

=== Récolter de la nourriture
Ci dessous la description du cas d'utilisation, récolter de la nourriture:

1.Pour survivre j'ai besoin de nourriture

1.1 Si je n'ai pas l'épée équipée je dois l'équipée

1.1.1 Pour cela je peux défiler la molette ou appuyer sur *Tab* ou *Shift + Tab* pour naviguer à travers les outils en bas de l'écran.

1.2 Une fois l'épée équipée je m'approche d'un animal

1.3 J'appuie sur *Space* pour tuer l'animal et récolter de la nourriture qui se rajoute à ma nourriture affiché en bas.

Voici le diagramme de séquence système associé:

#figure(image("./Diagrammes/Diagramme_séquence_nourriture.png", width: 80%), caption: "Diagramme de séquence récolte nourriture")

=== Se battre contre un fermier
Ci dessous la description du cas d'utilisation, se battre contre un fermier:

1.Pour survivre j'ai besoin de me battre

1.1 Si je n'ai pas l'épée équipée je dois l'équipée

1.1.1 Pour cela je peux défiler la molette ou appuyer sur *Tab* ou *Shift + Tab* pour naviguer à travers les outils en bas de l'écran.

1.2 Une fois l'épée équipée je m'approche d'un fermier

1.3 J'appuie sur *Space* autant de fois que nécessare pour mettre KO le fermier.

Voici le diagramme de séquence système associé:

#figure(image("./Diagrammes/Diagramme_séquence_combat.png", width: 80%), caption: "Diagramme de séquence combat fermier")

=== Se nourrir
Ci dessous la description du cas d'utilisation, se nourrir:

1.Pour survivre j'ai besoin de me nourrir

1.1 Si je n'ai pas de nourriture je dois en récolter

1.2 Lorsque j'en ai je dois la sélectionner, pour cela je peux défiler la molette ou appuyer sur *Tab* ou *Shift + Tab* pour naviguer à travers les outils en bas de l'écran.

1.3 Une fois la nourriture équipée j'appuie sur *Space* pour la consommer et restaurer ma jauge de faim

Voici le diagramme de séquence système associé:

#figure(image("./Diagrammes/Diagramme_séquence_se_nourrir.png", width: 80%), caption: "Diagramme de séquence consommation nourriture")

=== Se soigner
Ci dessous la description du cas d'utilisation, se soigner:

1.Pour survivre j'ai besoin de me soigner

1.1 Si je n'ai pas de potion je dois en récolter dans un coffre

1.2 Lorsque j'en ai je dois la sélectionner, pour cela je peux défiler la molette ou appuyer sur *Tab* ou *Shift + Tab* pour naviguer à travers les outils en bas de l'écran.

1.3 Une fois la potion équipée j'appuie sur *Space* pour la consommer et restaurer ma jauge de vie

Voici le diagramme de séquence système associé:

#figure(image("./Diagrammes/Diagramme_séquence_soin.png", width: 80%), caption: "Diagramme de séquence consommation potion")
=== Ouvrir un coffre au trésor
Ci dessous la description du cas d'utilisation, ouvrir un coffre au trésor:

1.Pour survivre et m'échapper j'ai besoin d'objet disponible dans les coffres au trésor

1.1 Si je n'ai pas la main équipée je dois l'équipée

1.1.1 Pour cela je peux défiler la molette ou appuyer sur *Tab* ou *Shift + Tab* pour naviguer à travers les outils en bas de l'écran.

1.2 Une fois la main équipée je m'approche d'un coffre au trésor

1.3 J'appuie sur *Space* pour ouvrir le coffre et récolter un objet qui se rajoute à mon inventaire en cas de matériaux obtenue ou bien à ma barre d'outils en cas de potion de soin

Voici le diagramme de séquence système associé:

#figure(image("./Diagrammes/Diagramme_séquence_trésor.png", width: 80%), caption: "Diagramme de séquence récolte trésor")

=== Finir le jeu
Ci dessous la description du cas d'utilisation, ouvrir un coffre au trésor:

1.Une fois les matériaux accumulé je souhaite pouvoir finir le jeu:

1.1 Je me déplace à l'aide des flèches vers le quai des îles.

1.2 J'interagis grâce à *Space* pour construire le radeau et m'échapper

#figure(image("./Diagrammes/Diagramme_séquence_end.png", width: 80%), caption: "Diagramme de séquence finir le jeu")

== Diagramme de classes d'analyse
A faire
= Document de conception
== Architecture logicielle
A faire
== Description de l'incrément choisit
Nous avons décidé, comme premier incrément, de fournir une partie du jeu déjà jouable. Nous avons réalisé une map où l'on peut se déplacer avec un personnage afin de survivre. Nous avons implémenté l'inventaire qui permet de posséder des matériaux et outils. Nous avons également implémenté les actions réalisable par le personnage qui sont:
- ouvrir un coffre
- couper un arbre pour obtenir du bois
- tuer un poulet pour obtenir de la nourriture
- manger
- se soigner
La map contient quatres îles sans conditions d'accès pour l'instant avec des zones où l'on peut marcher, passer devant des objets et passer derrière des objets. L'inventaire prend la forme d'une check-list où l'on peut voir si on a ou non assez de matériaux pour quitter l'île. Le bois est disponible en coupant des arbres qui repoussent avec le temps, les autres matériaux avec les coffres qui se remplissent également avec le temps.
Une fois les matériaux obtenu, on peut quitter l'île en se rendant au radeau, au bout du quai de l'île désertique et en interagissant avec.
Pour l'instant il n'y a pas d'ennemis à part la faim qui tiraille le joueur et menace de lui faire perdre de la vie si la jauge est vide.
== Conception détaillée
=== Diagramme de classe logicielle
A faire
=== Diagrammes de séquence
Pour ce qui a été conçu, on peut reprendre les diagrammes de la partie analyse qui permettaient d'implémenter les fonctionnalités générales du jeu. (Excepté celui du combat contre un fermier car non implémenté). En effet, ces fonctionnalités ayant été implémentées, les diagrammes de séquence associés sont toujours d'actualité.
#pagebreak()
Pour avoir une idée plus précise de l'implémentation, voici un exemple de diagramme de séquence plus précis pour l'ouverture d'un coffre au trésor:
#place(center + horizon,rotate(90deg)[#figure(image("./Diagrammes/Sequence_conception_trésor.png", width: 145%), caption: "Diagramme de séquence récolte trésor")])
#pagebreak()
Nous voyons bien ici que l'architecture du jeu suit la logique décrite précédemment. En effet, une action de l'utilisateur passe par un controlleur puis une entity, puis un component. (A l'exception près des events gérés différemments et des fonctionnalités de FXGL qui sont gérées par le framework).
= Manuel utilisateur
La partie compilation du projet et lancement du jeu est réalisable en suivant les instructions du A_LIRE.txt fourni avec le projet.

Concernant le jeu en lui-même, voici les commandes utilisables:
- Les flèches directionnelles pour se déplacer
- *Tab* ou *Shift + Tab* ou défilement de la molette pour naviguer à travers les outils en bas de l'écran (on peut aussi cliquer sur les outils)
- *Space* pour interagir avec l'outil équipé
- *T* pour se téléporter aléatoirement sur la map (fonction pour faciliter developpement)
- *Echap* pour ouvrir le menu géré grâce à FXGL qui propose pour l'instant de quitter le jeu ou changer des options comme: le volume (pas de son pour l'instant), les touches et la langue du menu.

*Quelques astuces*:
- Les arbres de l'île en haut à gauche sont les seules coupable et repoussent avec le temps.
- Les coffres se remplissent selon une certaines probabilité et contiennent un objet aléatoire pour construire le radeau ou une potion.
- De base, quasimment tout les matériaux sont déjà possédés par le joueur afin de tester le jeu plus facilement.
- La jauge de faim descend attention à manger pour ne pas mourir.
- Moins il y a de poulet sur les îles plus ils mettent de temps à se reproduire.
- Penser à utiliser le bon outil selon l'action désirée. (Pour le radeau il s'agit de la main)
- Il n'y a qu'une map disponible et on ne peut pas rentrer dans les grottes ou maisons.
= Bilan sur les outils de modélisation
== Draw.io
Draw.io est un outil de modélisation en ligne qui permet de réaliser des diagrammes. Il est très complet et facile d'utilisation. Il permet de partager les diagrammes avec un lien, de les exporter en image, pdf, xml, etc. De cette façon il est simple de travailler à plusieurs sur un diagramme. Il est très pratique pour réaliser des diagrammes simples et rapides. Cependant, il est moins adapté pour des diagrammes plus complexes et plus détaillés. En effet, il est difficile de gérer les relations entre les différents éléments d'un diagramme et même si on peut créer facilement des zones de textes, il est plus simples d'utiliser un outil qui prévoit déjà les zones prévues pour l'éciture d'attributs et de méthodes. C'est pourquoi nous l'avons utilisé pour les diagrammes de séquence uniquement.
== Visual Paradigm
Visual Paradigm est un outil de modélisation complet et très puissant. Il permet de réaliser des diagrammes de classes, de séquence et d'états-transitions. Il est souvent utilisé pour concevoir, modéliser et documenter des systèmes logiciels complexes. Certains membres du groupe étaient déjà familiers avec cet outil, c'est donc pour cela que, malgré sa complexité, nous l'avons utilisé. Il s'intègre bien avec des environnements de développement populaires comme IntelliJ IDEA et Visual Studio (IDE que nous avons utilisés), facilitant ainsi le passage entre la conception et l'implémentation. Visual Paradigm propose des fonctionnalités pour le travail collaboratif en temps réel, permettant aux équipes de travailler ensemble sur le même projet. Pour ce projet, la version gratuite a largement suffi.