package crazy_circus;

import java.util.Scanner;
import java.util.ArrayList;

public class Partie {
    private static int NB_ANIMAUX = 3;
    private static ArrayList<Joueur> listeJoueurs;
    private static Objectif listeCartes;
    private Carte carteInit;
    private Carte carteFin;
    private static boolean premiereManche = true;


    public Partie(){
        listeCartes = new Objectif();
        listeCartes.RemplirObj();
        listeJoueurs = new ArrayList<Joueur>();
    }
    

    public String getNomAnimal(Animal animal) {
        if (animal == Animal.LION)
            return "    " + Animal.LION.toString() + "    ";
        else if (animal == Animal.ELEPHANT)
            return "  " + Animal.ELEPHANT.toString() + "  ";
        else if (animal == Animal.OURS)
            return "    " + Animal.OURS.toString() + "    ";
        else
            return "            ";
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


    public String affichage(){
        StringBuilder sb = new StringBuilder();
        for (int i = NB_ANIMAUX - 1; i >= 0; --i){
        	if (this.carteInit.getpBleu().getNbAnimal() < i+1)
        		sb.append("            ");
        	else	
        		sb.append(this.getNomAnimal(this.carteInit.getpBleu().getAnimal(i))) ;
        	
        	if (this.carteInit.getpRouge().getNbAnimal() < i+1)
        		sb.append("            ");
        	else	
        		sb.append(this.getNomAnimal(this.carteInit.getpRouge().getAnimal(i))) ;
        	
        	sb.append("    ");
        	
        	if (this.carteFin.getpBleu().getNbAnimal() < i+1)
        		sb.append("            ");
        	else		
        		sb.append(this.getNomAnimal(this.carteFin.getpBleu().getAnimal(i))) ;
        	
        	if (this.carteFin.getpRouge().getNbAnimal() < i+1)
        		sb.append("            ");
        	else
        		sb.append(this.getNomAnimal(this.carteFin.getpRouge().getAnimal(i))) ;
        	sb.append("\n");
        }
        sb.append("    ----        ----     =>     ----        ---- \n");
        sb.append("    BLEU        ROUGE    =>     BLEU        ROUGE \n");
        sb.append("  ----------------------------------------------------\n");
        sb.append("        KI : BLEU --> ROUGE    NI : BLEU ^\n" +
                "        LO : BLEU <-- ROUGE    MA : ROUGE ^\n" +
                "        SO : BLEU <-> ROUGE");
        return sb.toString();
    }

    @SuppressWarnings("resource")
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
        
        System.out.println(this.affichage());

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
            }
            else if (joueurActuel.isAJoue())
            	System.err.println("Ce joueur a déjà joué");
            
            else {
                Sequence sequenceJoueur = new Sequence(reponseJoueur);
                Carte carteReponseJoueur = sequenceJoueur.execute(this.carteInit);

                if (carteReponseJoueur.compare(carteFin)) {
                	System.out.println("Joueur " + joueurActuel.toString() + " gagne cette manche");
                    joueurActuel.setScore();
                    return;
                }
                else {
                    joueurActuel.setAJoue(true);
                    System.err.println("C'est faux ! " + joueurActuel.toString() + " ne peut plus jouer pour ce tour !");
                    Joueur.setNbJoueursJoue(Joueur.getNbJoueursJoue()+1);
                }
            }
        }
        

        for (Joueur joueur : listeJoueurs) {
        	
            if (!joueur.isAJoue()) {

                joueur.setScore();
            	System.out.println("Le joueur " + joueur.toString() + " a gagné cette manche ! ");
        
            }
        }
    }


    public static void main(String[] args) {
    	Partie partie = new Partie();
        for (String arg : args) {
            listeJoueurs.add(new Joueur(arg));            
        }

        System.out.println();

        while (listeCartes.getNbCartesRestantes() > 0) {
            partie.jouerManche();
            Joueur.setNbJoueursJoue(0);
        }
        

        int meilleurScore = 0;
        for (Joueur joueur : listeJoueurs) {
            if (joueur.getScore() > meilleurScore) {
                meilleurScore = joueur.getScore();
            }
        }
        
    }
}




