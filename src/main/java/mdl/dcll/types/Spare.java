package mdl.dcll.types;

import mdl.dcll.types.Frame;

/**
 * Created by benja135 on 09/03/16.
 */
public class Spare extends Frame {

    private int c;

    public Spare(int c) {
        this.c = c;
    }

    public int score() {
        return 10;
    }

    public int score_c1() {
        return c;
    }

    public int score_c2() {
        return 10 - c;
    }

    public boolean isValid() {
        return c >= 0 && c < 10;
    }

    public String toString() {
        return "[" + c + ":Spare]";
    }
}
