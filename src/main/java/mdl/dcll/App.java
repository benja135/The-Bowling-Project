package mdl.dcll;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "The Bowling Project !" );

        String fullStike = "XXXXXXXXXXXX";
        String nineZero = "9_9_9_9_9_9_9_9_9_9_";
        String fullSpare = "5/5/5/5/5/5/5/5/5/5/5";

        System.out.println(sequenceIsCorrect(fullStike));

        System.out.println(sequenceIsCorrect(nineZero));

        System.out.println(sequenceIsCorrect(fullSpare));

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
