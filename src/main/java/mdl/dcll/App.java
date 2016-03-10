package mdl.dcll;

import mdl.dcll.types.CoupAdditionnel;
import mdl.dcll.types.Frame;
import mdl.dcll.types.Spare;
import mdl.dcll.types.Strike;

/**
 * The Bowling Project
 *
 */
public class App
{

    public static void main( String[] args ) {
        String resultat1 ="XXXXXXXXXXXX";
        String resultat2="9_9_9_9_9_9_9_9_9_9_";
        String resultat3="5/5/5/5/5/5/5/5/5/5/5";
        String resultat4="1234567891";

        Game game = new Game();
        System.out.println("Séquence correcte : " + game.build(resultat1));
        game.afficher();
        System.out.println();

        System.out.println("Séquence correcte : " + game.build(resultat2));
        game.afficher();
        System.out.println();

        System.out.println("Séquence correcte : " + game.build(resultat3));
        game.afficher();
        System.out.println();

        System.out.println("Séquence correcte : " + game.build(resultat4));
        game.afficher();
        System.out.println();

        System.out.println("\n\nnormalement 46 : " + calculerResultat(resultat4));
        System.out.println("normalement 150 : " + calculerResultat(resultat3));
        System.out.println("normalement 90 : " + calculerResultat(resultat2));
    }


    /**
     * Retourne le score de la partie passé en paramètre
     * @param res
     * @return
     */
    public static int calculerResultat(String res)
    {
        int score = 0;
        int bonus=0;
        int scoreAvalider =0;
        boolean spare = false;
        boolean strike = false;
        boolean rienTouche = false;

        for (int i=0; i< res.length(); i++) {

            if (res.charAt(i) == '/') { // si spare
                bonus = 10;
                spare = true;
            }

            if (res.charAt(i) == '_') { // si aucune boule touché
                rienTouche = true;
            }

            // a implenté : aucune boule touché au premier lancé
            if (i%2 == 0) { //tous les premiers pair
                if (spare) {
                    score += bonus;
                    score += res.charAt(i)-'0';
                    spare = false;
                    bonus = 0;
                }
                else if (rienTouche) {
                    scoreAvalider = 0;
                    rienTouche = false;
                }
                else {
                    scoreAvalider = res.charAt(i)-'0';
                }
            }
            else { // tous les coups impair
                if (!spare) {
                    if (rienTouche) {
                        score += scoreAvalider;
                        scoreAvalider = 0;
                        rienTouche = false;
                    } else {
                        score += scoreAvalider;
                        score += res.charAt(i)-'0';
                        scoreAvalider = 0;
                    }
                }
                else {
                    scoreAvalider = 0;
                }
            }
        }
        return score;
    }




}