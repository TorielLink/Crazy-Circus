package crazy_circus;

import java.util.Scanner;
import java.util.ArrayList;

public class Partie {
    private static ArrayList<Joueur> listeJoueurs;
    private static Objectif listeCartes;
    private Carte carteInit;
    private Carte carteFin;
    private static boolean premiereManche = true;


    public Partie() {
        listeCartes = new Objectif();
        listeCartes.RemplirObj();
        listeJoueurs = new ArrayList<>();
    }


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

    public int getNbJoueurs() {
        return listeJoueurs.size();
    }

    public Carte tirerCarte() {
        return listeCartes.CarteHasard();
    }

    private void setJoueursPasJoue() { // Remet à false avant le début d'une autre manche
        for (Joueur joueur : listeJoueurs) {
            joueur.setAJoue(false);
        }
    }

    public String affichageDeb() {
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
        sb.append("    BLEU        ROUGE    =>     BLEU        ROUGE \n");
        sb.append("  ----------------------------------------------------\n");
        sb.append("""
                        KI : BLEU --> ROUGE    NI : BLEU ^
                        LO : BLEU <-- ROUGE    MA : ROUGE ^
                        SO : BLEU <-> ROUGE\
                """);
        return sb.toString();
    }

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

    @SuppressWarnings("ressource")
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

        System.out.println(this.affichageDeb());

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

            }
        }
    }

    public static void main(String[] args) {
        Partie partie = new Partie();
        for (String arg : args) {
            listeJoueurs.add(new Joueur(arg));
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




