package crazy_circus;

import java.util.Comparator;

public class JoueurComparateur implements Comparator<Joueur> {
    @Override
    public int compare(Joueur j1, Joueur j2) {
        if (j2.getScore() > j1.getScore())
            return 1;
        else if (j2.getScore() < j1.getScore()) {
            return -1;
        }
        else {
            return j1.toString().compareTo(j2.toString());
        }

    }
}
