/**
 * Projet de première année à l'IUT de Paris - Rive de Seine
 * Jeu de Crazy Circus par Dominique Ehrhard
 * @author Clothilde PROUX, Suyi LYN
 * @file Carte.java
 * Cartes contenant deux podiums, un rouge et un bleu
 */
package test;

import java.util.ArrayList;
import java.util.Objects;

public class Carte {
    private final Podium pBleu;
    private final Podium pRouge;
    
    
    public class Podium {
        private final String couleur;
        private int nbAnimal;
        private ArrayList<Animal> animaux;

        /**
         * Constructeur avec une couleur
         * @param couleur -> couleur du podium : soit rouge, soit bleu
         */
        public Podium(String couleur) {
            assert (Objects.equals(couleur, "BLEU") || Objects.equals(couleur, "ROUGE"));
            this.couleur = couleur;
            this.nbAnimal = 0;
            this.animaux = new ArrayList<>();
        }

        /**
         * Ajoute au sommet du podium un animal
         * @param animal → l'animal qui va être mis au sommet de la podium
         */
        public void ajouter_haut(Animal animal) {
            assert (this.nbAnimal < 3);
            this.animaux.add(animal);
            this.nbAnimal++;
        }

        /**
         * Retire l'animal se trouvant en bas du podium
         * @return l'animal retiré
         */
        public Animal retirer_sommet() {
            assert (this.nbAnimal > 0);
            --this.nbAnimal;
            return this.animaux.remove(this.nbAnimal);
        }

        /**
         * Retire l'animal se trouvant au sommet du podium
         * @return l'animal retiré
         */
        public Animal retirer_bas() {
            assert (this.nbAnimal > 0);
            this.nbAnimal--;
            return this.animaux.remove(0);

        }

        /**
         * Teste si deux podiums sont composés des mêmes animaux
         * @param p -> le podium de comparaison
         * @return true s'ils sont égaux ; false sinon
         */
        public boolean compare(Podium p) {
            return this.animaux.equals(p.getAnimaux());
        }

        public int getNbAnimal() {
            return nbAnimal;
        }

        public Animal getAnimal(int index) {
            assert (index < 3 && index > 0);
            if (this.animaux.get(index) == null)
                return null;
            return this.animaux.get(index);
        }

        public ArrayList<Animal> getAnimaux(){
        	return this.animaux;
        }

        public String getCouleur() {
        	return this.couleur;
        }

    }


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
