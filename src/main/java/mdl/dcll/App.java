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
        String fullSpare = "5/5/5/5/5/5/5/5/5/5/5/";

        System.out.println(sequenceIsCorrect(fullStike));

        System.out.println(sequenceIsCorrect(nineZero));

        System.out.println(sequenceIsCorrect(fullSpare));

    }

    public static boolean sequenceIsCorrect(String sequence) {

        System.out.println("Vérification de la séquence");

        String acceptedChars1 = "_123456789X";
        String acceptedChars2 = acceptedChars1 + "/";

        boolean strike = false;
        boolean spare = false;
        int numJeu = 1;
        int numLance = 1;
        int maxNumJeu = 10;

        for (int lanceTotal = 0; lanceTotal < sequence.length(); lanceTotal++) {

            if (numJeu > maxNumJeu) {
                System.out.println("Erreur, trop de jeu jouer petit cheater.");
                return false;
            }

            strike = false;
            spare = false;
            String lance = (String) sequence.substring(lanceTotal, lanceTotal+1);

            if (numLance == 1) {
                if (acceptedChars1.contains(lance)) {
                    if (lance.compareTo("X") == 0) {
                        numJeu++;
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
                    numJeu++;
                    numLance = 1;
                    if (lance.compareTo("/") == 0) {
                        spare = true;
                    }
                } else {
                    System.out.println("Erreur, caractére " + lance + " inconnu.");
                    return false;
                }
            }

            if ((numJeu == 9 && strike) || (numJeu == 10 && spare)) {
                maxNumJeu = 11;
            }
            if ((numJeu == 10 && strike)) {
                maxNumJeu = 12;
            }
        }

        if (numJeu <= maxNumJeu || (numJeu == maxNumJeu && !strike && numLance != 2)) {
            System.out.println("Il reste des coups à jouer.");
            return false;
        }

        return true;
    }

}
