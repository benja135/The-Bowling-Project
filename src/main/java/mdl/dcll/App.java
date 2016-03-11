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


    public static void initialiseStrike(int[][] tableau,int nbstrike) {
        tableau[nbstrike][0] = 0; // doit atteindre 2 pour arreter le strike
        tableau[nbstrike][1] = 10; // score total du strike 10 de départ car bonus de 10
    }

    // retourne le score de la partie passé en paramètre
    public static int calculerResultat(String res){
        int score = 0;
        int bonus=0;
        int scoreAvalider =0;
        boolean spare = false;
        boolean strike = false;
        boolean rienTouche = false;
        int coupStrike = 2;

        for (int i=0; i< res.length(); i++) {

            if (res.charAt(i) == '/') { //si spare
                bonus = 10;
                spare = true;
            }

            if (res.charAt(i) == '_') { // si aucune boule touché
                rienTouche = true;
            }

            if (res.charAt(i) == 'X') {
                bonus = 10;
                strike = true;
            }

            //TODO à implenter : aucune boule touché au premier lancé
            if (i%2 == 0) { //tous les premiers pair

                if (spare) { // si spare
                    score += bonus;
                    score += res.charAt(i)-'0';
                    spare = false;
                    bonus = 0;
                }
                else if (rienTouche) { //si pas de boule touché
                    scoreAvalider = 0;
                    rienTouche = false;
                }
                else if (strike) { // si strike
                    scoreAvalider += bonus;
                }
                else {

                    scoreAvalider = res.charAt(i)-'0';
                }

            } else { //tous les coups impair

                if (!spare) {
                    if (rienTouche) {
                        score += scoreAvalider;
                        scoreAvalider = 0;
                        rienTouche = false;
                    } else {
                        score += scoreAvalider;
                        score += res.charAt(i)-'0';
                        scoreAvalider =0;
                    }
                } else {
                    scoreAvalider = 0;
                }
            }

        }

        //************************************************//
        //strike:

        int[][] tab = new int [10][10];

        int nbstrike=0;

        if(strike){
            nbstrike++;
            initialiseStrike(tab,nbstrike);

            for(int i=0; i< nbstrike;i++) {
                tab[nbstrike][0]++;

                if (tab[nbstrike][0] < 3) {

                    if (res.charAt(i) == '/' || res.charAt(i) == 'X') {
                        tab[nbstrike][1] += 10;
                    }
                    else if(res.charAt(i) == '/'){
                        tab[nbstrike][1] += 0;
                    }
                    else{
                        tab[nbstrike][1] += res.charAt(i)-'0';
                    }
                }
            }
        }
        ///***********************************************//
        return score;
    }

}
