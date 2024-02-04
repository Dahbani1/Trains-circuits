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
- Liste de trains en déplacement
- État de chaque section de ligne
- État de chaque quai dans chaque gare

#### Question 2.3 : À l’aide des variables identifiées, exprimez l’invariant de sûreté.

L'invariant de sûreté pourrait être exprimé comme suit :
- Aucune section de ligne ne peut être occupée par plus d'un train à la fois.
- Dans une gare, le nombre de trains maximum est égal au nombre de quais disponibles.

#### Question 2.4 : Quelles sont les actions « critiques » que peut effectuer un train ?

Les actions critiques sont les méthodes qui modifient l'état des variables qui représentent l'invariant de sûreté. Dans ce cas, les méthodes critiques incluraient :
- Arriver à une section de ligne
- Quitter une section de ligne
- Arriver à une gare
- Quitter une gare

#### Question 2.5 : Dans quelles classes ces actions doivent être ajoutées ?

Les actions critiques doivent être ajoutées à la classe qui représente le train. Chaque train doit être capable de gérer son mouvement, son arrivée dans une gare, etc.

#### Question 2.6 : Selon la méthode de construction d’une solution de synchronisation donnée plus haut, quelles autres méthodes faut-il ajouter et dans quelle classe ?

Selon la méthode donnée, vous devrez ajouter des méthodes dans la classe qui gère la synchronisation entre les trains. Ces méthodes devraient inclure des méthodes qui vérifient si l'invariant de sûreté est toujours satisfait après une action critique.

#### Question 2.7 : Ajoutez les méthodes identifiées dans les classes correspondantes.

En fonction de la structure actuelle de votre code, ajoutez les méthodes synchronisées dans la classe Train et dans une classe de gestion de la synchronisation. N'oubliez pas d'adapter cela en fonction de la structure réelle de votre code.
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

