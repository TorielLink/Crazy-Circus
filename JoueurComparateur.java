/**
 * Projet de première année à l'IUT de Paris - Rive de Seine
 * Jeu de Crazy Circus par Dominique Ehrhard
 * @author Clothilde PROUX, Suyi LYN
 * @file JoueurComparateur.java
 * Comparateur des scores et noms des joueurs
 */
package test;

import java.util.Comparator;

public class JoueurComparateur implements Comparator<Joueur> {
    /**
     * Permet le tri des joueurs par leur score et, en cas d'égalité, par leur pseudo
     * @return la différence entre les scores
     */
    @Override
    public int compare(Joueur j1, Joueur j2) {
        if (j2.getScore() > j1.getScore())
            return 1;
        else if (j2.getScore() < j1.getScore()) {
            return -1;
        }
        else {
            return j1.toString().compareTo(j2.toString());
        }

    }
}
