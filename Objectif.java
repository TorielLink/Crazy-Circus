package crazy_circus;

import java.util.ArrayList;
import java.util.Objects;


public class Objectif {
    //----------Mettre en enum dans un autre fichier----------
    private final int nbCartes = 24;
    private final int nbAnimaux = 3;
    //--------------------------------------------------------
    private final ArrayList<Carte> CartesObj;
    private final Animal[] TabAnimaux;
    private int nbCartesRestantes;

    public Objectif() {
        this.CartesObj = new ArrayList<Carte>();
        this.TabAnimaux = new Animal[]{Animal.ELEPHANT, Animal.LION, Animal.OURS};
        this.nbCartesRestantes = this.nbCartes;
    }

    public String getCarteObj(int index) { // À modifier
        return this.CartesObj.get(index).getpBleu().toString() + "\n\n" + this.CartesObj.get(index).getpRouge().toString();
    }

    public int getNbCartesRestantes() {
        return nbCartesRestantes;
    }

    public void RemplirObj() {
        int indexAnimal = 0, suivant = 1, apresSuivant = 2, compteurCouleur = 0;
        String couleur = "BLEU";
        boolean PMoitie = true;
        for (int i = 0; i < this.nbCartes; ++i) {
            Carte c = new Carte();
            this.RemplirC(c, indexAnimal, couleur, suivant, apresSuivant, PMoitie);
            indexAnimal = ++indexAnimal % this.nbAnimaux;
            if (i % 3 == 0 && i % 6 != 0) {
                suivant = 1;
                apresSuivant = 2;
            } else if (i % 6 == 0) {
                suivant = 2;
                apresSuivant = 1;
            }
            ++compteurCouleur;
            if (compteurCouleur == this.nbCartes / 4)
                couleur = "ROUGE";
            if (compteurCouleur == this.nbCartes / 2) {
                couleur = "BLEU";
                compteurCouleur = 0;
                PMoitie = false;
            }
            this.CartesObj.add(c);
        }
    }


    private void RemplirC(Carte c, int indexAnimal, String couleur, int suivant, int apresSuivant, Boolean PMoitie) {
        if (Objects.equals(couleur, "BLEU")) {
            c.getpBleu().ajouter_haut(this.TabAnimaux[indexAnimal]);
            c.getpBleu().ajouter_haut(this.TabAnimaux[(suivant + indexAnimal) % this.nbAnimaux]);
            if (PMoitie)
                c.getpBleu().ajouter_haut(this.TabAnimaux[(apresSuivant + indexAnimal) % this.nbAnimaux]);
            else
                c.getpRouge().ajouter_haut(this.TabAnimaux[(apresSuivant + indexAnimal) % this.nbAnimaux]);
        }
        if (Objects.equals(couleur, "ROUGE")) {
            c.getpRouge().ajouter_haut(this.TabAnimaux[indexAnimal]);
            c.getpRouge().ajouter_haut(this.TabAnimaux[(indexAnimal + suivant) % this.nbAnimaux]);
            if (PMoitie)
                c.getpRouge().ajouter_haut(this.TabAnimaux[(apresSuivant + indexAnimal) % this.nbAnimaux]);
            else
                c.getpBleu().ajouter_haut(this.TabAnimaux[(apresSuivant + indexAnimal) % this.nbAnimaux]);
        }
    }

    public Carte CarteHasard() {
        int iCarte = (int) (Math.random()*this.nbCartesRestantes);
        this.nbCartesRestantes--;
        return this.CartesObj.remove(iCarte);
    }
}
