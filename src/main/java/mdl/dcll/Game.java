package mdl.dcll;

import mdl.dcll.types.CoupAdditionnel;
import mdl.dcll.types.Frame;
import mdl.dcll.types.Spare;
import mdl.dcll.types.Strike;

import java.util.ArrayList;

/**
 * Created by benja135 on 09/03/16.
 * Une Game est une partie de bowling, composée de Frames
 */
public class Game {

    private final String acceptedChars1 = "_123456789X";
    private final String acceptedChars2 = acceptedChars1 + "/";
    private ArrayList<Frame> jeux;

    /**
     * Constructeur, initilise la liste de jeux
     */
    public Game() {
        jeux = new ArrayList<Frame>();
    }

    /**
     * Ajoute un jeu dans la partie
     * @param f
     */
    private void addFrame(Frame f) {
        if (jeux.size() >= 10 && !(f instanceof CoupAdditionnel)) {
            System.out.println("Le jeu ajouté n'est pas un coup additionnel " +
                    "alors qu'il y a déjà 10 jeux dans la partie, il ne sera pas ajouté.");
        } else if (jeux.size() < 12) {
            jeux.add(f);
        } else {
            System.out.println("Désolé. Il y a déjà 12 jeux entrés dans la partie.");
        }
    }


    /**
     * Retourne le jeu i de la partie (i commence à 1)
     * @param i
     * @return
     */
    private Frame getFrame(int i) {
        return jeux.get(i-1);
    }


    /**
     * Retourne si la partie est oui ou non valide
     * @return
     */
    public boolean isValid() {
        boolean r = true;

        for (Frame f : jeux) {
            if (!f.isValid()) {
                System.out.println("Le jeu " + f.toString() + " est incorrect.");
                r = false;
            }
        }

        if (this.size() < 10) {
            System.out.println("Le nombre de jeux est incorrect: " + this.size());
            r = false;
        } else if (this.getFrame(10) instanceof Strike && this.size() != 12) {
            System.out.println("Il manque " + (12-this.size()) + " coups additionnels.");
            r = false;
        } else if (this.getFrame(10) instanceof Spare && this.size() != 11) {
            System.out.println("Il manque 1 coup additionnel.");
            r = false;
        }
        return r;
    }


    /**
     * Affiche l'intégralité des jeux de la partie
     */
    public void afficher() {
        for (int i = 1; i <= this.size(); i++) {
            System.out.print(this.getFrame(i).toString());
            if (i < this.size()) {
                System.out.print(" | ");
            } else {
                System.out.println();
            }
        }
    }


    /**
     * Retourne le nombre de jeux insérés dans la partie
     * @return
     */
    public int size() {
        return jeux.size();
    }


    /**
     * Détruit tous les jeux de la partie
     */
    private void delete() {
        jeux.clear();
    }


    /**
     * Calcule le score totale de la partie
     * @return
     */
    public int computeScore() {
        int score = 0;

        for (int i = 1; i <= 10; i++) {
            if (this.getFrame(i) instanceof Strike) {
                score += this.getFrame(i).score() + this.scoreOfTheTwoNextStrokes(i);
            } else if (this.getFrame(i) instanceof Spare) {
                score += this.getFrame(i).score() + this.scoreOfTheNextStroke(i);
            } else if (this.getFrame(i) instanceof Frame) {
                score += this.getFrame(i).score();
            }
        }
        return score;
    }

    /**
     * Calcule le score des deux prochains lancés par rapport à une Frame
     * Sous fonction pour le calcul du score total de la partie
     * @param fCourante
     * @return
     */
    private int scoreOfTheTwoNextStrokes(int fCourante) {
        int score = 0;
        if (this.getFrame(fCourante+1) instanceof Strike || this.getFrame(fCourante+1) instanceof CoupAdditionnel) {
            score = scoreOfTheNextStroke(fCourante) + scoreOfTheNextStroke(fCourante+1);
        } else if (this.getFrame(fCourante+1) instanceof Spare || this.getFrame(fCourante+1) instanceof Frame) {
            score = this.getFrame(fCourante+1).score();
        }
        return score;
    }

    /**
     * Calcule le score du prochain lancé par rapport à une Frame
     * Sous fonction pour le calcul du score total de la partie
     * @param fCourante
     * @return
     */
    private int scoreOfTheNextStroke(int fCourante) {
        return this.getFrame(fCourante+1).score_c1();
    }

    /**
     * Construit la partie en donnant la séquence sous forme de String
     * et retourne si oui ou non la partie est valide
     * @param sequence
     * @return
     */
    public boolean build(String sequence) {

        this.delete();
        System.out.println("Vérification de la séquence");

        int numLance = 1;
        int lanceCourant = 0;
        char lance;

        while (lanceCourant < sequence.length()) {

            lance = sequence.charAt(lanceCourant);

            if (numLance == 1) {
                if (acceptedChars1.contains(lance + "")) {
                    if (lance == 'X') {
                        this.addFrame(new Strike());
                        numLance = 1;
                    } else {
                        numLance++;
                    }
                } else {
                    System.out.println("Erreur, caractére " + lance + " inattendu.");
                    return false;
                }
            } else { // numLance == 2
                if (acceptedChars2.contains(lance + "")) {
                    numLance = 1;
                    if (lance == '/') {
                        this.addFrame(new Spare(toInt(sequence.charAt(lanceCourant - 1))));
                    } else {
                        this.addFrame(new Frame(toInt(sequence.charAt(lanceCourant - 1)), toInt(lance)));
                    }
                } else {
                    System.out.println("Erreur, caractére " + lance + " inattendu.");
                    return false;
                }
            }

            if (this.size() == 10) {

                if (this.getFrame(10) instanceof Strike) {

                    if (lanceCourant+2+1 == sequence.length()) {
                        if (acceptedChars1.contains(sequence.charAt(lanceCourant+1)+"")
                                && acceptedChars2.contains(sequence.charAt(lanceCourant+2)+"")) {
                            this.addFrame(new CoupAdditionnel(toInt(sequence.charAt(lanceCourant+1))));
                            this.addFrame(new CoupAdditionnel(toInt(sequence.charAt(lanceCourant+2))));
                        } else {
                            System.out.println("Erreur, caractére inattendu.");
                            return false;
                        }
                    } else {
                        System.out.println("Erreur, nombre de coups après le jeu 10 invalides.");
                        return false;
                    }
                    lanceCourant += 2;

                } else if (this.getFrame(10) instanceof Spare) {

                    if (lanceCourant+1+1 == sequence.length()) {
                        if (acceptedChars1.contains(sequence.charAt(lanceCourant+1)+"")) {
                            this.addFrame(new CoupAdditionnel(toInt(sequence.charAt(lanceCourant+1))));
                        } else {
                            System.out.println("Erreur, caractére inattendu.");
                            return false;
                        }
                    } else {
                        System.out.println("Erreur, nombre de coups après le jeu 10 invalides.");
                        return false;
                    }
                    lanceCourant += 1;
                }
            }
            lanceCourant++;
        }

        if (this.isValid()) {
            return true;
        } else {
            System.out.println("La partie n'est pas valide.");
            return false;
        }

    }


    /**
     * Converti un lancé en int (de 0 (_) à 10 (X))
     * @param c
     * @return
     */
    private static int toInt(char c) {
        int entier;

        if (c == '_') {
            entier = 0;
        } else if (c == 'X') {
            entier = 10;
        } else {
            entier = Integer.parseInt(c + "");
        }
        return entier;
    }

}
