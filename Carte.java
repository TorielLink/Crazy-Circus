package crazy_circus;

import javax.management.Query;

public class Carte {
    private final Podium pBleu;
    private final Podium pRouge;

    public Carte() {
        pBleu = new Podium("BLEU");
        pRouge = new Podium("ROUGE");
    }

    public Carte(Carte carte) {
        pBleu = carte.getpBleu();
        pRouge = carte.getpRouge();
    }

    public Podium getpBleu() {
        return pBleu;
    }

    public Podium getpRouge() {
        return pRouge;
    }

    public boolean compare(Carte carteAVerifier){
        return this.equals(carteAVerifier);
    }
}
