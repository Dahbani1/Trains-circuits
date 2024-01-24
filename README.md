# TP Train (TP 11 - 15)
_Groupe composé de Mohammed Dahbani, Anas Ezzakri

## Introduction
Ce projet s'inscrit dans le cadre du cours sur les logiciels concurrents. Il consiste en la modélisation en Java du déplacement de trains sur des rails.
- Éditeur utilisé : `XXX (au choix)`

## Réponses aux questions

### Question 1.1
Bien sûr, voici une explication détaillée des classes et de leurs rôles dans le contexte du déplacement des trains sur une ligne de chemin de fer :

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

### Question 1.2
Réponse
### Question 1.3

etc...
