package mdl.dcll.types;

import static mdl.dcll.types.Constantes.MAX_QUILLE;

/**
 * Created by benja135 on 09/03/16.
 * Un Spare est une Frame où les quilles sont tombées au bout des deux coups.
 */
public class Spare extends Frame {

    /**
     * c pour coup,
     * Une spare est constitué d'un seul lancé utile.
     */
    private int c;

    /**
     * Constructeur de Spare.
     *
     * @param coup lancé 1
     */
    public Spare(final int coup) {
        this.c = coup;
    }

    /**
     * Retourne le score d'un spare, normalement MAX_QUILLE.
     *
     * @return MAX_QUILLE
     */
    public int score() {
        return MAX_QUILLE;
    }

    /**
     * Retourne le score du premier lancé du Spare.
     *
     * @return c, lancé1
     */
    public int scoreC1() {
        return c;
    }

    /**
     * Retourne si oui ou non le spare est valide.
     *
     * @return true si le spare est valide
     */
    public boolean isValid() {
        return c >= 0 && c < MAX_QUILLE;
    }

    /**
     * Retourne la représensation d'un Spare.
     *
     * @return string représentant le Spare
     */
    public String toString() {
        return "[" + c + ":Spare]";
    }
}
