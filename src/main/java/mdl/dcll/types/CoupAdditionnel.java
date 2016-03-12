package mdl.dcll.types;

import mdl.dcll.types.Frame;

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

    public int score_c1() {
        return c;
    }

    public boolean isValid() {
        return c >= 0 && c <= 10;
    }

    public String toString() {
        return "[Coup additionnel: " + c + "]";
    }

}