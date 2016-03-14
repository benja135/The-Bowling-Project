package mdl.dcll.types;

import static mdl.dcll.types.Constantes.MAX_QUILLE;

/**
 * Created by benja135 on 09/03/16.
 * Un Strike est une Frame où toutes les quilles sont tombées en 1 coup.
 */
public class Strike extends Frame {

    public Strike() {
    }

    public int score() {
        return MAX_QUILLE;
    }

    public int scoreC1() {
        return MAX_QUILLE;
    }

    public boolean isValid() {
        return true;
    }

    public String toString() {
        return "[Strike!]";
    }
}