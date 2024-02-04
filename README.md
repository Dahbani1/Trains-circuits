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
     - `currentPosition: Position` - La position actuelle du train sur la ligne.
   - **Méthodes :**
     - `Train(String, Position)` - Constructeur pour créer une instance de la classe avec le nom et la position initiale spécifiés.
     - `toString(): String` - Une méthode qui retourne une représentation textuelle de l'objet Train.
     - `moveForward(): void` - Méthode pour déplacer le train vers l'avant sur la ligne.
     - `moveBackward(): void` - Méthode pour déplacer le train vers l'arrière sur la ligne.

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
- Une liste des sections occupées par des trains dans un sens.
- Une liste des sections occupées par des trains dans l'autre sens.

#### Question 3.2 : À l’aide des nouvelles variables, identifiez la nouvelle condition pour l’invariant de sûreté.

La nouvelle condition pour l'invariant de sûreté pourrait être formulée comme suit :
- Aucune section de ligne ne peut être occupée par plus d'un train à la fois.
- Dans une gare, le nombre de trains maximum est égal au nombre de quais disponibles.
- Si un train est dans une section, aucun train dans le sens opposé ne peut entrer dans une section qui serait sa destination.

#### Question 3.3 : Quelle est la classe responsable de la gestion de ces variables ?

La classe responsable de la gestion de ces nouvelles variables pourrait être la classe de gestion de la synchronisation, par exemple `SynchronisationManager`.

#### Question 3.4 : Utilisez la méthode de construction d’une solution de synchronisation présentée dans l’exercice précédent pour tenir compte de cette nouvelle condition.

En utilisant la méthode de construction d'une solution de synchronisation avec des moniteurs, vous pouvez ajouter des méthodes synchronisées à la classe `SynchronisationManager` pour gérer les nouvelles conditions d'invariant de sûreté. Par exemple :

