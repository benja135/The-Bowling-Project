package mdl.dcll.types;

/**
 * Created by benja135 on 14/03/16.
 * Classe non instanciable qui contient toutes nos constantes.
 */
public final class Constantes {

    /**
     * Constructeur privé pour empêcher l'instanciation.
     */
    private Constantes() {
        // rien
    }

    /**
     * MAX_FRAME est le nombre de Frame d'une partie de Bowling.
     * MAX_FRAME = 10 mais cette valeur peut normalement être modifié.
     */
    public static final int MAX_FRAME = 10;

    /**
     * MAX_QUILLE est le nombre de quille à faire tomber.
     * Cette valeur ne doit pas être modifié.
     */
    public static final int MAX_QUILLE = 10;

}
