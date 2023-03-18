/**
 * Projet de première année à l'IUT de Paris - Rive de Seine
 * Jeu de Crazy Circus par Dominique Ehrhard
 * @author Clothilde PROUX, Suyi LYN
 * @file Podium.java
 * Podiums sur lequels les animaux seront
 */
package crazy_circus;

import java.util.ArrayList;
import java.util.Objects;


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

    /**
     * Affiche le podium : sa couleur et les animaux dessus
     * @return la représentation en chaîne de caractère du podium
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = this.nbAnimal-1; i >= 0; --i) {
            sb.append(this.animaux.get(i));
            sb.append("\n");
        }
        sb.append("----\n");
        sb.append(this.couleur);
        return sb.toString();
    }
}
