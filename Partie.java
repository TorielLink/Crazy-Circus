/**
 * Projet de première année à l'IUT de Paris - Rive de Seine
 * Jeu de Crazy Circus par Dominique Ehrhard
 * @author Clothilde PROUX, Suyi LYN
 * @file Partie.java
 * Joue une partie complète de Crazy Circus
 */
package test;

import java.util.Scanner;
import java.util.ArrayList;

public class Partie {
    private static ArrayList<Joueur> listeJoueurs;
    private static Objectif listeCartes;
    private Carte carteInit;
    private Carte carteFin;
    private static boolean premiereManche = true;

    /**
     * Constructeur vide créant une partie
     */
    public Partie() {
        listeCartes = new Objectif();
        listeCartes.RemplirObj();
        listeJoueurs = new ArrayList<>();
    }

    /*
     * Tire une carte au hasard
     * param[out] : La carte tirée
     */
    public Carte tirerCarte() {
        return listeCartes.CarteHasard();
    }

    /**
     * Affiche l'objectif, soit la carte de départ et celle de fin, et les ordres possibles
     * @return les cartes de la manche et les ordres
     */
    public String affichageDebManche() {
        StringBuilder sb = new StringBuilder();
        int NB_ANIMAUX = 3;
        for (int i = NB_ANIMAUX - 1; i >= 0; --i) {
            if (this.carteInit.getpBleu().getNbAnimal() < i + 1)
                sb.append("            ");
            else
                sb.append(this.getNomAnimal(this.carteInit.getpBleu().getAnimal(i)));

            if (this.carteInit.getpRouge().getNbAnimal() < i + 1)
                sb.append("            ");
            else
                sb.append(this.getNomAnimal(this.carteInit.getpRouge().getAnimal(i)));

            sb.append("    ");

            if (this.carteFin.getpBleu().getNbAnimal() < i + 1)
                sb.append("            ");
            else
                sb.append(this.getNomAnimal(this.carteFin.getpBleu().getAnimal(i)));

            if (this.carteFin.getpRouge().getNbAnimal() < i + 1)
                sb.append("            ");
            else
                sb.append(this.getNomAnimal(this.carteFin.getpRouge().getAnimal(i)));
            sb.append("\n");
        }
        sb.append("    ----        ----     =>     ----        ---- \n");
        sb.append("    "+ carteInit.getpBleu().getCouleur() +"        "+carteInit.getpRouge().getCouleur()+"    =");
        sb.append(">     " + carteFin.getpBleu().getCouleur()+"        "+carteFin.getpRouge().getCouleur()+" \n");
        
        sb.append("  ----------------------------------------------------\n");
        sb.append("""
                        KI : BLEU --> ROUGE    NI : BLEU ^
                        LO : BLEU <-- ROUGE    MA : ROUGE ^
                        SO : BLEU <-> ROUGE\
                """);
        return sb.toString();
    }

    /**
     * Affiche la fin de la partie, le classement des joueurs
     * @return le rang, le pseudo et le score des joueurs
     */
    public static String affichageFin() {
        StringBuilder sb = new StringBuilder();

        sb.append("----------------------------------------------------\n");
        for (Joueur joueur : listeJoueurs) {
            sb.append(joueur.getRang());
            sb.append(" ");
            sb.append(joueur);
            sb.append(" (");
            sb.append(joueur.getScore());
            if (joueur.getScore()>1)
                sb.append(" points)\n");
            else
                sb.append(" point)\n");
        }
        return sb.toString();
    }

    /**
     * Simule tout le déroulement de la partie
     */
    @SuppressWarnings("resource")
    public void jouerManche() {
        setJoueursPasJoue();

        // Tirage des cartes
        if (premiereManche) {
            this.carteInit = tirerCarte();
            premiereManche = false;
        } else {
            this.carteInit = this.carteFin;
        }
        this.carteFin = tirerCarte();

        System.out.println(this.affichageDebManche());

        while ((getNbJoueurs() - Joueur.getNbJoueursJoue()) != 1) { //Tant qu'il ne reste pas qu'un joueur pouvant jouer
            Scanner sc = new Scanner(System.in);
            String nomJoueur = sc.next();
            String reponseJoueur = sc.next();
            Joueur joueurActuel = null;

            // Vérifie si le joueur existe
            for (Joueur joueur : listeJoueurs) {
                joueurActuel = joueur.reconnaitJoueur(nomJoueur);
                if (joueurActuel != null) break;
            }

            if (joueurActuel == null) {
                System.err.println("Ce joueur n'existe pas.");
            } else if (joueurActuel.isAJoue())
                System.err.println("Ce joueur a déjà joué");

            // Teste la séquence proposée par le joueur
            else {
                Sequence sequenceJoueur = new Sequence(reponseJoueur);
                Carte carteReponseJoueur = sequenceJoueur.execute(this.carteInit);

                if (carteReponseJoueur.compare(carteFin)) {
                    System.out.println("Joueur " + joueurActuel + " gagne cette manche");
                    joueurActuel.setScore();
                    return;
                } else {
                    joueurActuel.setAJoue(true);
                    System.err.println("C'est faux ! " + joueurActuel + " ne peut plus jouer pour ce tour !");
                    Joueur.setNbJoueursJoue(Joueur.getNbJoueursJoue() + 1);
                }
            }
        }


        for (Joueur joueur : listeJoueurs) {

            if (!joueur.isAJoue()) {

                joueur.setScore();
                System.out.println("Le joueur " + joueur + " gagne cette manche ! ");
                return;
            }
        }
    }

    /* Renvoie comment chaque animal devra être affiché à l'ecran 
     * @param[in] : Animal
     * @param[out] : String de l'animal
     */
    public String getNomAnimal(Animal animal) {
        if (animal == Animal.LION)
            return "    " + Animal.LION + "    ";
        else if (animal == Animal.ELEPHANT)
            return "  " + Animal.ELEPHANT + "  ";
        else if (animal == Animal.OURS)
            return "    " + Animal.OURS + "    ";
        else
            return "            ";
    }

    /* Renvoie le nombre de joueurs
     * param[out] : Le nombre de joueurs
     */
    public int getNbJoueurs() {
        return listeJoueurs.size();
    }

    
    /* Met que tous les joueurs n'ont pas joué
     * 
     */
    private void setJoueursPasJoue() { // Remet à false avant le début d'une autre manche
        for (Joueur joueur : listeJoueurs) {
            joueur.setAJoue(false);
        }
    }
    
    

    public static void main(String[] args) {
        Partie partie = new Partie();
        for (String arg : args) {
            listeJoueurs.add(new Joueur(arg));
        }
        
        if (listeJoueurs.size() == 0) {
        	System.out.println("Il faut mettre les noms de joueurs avant de lancer de programme ! ");
        	return;
        }

        while (listeCartes.getNbCartesRestantes() > 0) {
            partie.jouerManche();
            Joueur.setNbJoueursJoue(0);
        }


        listeJoueurs.sort(new JoueurComparateur()); // Trie les joueurs par leur score

        int scoreBest = listeJoueurs.get(0).getScore();
        int rang = 1;
        for (Joueur joueur : listeJoueurs) { // Attribue le rang en fonction du score
            if (joueur.getScore() == scoreBest) {
                joueur.setRang(rang);
            }
            else {
                rang++;
                scoreBest = joueur.getScore();
                joueur.setRang(rang);
            }
        }
        System.out.println(affichageFin());
    }
}




