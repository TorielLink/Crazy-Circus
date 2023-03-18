/**
 * Projet de première année à l'IUT de Paris - Rive de Seine
 * Jeu de Crazy Circus par Dominique Ehrhard
 * @author Clothilde PROUX, Suyi LYN
 * @file Carte.java
 * Cartes contenant deux podiums, un rouge et un bleu
 */
package crazy_circus;


public class Carte {
    private final Podium pBleu;
    private final Podium pRouge;

    /**
     * Constructeur vide pour initialiser de nouvelles cartes de fin (et de début pour la première manche)
     */
    public Carte() {
        pBleu = new Podium("BLEU");
        pRouge = new Podium("ROUGE");
    }

    /**
     * Constructeur depuis une carte pour faire passer les cartes de fin aux cartes de début
     * @param carte → la carte finale du tour précédent
     */
    public Carte(Carte carte) {
        pBleu = carte.getpBleu();
        pRouge = carte.getpRouge();
    }

    /**
     * Teste si deux cartes sont composées des mêmes podiums
     * @param carteAVerifier -> la carte de comparaison
     * @return true s'elles sont égales ; false sinon
     */
    public boolean compare(Carte carteAVerifier){
        return this.pBleu.compare(carteAVerifier.getpBleu()) && this.pRouge.compare(carteAVerifier.getpRouge());
    }

    public Podium getpBleu() {
        return pBleu;
    }

    public Podium getpRouge() {
        return pRouge;
    }

}
