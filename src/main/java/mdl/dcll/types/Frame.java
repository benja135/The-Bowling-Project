package mdl.dcll.types;

import static mdl.dcll.types.Constantes.MAX_QUILLE;

/**
 * Created by benja135 on 09/03/16.
 * Une frame est un "jeu" classique. càd un tour de 2 lancés (ni strike ni spare)
 */
public class Frame {

    /**
     * c1 et c2 pour coup 1 et coup 2.
     * Car une frame classique est composé de 2 lancés.
     */
    private int c1, c2;

    /**
     * Constructeur par défaut.
     */
    public Frame() {
    }

    /**d
     * Constructeur d'une Frame.
     *
     * @param coup1 Lancé 1
     * @param coup2 Lancé 2
     */
    public Frame(final int coup1, final int coup2) {
        this.c1 = coup1;
        this.c2 = coup2;
    }

    /**
     * Retourne le score total de la Frame.
     *
     * @return score total de la Frame
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
     * Retourne si oui ou non la frame est valide.
     *
     * @return true si la frame est valide
     */
    public boolean isValid() {
        return c1 + c2 < MAX_QUILLE;
    }

    /**
     * Retourne la représensation d'une frame.
     *
     * @return string représentant la Frame
     */
    public String toString() {
        return "[" + c1 + ":" + c2 + "]";
    }
}
