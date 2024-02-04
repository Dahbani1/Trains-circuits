# TP Train (TP 11 - 15)
_Groupe composé de Mohammed Dahbani, Anas Ezzakri

## Introduction
Ce projet s'inscrit dans le cadre du cours sur les logiciels concurrents. Il consiste en la modélisation en Java du déplacement de trains sur des rails.
- Éditeur utilisé : `XXX (au choix)`

## Partie 1: Le comportement d'un train

### Question 1.1
Une explication détaillée des classes et de leurs rôles dans le contexte du déplacement des trains sur une ligne de chemin de fer :

1. **Railway :**
   - **Attributs :**
     - `elements: Element[]` - Un tableau d'éléments qui compose la ligne de chemin de fer.
   - **Méthodes :**
     - `Railway(Element[])` - Constructeur pour créer une instance de la classe avec les éléments spécifiés.
     - `toString(): String` - Une méthode qui retourne une représentation textuelle de l'objet Railway.

   - **Rôle :** Représente la ligne de chemin de fer. Stocke les éléments (gares et sections de ligne) qui la composent.

2. **Element :**
   - **Attributs :**
     - `name: String` - Le nom de l'élément.
   - **Méthodes :**
     - `Element(String)` - Constructeur pour créer une instance de la classe avec le nom spécifié.
     - `setRailway(Railway): void` - Une méthode qui associe l'élément à une ligne de chemin de fer.
     - `toString(): String` - Une méthode qui retourne une représentation textuelle de l'objet Element.

   - **Rôle :** Classe abstraite représentant les éléments de la ligne (sections ou gares). Contient des informations communes et définit des méthodes pour être étendue par les classes Section et Station.

3. **Train :**
   - **Attributs :**
     - `name: String` - Le nom du train.
   - **Méthodes :**
     - `Train(String, Position)` - Constructeur pour créer une instance de la classe avec le nom et la position initiale spécifiés.
     - `toString(): String` - Une méthode qui retourne une représentation textuelle de l'objet Train.

   - **Rôle :** Représente un train sur la ligne de chemin de fer. Gère sa position actuelle et permet de le déplacer dans les deux sens.

4. **Position :**
   - **Attributs :**
     - `element: Element` - L'élément sur lequel le train se trouve.
     - `direction: Direction` - La direction dans laquelle le train se déplace (LR ou RL).
   - **Méthodes :**
     - `Position(Element, Direction)` - Constructeur pour créer une instance de la classe avec l'élément et la direction spécifiés.
     - `clone(): Position` - Méthode pour créer une copie de l'objet Position.
     - `toString(): String` - Une méthode qui retourne une représentation textuelle de l'objet Position.

   - **Rôle :** Classe d'association entre Train et Element. Gère la position du train sur la ligne en spécifiant l'élément et la direction.

5. **Direction :**
   - **Énumération :**
     - `LR` - Indique la direction de gauche à droite.
     - `RL` - Indique la direction de droite à gauche.

   - **Rôle :** Énumération pour représenter les deux directions possibles sur la ligne de chemin de fer.

6. **Section :**
   - **Méthodes :**
     - `Section(String)` - Constructeur pour créer une instance de la classe avec le nom spécifié.

   - **Rôle :** Représente une section de ligne sur laquelle un train peut circuler.

7. **Station :**
   - **Attributs :**
     - `size: int` - Le nombre de quais dans la gare.
   - **Méthodes :**
     - `Station(String, int)` - Constructeur pour créer une instance de la classe avec le nom et la taille spécifiés.

   - **Rôle :** Représente une gare avec plusieurs quais. La taille indique le nombre de trains que la gare peut accueillir simultanément.

### Question 1.2 : Le diagramme de classe modifié:
<img src="Diagramme de classe.drawio (2).png" alt="Diagramme de classe modifié">


### Question 1.3 : Code
Voir code (V1.zip)

## Partie 2: Plusieurs trains sur la ligne

#### Question 2.2 : Identifiez les variables qui permettent d’exprimer l’invariant de sûreté pour la ligne de trains.

Les variables qui permettent d’exprimer l’invariant de sûreté pour la ligne de trains pourraient inclure :
- Nombre de trains dans un élément (section, station)
- La taille de chaque section de ligne (taille d'une section=1)
- La taille d'une station (nombre de quais disponibles = n initialisé au moment de la constrictuon d'un objet de type classe)

#### Question 2.3 : À l’aide des variables identifiées, exprimez l’invariant de sûreté.

L'invariant de sûreté pourrait être exprimé comme suit :
- Aucune section de ligne ne peut être occupée par plus d'un train à la fois.
- Dans une gare, le nombre de trains maximum est égal au nombre de quais disponibles.

