package mdl.dcll.types;

import static mdl.dcll.types.Constantes.MAX_QUILLE;

/**
 * Created by benja135 on 15/03/16.
 * Un Simple est une frame simple, càd une frame en deux coups qui n'est pas un Spare.
 */
public final class Simple extends Frame {

    /**
     * c1 et c2 pour coup 1 et coup 2.
     * Car une frame Simple est composé de 2 lancés.
     */
    private int c1, c2;

    /**
     * Constructeur d'une frame Simple.
     *
     * @param coup1 Lancé 1s
     * @param coup2 Lancé 2
     */
    public Simple(final int coup1, final int coup2) {
        this.c1 = coup1;
        this.c2 = coup2;
    }

    /**
     * Retourne le score total de la frame Simple.
     *
     * @return score total de la frame simple
     */
    public int score() {
        return c1 + c2;
    }

    /**
     * Retourne le score du premier lancé.
     *
     * @return score du 1er lancé
     */
    public int scoreC1() {
        return c1;
    }

    /**
     * Retourne si oui ou non la frame Simple est valide.
     *
     * @return true si la frame Simple est valide
     */
    public boolean isValid() {
        return c1 + c2 < MAX_QUILLE;
    }

    /**
     * Retourne la représensation d'une frame Simple.
     *
     * @return string représentant la frame Simple
     */
    public String toString() {
        return "[" + c1 + ":" + c2 + "]";
    }
}
