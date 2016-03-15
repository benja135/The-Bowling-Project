package mdl.dcll;

/**
 * Created by benja135 on 13/03/16.
 */
public class Couple {
    private int entier;
    private String chaine;

    public Couple(String chaine, int entier) {
        this.entier = entier;
        this.chaine = chaine;
    }

    public String getChaine() {
        return chaine;
    }

    public int getEntier() {
        return entier;
    }
}