#### Question 2.4 : Quelles sont les actions « critiques » que peut effectuer un train ?

Les actions critiques sont les méthodes qui modifient l'état des variables qui représentent l'invariant de sûreté. Dans ce cas, les méthodes critiques incluraient :
- Arriver à une section de ligne (classe Element /méthode allowTrain ie laisser le train entrer si la condition est satisfaite cad la section est vide)
- Quitter une section de ligne (classe Element / méthode notifyTrain ie un train est sorti d'une section et tous est informé que cette section est vide est disponible)
- Arriver à une gare (classe Station (hérité de Element / méthode allowTrain redéfinie ie laisser le train entre s'il reste encore une place dans la gare)
- Quitter une gare (classe Element / méthode notifyTrain ie un train est sorti d'une section et tous est informé qu'une place de plus est disponible dans cette gare (le nombre de trains dans cette gare diminue par 1))

#### Question 2.5 : Dans quelles classes ces actions doivent être ajoutées ?

Les actions critiques (allowTrain, notifyTrains) doivent être ajoutées à la classe qui représente l' Element (Section, Station). Chaque élément de la ligne doit  être capable de gérer ses entrées et ses sorties et mettre à  jour son état à chaque passage d'un train.

Les actions critiques (arrivée et sortie du trains) doivent être ajoutées à la classe qui représente Train. Ces actions sont définies tous par une méthode "move" responsable du mouvement du Train Chaque élément de la ligne doit  être capable de gérer ses entrées et ses sorties et mettre à  jour son état à chaque passage d'un train, son arrivée dans une gare, sa sortie etc.

#### Question 2.6 : Selon la méthode de construction d’une solution de synchronisation donnée plus haut, quelles autres méthodes faut-il ajouter et dans quelle classe ?

Nous devons obligatoirement et principalement ajouter des méthodes dans la classe qui gère la synchronisation entre les trains. Dans notre cas on parle de la classe Element et ses classes héritées (Station, Section). Ces méthodes devraient inclure des méthodes qui vérifient si l'invariant de sûreté est toujours satisfait après une action critique.

Ces méthodes sont par la suite utilisé dans la méthode move de la classe Train, ce qui permet au train d'être capable de gérer son mouvement, son arrivée dans une gare, etc.

#### Question 2.7 : Ajoutez les méthodes identifiées dans les classes correspondantes.

Voir Code V2.zip


## Partie 3: Éviter les interblocages...

#### Question 3.1 : Identifiez les variables qui permettent d’exprimer la nouvelle condition.

Les variables qui permettent d’exprimer la nouvelle condition peuvent inclure :
-Le sens de circulation de la ligne "railway", en effet la ligne aura un seul sens de circulation, tous les trains qui sont en dehors des gares ont la meme directions. Exemple: Deux trains circulant dans la direction (left to right) de la gare A vers la gare D. La direction de ligne est donc LR. En parallèle, un train est pret à sortir de la gare D vers la gare A (right to left), son mouvement sera alors bloqué attendant l'arrivée de tous les trains à la gare d'arrivée et la libération de la ligne . A son mouvement, la dirction de la ligne devient RL et empeche alors toute sortie de train de la gare A. Lorsque la ligne est vide, la direction devient null.

#### Question 3.2 : À l’aide des nouvelles variables, identifiez la nouvelle condition pour l’invariant de sûreté.

La nouvelle condition pour l'invariant de sûreté pourrait être formulée comme suit :
- Aucune section de ligne ne peut être occupée par plus d'un train à la fois.
- Dans une gare, le nombre de trains maximum est égal au nombre de quais disponibles.
- Si au moins train est dans une section de la ligne, aucun train dans le sens opposé ne peut sortir de sa gare.

#### Question 3.3 : Quelle est la classe responsable de la gestion de ces variables ?

La classe responsable de la gestion de ces nouvelles variables est la classe de gestion de la synchronisation: Element, Section, Station.
Explication: Comme expliqué au dessus, la méthode notifyTrains principalement dans la classe Station permet un train de quitter la gare à condition que l'invariant de sureté soit vérifié (condition: pas de train circulant dans le sens opposé).

#### Question 3.4 : Utilisez la méthode de construction d’une solution de synchronisation présentée dans l’exercice précédent pour tenir compte de cette nouvelle condition.

En utilisant la méthode de construction d'une solution de synchronisation avec des moniteurs, cette condition a été dans la méthode redéfinie "notifyTrain" de la classe Station.

#### Question 3.5 : Test de la solution  =>soulèvement d'une nouvelle invariante de sureté
Voir V3.zip. 

Cependant, nous vous recommendons de voir une deuxième version du code dans le package src/train traitant cette partie d'interblocage.
En effet, après le test de cette solution, nous avons remarqué si le nombre de trains dépasse la taille d'une gare., nous pouvons arriver dans une situation d’interblocage (deadlock). Pour mieux comprendre la situation, voici un exemple: 
Soient deux gares A et D de tailles respectives 4 et 2. La ligne enetre ces deux gares contients trois sections AB, BC, CD.
Trois trains sont initialement dans la gare A. La gare D est vide. Deux trains partent de la gare A à la gare D. La gare D est désormais pleine. Le troixième trains sort de la gare A dirigé vers la gare D. A son arrivée à la section CD, il se trouve dans une situation de blocage car aucune place n'est libre dans la gare d'arrivée D. Les deux autres trains occuppant la gare D ne peuvent pas bouger aussi car ils attendent  la libération de leurs nextElement (la section CD). On se retrouve dans une situation d'inter-blocage.

La nouvelle condition pour l'invariant de sûreté pourrait être formulée comme suit :
- Aucune section de ligne ne peut être occupée par plus d'un train à la fois.
- Dans une gare, le nombre de trains maximum est égal au nombre de quais disponibles.
- Si au moins train est dans une section de la ligne, aucun train dans le sens opposé ne peut sortir de sa gare.
- S'il n y aurait pas de places libres dans sa gare de destination à son arrivée à la section qui précède cette gare, le train ne peut pas sortir de sa gare.

La classe responsable de la gestion de ces nouvelles variables est la classe de gestion de la synchronisation: Station.
Explication: Comme expliqué au dessus, la méthode notifyTrains principalement dans la classe Station permet un train de quitter la gare à condition que l'invariant de sureté soit vérifié (ici la condition devient : pas de train circulant dans le sens opposé, et il y aura une place libre dans la gare de destination à son arrivée, pour cela nous augmentons le nombre de train qui occupent la gare au moment de sortie d'un train de la gare opposée pas jusqu'à son arrivée).

##### Remarque:

##### Code: Voir code src/train

## Partie 4: Pour aller plus loin (Ajout de n gares intermédiaires)

Le but de cette partie est d’ajouter une gare intermédiaire au milieu d’une suite de sections.
Une telle gare permet à des trains de se croiser.

#### Question 4.1 : Modifiez votre code pour permettre d’ajouter des gares intermédiaires.
Voir code Gare-Intermediaire.zip

**Ajout de la classe StationIntermediaire :**
   - Créeation d'une nouvelle classe `StationInter` qui étend la classe `Station`. Cette classe hérite les memes methodes dela classe 'Station', nous pouvons dire que la station intermédiaire se comporte comme une section à plusieurs quai, cad le train ne tourne pas (ne change pas de direction) à son arrivée à ces gares intermédiaire (comme les sections), mais ces gares peuvent aussi acceuillir plusieurs trains selon le nombre de quai.
   
Le meme principe appliqué  entre deux gares dans l'exercice précédent sera appliqué entre chaque deux gares successives. Pour cela nous faisons les modifications suivantes:

1. **Modification de la classe Railway :**
   - Ajouter comme attribut la liste Stations correspondant à la liste des gares (Stations et stations intermédiaires)
   - Ajouter comme attribut la liste directions correspondant à la liste des directions de chaque deux gares successives
  
2. **Mise à jour des méthodes de Station (allowTrain (entrer), notifyTrains(sortir)) :**
   - Modifier les méthodes de gestion des mouvements des trains (`notifuTrins`, `allowTrain`, etc.) pour prendre en compte la possibilité de passer par des gares intermédiaires.
  
   - 

#### Question 4.2 : Ajout d'un nouvel invariant de sûreté pour éviter un interblocage si la gare intermédiaire a n places et qu’il y a n + 2 trains.

Dans notre version améliorée de l'exercice 3, nous avons déjà pu constater à l'avance cette situation d'inter-blocage qui est due au fait que le nombre de trains dépasse la taille d'une gare.
Le meme invariant de sureté exprimé dans l'exercice 3 doit etre vérifié entre deux gares successives, ceci dit:
- Aucune section de ligne ne peut être occupée par plus d'un train à la fois.
- Dans une gare, le nombre de trains maximum est égal au nombre de quais disponibles.
- Si au moins un train de direction "l" est dans une section de la ligne entre deux gares successives, aucun train dans l'une de ces deux gares et dont le sens est opposé à "l" ne peut sortir de sa gare.
- S'il n y aurait pas de places libres dans sa prochaine gare de destination à son arrivée à la section qui précède cette gare, le train ne peut pas sortir de sa gare actuelle.

#### Question 4.3 :
Voir code Gare-Intermediaire.zip

