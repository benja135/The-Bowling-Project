package mdl.dcll.types;

/**
 * Created by benja135 on 09/03/16.
 * Une frame est un "jeu" classique. càd un tour de 2 lancés (ni strike ni spare)
 */
public class Frame {

    private int c1, c2;

    public Frame() {}

    public Frame(int c1, int c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    public int score() {
        return c1 + c2;
    }

    public int score_c1() {
        return c1;
    }

    public int score_c2() {
        return c2;
    }

    public boolean isValid() {
        return c1 + c2 < 10;
    }

    public String toString() {
        return "[" + c1 + ":" + c2 + "]";
    }
}