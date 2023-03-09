package crazy_circus;

public class Joueur {
    private static int nbJoueursJoue = 0;
    private final String pseudo;
    private int score;
    private int rang;
    private boolean aJoue;

    public Joueur (String pseudo) {
        this.pseudo = pseudo;
        this.score = 0;
        this.aJoue = false;

    }


    public Joueur reconnaitJoueur(String nom) {
        if (this.pseudo.equals(nom))
            return this;
        return null;
    }

    public static int getNbJoueursJoue() {
        return nbJoueursJoue;
    }

    public static void setNbJoueursJoue(int nbJoueursJoue) {
        Joueur.nbJoueursJoue++;
    }

    public void setScore() {
        this.score++;
    }

    public void setAJoue(boolean bool) {
        this.aJoue = bool;
    }
}
