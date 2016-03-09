package mdl.dcll;

/**
 * Hello world!
 *
 */
public class App
{

    public static void main( String[] args ) {
        String resultat1 ="XXXXXXXXXXXX";
        String resultat2="9_9_9_9_9_9_9_9_9_9_";
        String resultat3="5/5/5/5/5/5/5/5/5/5/5";
        String resultat4="1234567891";

        System.out.println(sequenceIsCorrect(resultat1));
        System.out.println(sequenceIsCorrect(resultat2));
        System.out.println(sequenceIsCorrect(resultat3));
        System.out.println(sequenceIsCorrect(resultat4));

        System.out.println("normalement 46 : " + calculerResultat(resultat4));
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


    /**
     * Retourne si oui ou non une séquence de point bowling est valide.
     * @param sequence séquence de points d'une partone
     * @return true si la séquence est valide
     */
    public static boolean sequenceIsCorrect(String sequence) {

        System.out.println("Vérification de la séquence");

        String acceptedChars1 = "_123456789X";
        String acceptedChars2 = acceptedChars1 + "/";

        boolean strike = false;
        boolean spare = false;
        int jeuJoue = 0;
        int numLance = 1;
        int maxNumJeu = 10;

        for (int lanceTotal = 0; lanceTotal < sequence.length(); lanceTotal++) {

            strike = false;
            spare = false;
            String lance = (String) sequence.substring(lanceTotal, lanceTotal+1);

            if (numLance == 1) {
                if (acceptedChars1.contains(lance)) {
                    if (lance.compareTo("X") == 0) {
                        jeuJoue++;
                        numLance = 1;
                        strike = true;
                    } else {
                        numLance++;
                    }
                } else {
                    System.out.println("Erreur, caractére " + lance + " inconnu ou mal placé.");
                    return false;
                }
            } else { // numLance == 2
                if (acceptedChars2.contains(lance)) {
                    jeuJoue++;
                    numLance = 1;
                    if (lance.compareTo("/") == 0) {
                        spare = true;
                    }
                } else {
                    System.out.println("Erreur, caractére " + lance + " inconnu.");
                    return false;
                }
            }

            // TODO c'est dégueulasse de faire ça. Arriver en master pour faire ça sérieux ! Je me dégoute.
            if (jeuJoue == 10) {
               if (spare) {
                    // rejouer une boule
                   if (lanceTotal+1+1 != sequence.length()) {
                       System.out.println("Erreur, nombre de coups après le jeu 10 invalides.");
                       return false;
                   }
                   if (!acceptedChars1.contains(sequence.substring(lanceTotal+1, lanceTotal+2))) {
                       System.out.println("Erreur, caractére inconnu ou mal placé.");
                       return false;
                   }
               } else if (strike) {
                   // rejouer 2 boules
                   if (lanceTotal+2+1 != sequence.length()) {
                       System.out.println("Erreur, nombre de coups après le jeu 10 invalides.");
                       return false;
                   }
                   if (!acceptedChars1.contains(sequence.substring(lanceTotal+1, lanceTotal+2))
                           || !acceptedChars2.contains(sequence.substring(lanceTotal+2, lanceTotal+3))) {
                       System.out.println("Erreur, caractére inconnu ou mal placé.");
                       return false;
                   }
               }
            }

        }

        if (jeuJoue < maxNumJeu) {
            System.out.println("Il reste des coups à jouer.");
            return false;
        }

        return true;
    }

}
