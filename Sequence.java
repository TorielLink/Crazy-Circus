package crazy_circus;

import java.util.ArrayList;


public class Sequence {
    private final ArrayList<Ordres> morceauSequence;
    private enum Ordres {
        KI, LO, MA, SO, NI
    }

    public Sequence(String sequenceOrdres) {
        assert (sequenceOrdres.length() % 2 == 0) : "La séquence ne peut être impaire.";
        this.morceauSequence = new ArrayList<>();
        for (int i = 0; i < sequenceOrdres.length(); i+=2) {
            this.morceauSequence.add(Ordres.valueOf(sequenceOrdres.substring(i, i+2)));
        }
    }

    public Carte execute(Carte carteDepart) {
        Carte carteTemp = new Carte(carteDepart);

        for (Ordres testOrdre: morceauSequence) {
            switch (testOrdre) {
                case MA : carteTemp.getpRouge().ajouter_haut(carteTemp.getpRouge().retirer_bas());
                case NI : carteTemp.getpBleu().ajouter_haut(carteTemp.getpBleu().retirer_bas());
                case KI : carteTemp.getpRouge().ajouter_haut(carteTemp.getpBleu().retirer_sommet());
                case LO : carteTemp.getpBleu().ajouter_haut(carteTemp.getpRouge().retirer_sommet());
                case SO : if(carteTemp.getpBleu().getNbAnimal() > carteTemp.getpRouge().getNbAnimal()) {
                    carteTemp.getpRouge().ajouter_haut(carteTemp.getpBleu().retirer_sommet());
                    carteTemp.getpBleu().ajouter_haut(carteTemp.getpRouge().retirer_bas());
                }
                else {
                    carteTemp.getpBleu().ajouter_haut(carteTemp.getpRouge().retirer_sommet());
                    carteTemp.getpRouge().ajouter_haut(carteTemp.getpBleu().retirer_bas());
                }
                default : throw new IllegalStateException("L'ordre suivant n'existe pas : " + testOrdre);
            }
        }
        return carteTemp;
    }

    // Fonction pour vérifier que la carte Temp est la même que celle finale
    //TODO
}



