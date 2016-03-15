package mdl.dcll.types;

import static mdl.dcll.types.Constantes.MAX_QUILLE;

/**
 * Created by benja135 on 09/03/16.
 * Un Strike est une Frame où toutes les quilles sont tombées en 1 coup.
 */
public final class Strike extends Frame {

    /**
     * Constructeur de stike.
     */
    public Strike() {
    }

    /**
     * Retourne le score d'un Strike.
     *
     * @return MAX_QUILLE
     */
    public int score() {
        return MAX_QUILLE;
    }

    /**
     * Retourne le score du premier lancé du Strike, càd MAX_QUILLE.
     *
     * @return MAX_QUILLE
     */
    public int scoreC1() {
        return MAX_QUILLE;
    }

    /**
     * Retourne si oui ou non le Strike est valide.
     *
     * @return true si le Strike est valide
     */
    public boolean isValid() {
        return true;
    }

    /**
     * Retourne la représensation d'un Strike.
     *
     * @return string représentant le Strike
     */
    public String toString() {
        return "[Strike!]";
    }
}
