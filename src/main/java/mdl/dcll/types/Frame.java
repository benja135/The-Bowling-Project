package mdl.dcll.types;

/**
 * Created by benja135 on 09/03/16.
 * Une Frame peut-être un Strike, un Spare, un jeu Simple ou bien un CoupAdditionnel.
 */
public abstract class Frame {

    /**
     * Retourne le score total de la Frame.
     *
     * @return score total de la Frame
     */
    public abstract int score();

    /**
     * Retourne le score du premier lancé.
     *
     * @return score du 1er lancé
     */
    public abstract int scoreC1();

    /**
     * Retourne si oui ou non la frame est valide.
     *
     * @return true si la frame est valide
     */
    public abstract boolean isValid();

    /**
     * Retourne la représensation d'une frame.
     *
     * @return string représentant la Frame
     */
    public abstract String toString();
}
