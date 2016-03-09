package mdl.dcll;

/**
 * Hello world!
 *
 */
public class App
{

    public static void initialiseStrike(int[][] tableau,int nbstrike){

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

            if(res.charAt(i) == '/') { //si spare
                bonus = 10;
                spare = true;
            }

            if(res.charAt(i) == '_'){ // si aucune boule touché
                rienTouche = true;
            }

            if(res.charAt(i) == 'X'){
                bonus = 10;
                strike = true;
            }



// a implenté : aucune boule touché au premier lancé
            if(i%2 == 0){ //tous les premiers pair

                if(spare){ // si spare
                    score += bonus;
                    score += res.charAt(i)-'0';
                    spare = false;
                    bonus = 0;
                }
                else if(rienTouche) { //si pas de boule touché
                    scoreAvalider = 0;
                    rienTouche = false;
                }
                else if(strike){ // si strike
                    scoreAvalider += bonus;
                }
                else{

                    scoreAvalider = res.charAt(i)-'0';
                }

            }
            else{ //tous les coups impair



                if(!spare){

                    if(rienTouche){
                        score += scoreAvalider;
                        scoreAvalider = 0;
                        rienTouche = false;
                    }
                    else{
                        score += scoreAvalider;
                        score += res.charAt(i)-'0';
                        scoreAvalider =0;
                    }


                }
                else{

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



    public static void main( String[] args )
    {
        String resultat1 ="XXXXXXXXXXXX";
        String resultat2="9_9_9_9_9_9_9_9_9_9_";
        String resultat3="5/5/5/5/5/5/5/5/5/5/5";





        System.out.println("normalement 150 : " + calculerResultat(resultat3));
        System.out.println("normalement 90 : " + calculerResultat(resultat2));
        System.out.println("normalement 300 : " + calculerResultat(resultat1));




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
