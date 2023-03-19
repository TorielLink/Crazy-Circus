/**
 * Projet de première année à l'IUT de Paris - Rive de Seine
 * Jeu de Crazy Circus par Dominique Ehrhard
 * @author Clothilde PROUX, Suyi LYN
 * @file Joueur.java
 * Joueurs de la partie
 */
package test;

public class Joueur {
    private static int nbJoueursJoue = 0;
    private final String pseudo;
    private int score;
    private int rang;
    private boolean aJoue;

    /**
     * Constructeur à partir d'un nom
     * @param pseudo → pseudo donné au joueur pour s'identifier
     */
    public Joueur (String pseudo) {
        this.pseudo = pseudo;
        this.score = 0;
        this.aJoue = false;

    }

    /**
     * Teste si le joueur existe ou non
     * @param nom → le nom entré par le joueur
     * @return null si le joueur n'existe pas ; le joueur sinon
     */
    public Joueur reconnaitJoueur(String nom) {
        if (this.pseudo.equals(nom))
            return this;
        return null;
    }

    public static int getNbJoueursJoue() {
        return nbJoueursJoue;
    }

    public static void setNbJoueursJoue(int nbJoueursJoue) {
        Joueur.nbJoueursJoue = nbJoueursJoue;
    }

    public void setScore() {
        this.score++;
    }

    public int getScore() {
        return score;
    }

    public void setAJoue(boolean bool) {
        this.aJoue = bool;
    }

    public boolean isAJoue() {
        return aJoue;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public int getRang() {
        return rang;
    }
    
    public String toString() {
        return this.pseudo;

    }
}
