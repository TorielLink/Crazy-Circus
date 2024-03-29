/**
 * Projet de première année à l'IUT de Paris - Rive de Seine
 * Jeu de Crazy Circus par Dominique Ehrhard
 * @author Clothilde PROUX, Suyi LYN
 * @file Sequence.java
 * Réponse du joueur et liste des ordres applicables aux animaux
 */
package test;

import java.util.ArrayList;


public class Sequence {
    private final ArrayList<Ordres> morceauSequence;
    private enum Ordres {
        KI, LO, MA, SO, NI
    }

    /**
     * Constructeur depuis une séquence
     * Découpe en morceaux de deux caractères la séquence pour obtenir les ordres
     * @param sequenceOrdres → la séquence jouée par le joueur
     */
    public Sequence(String sequenceOrdres) {
        assert (sequenceOrdres.length() % 2 == 0) : "La séquence ne peut être impaire.";
        this.morceauSequence = new ArrayList<>();
        for (int i = 0; i < sequenceOrdres.length(); i+=2) {
            try {
                this.morceauSequence.add(Ordres.valueOf(sequenceOrdres.toUpperCase().substring(i, i+2)));
            }
            catch (IllegalArgumentException e) {
                System.err.println("La séquence est incorrecte : un ordre n'existe pas");
                this.morceauSequence.clear();
                break;
            }
        }
    }

    /**
     * Exécute les ordres de la séquence sur la carte de départ
     * @param carteDepart → carte de départ de la manche en cours
     * @return une carte temporaire qu'on comparera avec la carte de fin
     */
    public Carte execute(Carte carteDepart) {
        Carte carteTemp = new Carte(carteDepart);


        for (Ordres testOrdre: morceauSequence) {
            switch (testOrdre) {
                case MA -> { // L’animal se trouvant en bas de la pile du podium rouge monte et se place en haut de la pile de ce même podium.
                    if (carteTemp.getpRouge().getNbAnimal() != 0)
                        carteTemp.getpRouge().ajouter_haut(carteTemp.getpRouge().retirer_bas());
                    else
                        System.err.println("L'ordre ne pas pas être exécuté car aucun animal ne peut être déplacé.");
                }
                case NI -> { // L’animal se trouvant en bas de la pile du podium bleu monte et se place en haut de la pile de ce même podium.
                    if (carteTemp.getpBleu().getNbAnimal() != 0)
                        carteTemp.getpBleu().ajouter_haut(carteTemp.getpBleu().retirer_bas());
                    else
                        System.err.println("L'ordre ne pas pas être exécuté car aucun animal ne peut être déplacé.");
                }
                case KI -> { // L’animal se trouvant en haut de la pile du podium bleu saute pour rejoindre le sommet de la pile du podium rouge.
                    if (carteTemp.getpBleu().getNbAnimal() != 0)
                        carteTemp.getpRouge().ajouter_haut(carteTemp.getpBleu().retirer_sommet());
                    else
                        System.err.println("L'ordre ne pas pas être exécuté car aucun animal ne peut être déplacé.");
                }
                case LO -> { // L’animal se trouvant en haut de la pile du podium rouge saute pour rejoindre le sommet de la pile du podium bleu.
                    if (carteTemp.getpRouge().getNbAnimal() != 0)
                        carteTemp.getpBleu().ajouter_haut(carteTemp.getpRouge().retirer_sommet());
                    else
                        System.err.println("L'ordre ne pas pas être exécuté car aucun animal ne peut être déplacé.");
                }
                case SO -> { // Les deux animaux se trouvant au sommet des piles des deux podiums échangent leur place.
                    if (carteTemp.getpRouge().getNbAnimal() != 0 && carteTemp.getpBleu().getNbAnimal() != 0) {
                        if (carteTemp.getpBleu().getNbAnimal() > carteTemp.getpRouge().getNbAnimal()) {
                            carteTemp.getpRouge().ajouter_haut(carteTemp.getpBleu().retirer_sommet());
                            carteTemp.getpBleu().ajouter_haut(carteTemp.getpRouge().retirer_bas());
                        } else {
                            carteTemp.getpBleu().ajouter_haut(carteTemp.getpRouge().retirer_sommet());
                            carteTemp.getpRouge().ajouter_haut(carteTemp.getpBleu().retirer_bas());
                        }
                    } else
                        System.err.println("L'ordre ne pas pas être exécuté car aucun animal ne peut être déplacé.");
                }
            }
        }
        return carteTemp;
    }
}



