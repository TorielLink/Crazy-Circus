package crazy_circus;

import java.util.Scanner;
import java.util.ArrayList;

public class Partie {
    private static int NB_CARTES = 24;
    private static ArrayList<Joueur> listeJoueurs;
    private static Objectif listeCartes;
    private Carte carteInit;
    private Carte carteFin;
    private static boolean premiereManche = true;


    public Partie(){
        Objectif listeCartes = new Objectif();
        listeCartes.RemplirObj();
    }

    public static void main(String[] args) {
        for (String arg : args) {
            listeJoueurs.add(new Joueur(arg));
        }
        Partie partie = new Partie();
        while (listeCartes.getNbCartesRestantes() > 0) {
            partie.jouerManche();
            NB_CARTES--;
        }
    }

    public int getNbJoueurs(){
        return listeJoueurs.size();
    }

    public Carte tirerCarte() {
        return listeCartes.CarteHasard();
    }

    // Remet à false avant le début d'une autre manche
    private void setJoueursPasJoue(){
        for (Joueur joueur : listeJoueurs) {
            joueur.setAJoue(false);
        }
    }



    public void jouerManche() {
        setJoueursPasJoue();

        // Tirage des cartes
        if (premiereManche) {
            this.carteInit = tirerCarte();
            premiereManche = false;
        }
        else {
            this.carteInit = this.carteFin;
        }
        this.carteFin = tirerCarte();

        while ((getNbJoueurs() - Joueur.getNbJoueursJoue()) != 1) { //Tant qu'un joueur n'a pas gagné || il ne reste qu'un joueur pouvant jouer
            Scanner sc = new Scanner(System.in);
            String nomJoueur = sc.toString();
            String reponseJoueur = sc.next();
            Joueur joueurActuel = null;

            // Vérifie si le joueur existe
            for (Joueur joueur : listeJoueurs) {
                joueurActuel = joueur.reconnaitJoueur(nomJoueur);
                if (joueurActuel != null) break;
            }
            if (joueurActuel == null) {
                System.out.println("Le joueur n'existe pas.");
            }

            else {
                Sequence sequenceJoueur = new Sequence(reponseJoueur);
                Carte carteReponseJoueur = sequenceJoueur.execute(carteInit);


                if (carteReponseJoueur.compare(carteFin)) {
                    joueurActuel.setScore();
                    return;
                }
                else {
                    joueurActuel.setAJoue(true);
                }
            }
        }

        // Faire le cas où il reste qu'un joueur.
        //TODO

    }
}
