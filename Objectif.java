/**
 * Projet de première année à l'IUT de Paris - Rive de Seine
 * * Jeu de Crazy Circus par Dominique Ehrhard
 * @author Clothilde PROUX, Suyi LYN
 * @file Objectif.java
 * Jeu de cartes
 */
package test;

import java.util.ArrayList;
import java.util.Objects;


public class Objectif {
    private final int nbCartes = 24;
    private final int nbAnimaux = 3;
    private int nbCartesRestantes;
    private final ArrayList<Carte> CartesObj;
    private final Animal[] TabAnimaux;

    /**
     * Constructeur vide qui crée la liste des cartes de la partie
     */
    public Objectif() {
        this.CartesObj = new ArrayList<>();
        this.TabAnimaux = new Animal[]{Animal.ELEPHANT, Animal.LION, Animal.OURS};
        this.nbCartesRestantes = this.nbCartes;
    }

    /**
     * Remplit la liste des cartes avec toutes les combinaisons possibles
     */
    public void RemplirObj() {
        int indexAnimal = 0, suivant = 1, apresSuivant = 2, compteurCouleur = 0;
        String couleur = "BLEU";
        boolean PMoitie = true;
        for (int i = 0; i < this.nbCartes; ++i) {
            Carte c = new Carte();
            this.RemplirC(c, indexAnimal, couleur, suivant, apresSuivant, PMoitie);
            indexAnimal = ++indexAnimal % this.nbAnimaux;
            if (i % 3 == 0 && i % 6 != 0) {
                suivant = 1;
                apresSuivant = 2;
            } else if (i % 6 == 0) {
                suivant = 2;
                apresSuivant = 1;
            }
            ++compteurCouleur;
            if (compteurCouleur == this.nbCartes / 4)
                couleur = "ROUGE";
            if (compteurCouleur == this.nbCartes / 2) {
                couleur = "BLEU";
                compteurCouleur = 0;
                PMoitie = false;
            }
            this.CartesObj.add(c);
        }
    }

    /**
     * Remplit la carte de ses deux podiums
     * @param c → la carte qui va être remplie
     * @param indexAnimal → position de l'animal dans la liste
     * @param couleur -> couleur du podium
     * @param suivant -> L'index de l'animal à mettre après l'animal courant
     * @param apresSuivant -> L'index de l'animal à mettre après l'animal suivant le courant
     * @param PMoitie → Arrivé à la moitié ? Les combinaisons sont les mêmes pour les deux podiums.
     */
    private void RemplirC(Carte c, int indexAnimal, String couleur, int suivant, int apresSuivant, Boolean PMoitie) {
        if (Objects.equals(couleur, "BLEU")) {
            c.getpBleu().ajouter_haut(this.TabAnimaux[indexAnimal]);
            c.getpBleu().ajouter_haut(this.TabAnimaux[(suivant + indexAnimal) % this.nbAnimaux]);
            if (PMoitie)
                c.getpBleu().ajouter_haut(this.TabAnimaux[(apresSuivant + indexAnimal) % this.nbAnimaux]);
            else
                c.getpRouge().ajouter_haut(this.TabAnimaux[(apresSuivant + indexAnimal) % this.nbAnimaux]);
        }
        if (Objects.equals(couleur, "ROUGE")) {
            c.getpRouge().ajouter_haut(this.TabAnimaux[indexAnimal]);
            c.getpRouge().ajouter_haut(this.TabAnimaux[(indexAnimal + suivant) % this.nbAnimaux]);
            if (PMoitie)
                c.getpRouge().ajouter_haut(this.TabAnimaux[(apresSuivant + indexAnimal) % this.nbAnimaux]);
            else
                c.getpBleu().ajouter_haut(this.TabAnimaux[(apresSuivant + indexAnimal) % this.nbAnimaux]);
        }
    }

    /**
     * Retourne une carte choisie au hasard dans la liste des cartes du jeu
     * @return la carte aléatoirement choisie
     */
    public Carte CarteHasard() {
        int iCarte = (int) (Math.random()*this.nbCartesRestantes);
        this.nbCartesRestantes--;
        return this.CartesObj.remove(iCarte);
    }

    public int getNbCartesRestantes() {
        return nbCartesRestantes;
    }
}
