package crazy_circus;

import java.util.ArrayList;
import java.util.Objects;


public class Podium {
    private String couleur;
    private int nbAnimal;
    private ArrayList<Animal> animaux;


    public Podium(String couleur) {
        assert (Objects.equals(couleur, "BLEU") || Objects.equals(couleur, "ROUGE"));
        this.couleur = couleur;
        this.nbAnimal = 0;
        this.animaux = new ArrayList<Animal>();
    }

    public Podium(Podium p, String couleur) {
        this(couleur);
        this.nbAnimal += p.nbAnimal;
        this.animaux.addAll(p.animaux);
    }

    public int getNbAnimal() {
        return nbAnimal;
    }

    /*
     * Ajoute au sommet du podium un animal
     */
    public void ajouter_haut(Animal animal) {
        assert (this.nbAnimal < 3);
        this.animaux.add(animal);
        this.nbAnimal++;
    }

    /*
     * Retire l'animal se trouvant en bas du podium
     * Retourne l'animal retiré
     */
    public Animal retirer_sommet() {
        assert (this.nbAnimal > 0);
        --this.nbAnimal;
        return this.animaux.remove(this.nbAnimal);
    }

    /*
     * Retire l'animal se trouvant au sommet du podium
     * Retourne l'animal retiré
     */
    public Animal retirer_bas() {
        assert (this.nbAnimal > 0);
        this.nbAnimal--;
        return this.animaux.remove(0);

    }

    public String getAnimal(int index) {
        assert (index < 3 && index > 0);
        StringBuilder sb = new StringBuilder();
        return sb.append(this.animaux.get(index)).toString();
    }

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
