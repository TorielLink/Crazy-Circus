package crazy_circus;

public enum Animal{
    OURS, LION, ELEPHANT;

    public static String getNomAnimal(Animal animal) {
        if (animal == LION)
            return "    " + LION.toString() + "    ";
        else if (animal == ELEPHANT)
            return "  " + ELEPHANT.toString() + "  ";
        else if (animal == OURS)
            return "    " + OURS.toString() + "    ";
        else
            return "            ";
    }
}

