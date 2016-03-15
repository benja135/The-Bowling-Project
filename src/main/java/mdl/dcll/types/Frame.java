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

    /**
     * Constructeur d'une Frame.
     *
     * @param coup1 Coup 1
     * @param coup2
     */
    public Frame(final int coup1, final int coup2) {
        this.c1 = coup1;
        this.c2 = coup2;
    }

    public int score() {
        return c1 + c2;
    }

    public int scoreC1() {
        return c1;
    }

    public boolean isValid() {
        return c1 + c2 < MAX_QUILLE;
    }

    public String toString() {
        return "[" + c1 + ":" + c2 + "]";
    }
}
