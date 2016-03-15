package mdl.dcll;

/**
 * The Bowling Project
 */
public class App {

    public static void main(final String[] args) {
        String resultat1 = "XXXXXXXXXXXX";            // 300
        String resultat2 = "9_9_9_9_9_9_9_9_9_9_";    // 90
        String resultat3 = "5/5/5/5/5/5/5/5/5/5/5";   // 150
        String resultat4 = "6/8/4/9/XXX8/1/6/X";      // 196

        Game game = new Game();
        System.out.println(game.build(resultat1));
        game.afficher();
        System.out.println();

        System.out.println(game.build(resultat2));
        game.afficher();
        System.out.println();

        System.out.println(game.build(resultat3));
        game.afficher();
        System.out.println();

        System.out.println(game.build(resultat4));
        game.afficher();
        System.out.println();
    }

}
