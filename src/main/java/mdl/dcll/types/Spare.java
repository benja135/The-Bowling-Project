package mdl.dcll.types;

import static mdl.dcll.types.Constantes.MAX_QUILLE;

/**
 * Created by benja135 on 09/03/16.
 * Un Spare est une Frame où les quilles sont tombées au bout des deux coups.
 */
public class Spare extends Frame {

    private int c;

    public Spare(int c) {
        this.c = c;
    }

    public int score() {
        return MAX_QUILLE;
    }

    public int scoreC1() {
        return c;
    }

    public boolean isValid() {
        return c >= 0 && c < MAX_QUILLE;
    }

    public String toString() {
        return "[" + c + ":Spare]";
    }
}
