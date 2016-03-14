package mdl.dcll.types;

import static mdl.dcll.types.Constantes.MAX_QUILLE;

/**
 * Created by benja135 on 09/03/16.
 * Un CoupAdditionnel est le type de coup joué aprés la Frame 10
 */
public class CoupAdditionnel extends Frame {

    private int c;

    public CoupAdditionnel(int c) {
        this.c = c;
    }

    public int score() {
        return c;
    }

    public int scoreC1() {
        return c;
    }

    public boolean isValid() {
        return c >= 0 && c <= MAX_QUILLE;
    }

    public String toString() {
        return "[Coup additionnel: " + c + "]";
    }

}