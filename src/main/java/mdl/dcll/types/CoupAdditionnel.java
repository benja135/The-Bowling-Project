package mdl.dcll.types;

import static mdl.dcll.types.Constantes.MAX_QUILLE;

/**
 * Created by benja135 on 09/03/16.
 * Un CoupAdditionnel est le type de coup joué aprés la Frame 10
 */
public class CoupAdditionnel extends Frame {

    /**
     * c pour coup,
     * Un coup additionnel est constitué d'un seul lancé.
     */
    private int c;

    /**
     * Constructeur de CoupAdditionnel.
     *
     * @param coup lancé
     */
    public CoupAdditionnel(final int coup) {
        this.c = coup;
    }

    /**
     * Retourne le score du lancé.
     *
     * @return score du lancé
     */
    public int score() {
        return c;
    }

    /**
     * Retourne le score du 1er lancé, la même chose que score.
     *
     * @return score du lancé
     */
    public int scoreC1() {
        return c;
    }

    /**
     * Retourne si oui ou non le coup est valide.
     *
     * @return true si le coup est valide
     */
    public boolean isValid() {
        return c >= 0 && c <= MAX_QUILLE;
    }

    /**
     * Retourne la représensation d'un coup additionnel.
     *
     * @return string représentant le CoupAdditionnel
     */
    public String toString() {
        return "[Coup additionnel: " + c + "]";
    }

}
