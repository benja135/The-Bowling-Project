package mdl.dcll;

/**
 * The Bowling Project
 *
 */
public class App
{

    public static void main( String[] args )
    {
        String resultat1 ="XXXXXXXXXXXX";
        String resultat2="9_9_9_9_9_9_9_9_9_9_";
        String resultat3="5/5/5/5/5/5/5/5/5/5/5";
        String resultat4="1234567891";

        Game game = new Game();
        System.out.println("Séquence correcte : " + game.build(resultat1));
        game.afficher();
        System.out.println("normalement 300 : " + game.computeScore());
        System.out.println();

        System.out.println("Séquence correcte : " + game.build(resultat2));
        game.afficher();
        System.out.println("normalement 90 : " + game.computeScore());
        System.out.println();

        System.out.println("Séquence correcte : " + game.build(resultat3));
        game.afficher();
        System.out.println("normalement 150 : " + game.computeScore());
        System.out.println();

        System.out.println("Séquence correcte : " + game.build(resultat4));
        game.afficher();
        System.out.println();
    }

}
