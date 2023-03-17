package crazy_circus;

import java.util.ArrayList;
import java.util.Objects;


public class Podium {
    private final String couleur;
    private int nbAnimal;
    private final ArrayList<Animal> animaux;


    public Podium(String couleur) {
        assert (Objects.equals(couleur, "BLEU") || Objects.equals(couleur, "ROUGE"));
        this.couleur = couleur;
        this.nbAnimal = 0;
        this.animaux = new ArrayList<>();
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

    public Animal getAnimal(int index) {
        assert (index < 3 && index > 0);
        if (this.animaux.get(index) == null)
            return null;
        return this.animaux.get(index);
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
    
    public ArrayList<Animal> getAnimaux(){
    	return this.animaux;
    }
    
    public boolean compare(Podium p) {
    	return this.animaux.equals(p.getAnimaux());
    }
}
