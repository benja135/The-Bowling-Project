package mdl.dcll;

import mdl.dcll.types.CoupAdditionnel;
import mdl.dcll.types.Frame;
import mdl.dcll.types.Spare;
import mdl.dcll.types.Strike;

import static mdl.dcll.types.Constantes.MAX_FRAME;

import java.util.ArrayList;

/**
 * Created by benja135 on 09/03/16.
 * Une Game est une partie de bowling, composée de Frames
 */
public class Game {

    private static final String ACCEPTED_CHARS_1 = "_123456789X";
    private static final String ACCEPTED_CHARS_2 = ACCEPTED_CHARS_1 + "/";
    private ArrayList<Frame> jeux;

    /**
     * Constructeur, initilise la liste de jeux
     */
    public Game() {
        jeux = new ArrayList<Frame>();
    }

    /**
     * Ajoute un jeu dans la partie
     *
     * @param f
     */
    private void addFrame(Frame f) {
        if (jeux.size() >= MAX_FRAME && !(f instanceof CoupAdditionnel)) {
            System.out.println("Le jeu ajouté n'est pas un coup additionnel alors qu'il y a "
                    + "déjà " + jeux.size() + " jeux dans la partie, il ne sera pas ajouté.");
        } else if (jeux.size() < MAX_FRAME + 2) {
            jeux.add(f);
        } else {
            System.out.println("Désolé. Il y a déjà " + (MAX_FRAME + 2) + " jeux entrés dans la partie.");
        }
    }


    /**
     * Retourne le jeu i de la partie (i commence à 1)
     *
     * @param i
     * @return
     */
    private Frame getFrame(int i) {
        return jeux.get(i - 1);
    }


    /**
     * Retourne si la partie est oui ou non valide
     *
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

        if (this.size() < MAX_FRAME) {
            System.out.println("Le nombre de jeux est incorrect: " + this.size());
            r = false;
        } else if ((this.getFrame(MAX_FRAME) instanceof Strike && this.size() != MAX_FRAME + 2)
                || (this.getFrame(MAX_FRAME) instanceof Spare && this.size() != MAX_FRAME + 1)) {
            System.out.println("Erreur, nombre de coups après le jeu " + MAX_FRAME + " invalides.");
            r = false;
        }
        return r;
    }


    /**
     * Affiche l'intégralité des jeux de la partie et le score
     */
    public void afficher() {
        for (int i = 1; i <= this.size(); i++) {
            System.out.print(this.getFrame(i).toString());
            if (i < this.size()) {
                System.out.print(" | ");
            } else {
                System.out.println("\nScore : " + this.computeScore());
            }
        }
    }


    /**
     * Retourne le nombre de jeux insérés dans la partie
     *
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
     *
     * @return
     */
    public int computeScore() {

        int score = 0;
        if (!this.isValid()) {
            return score;
        }

        for (int i = 1; i <= MAX_FRAME; i++) {
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
     *
     * @param fCourante
     * @return
     */
    private int scoreOfTheTwoNextStrokes(int fCourante) {
        int score = 0;
        if (this.getFrame(fCourante + 1) instanceof Strike || this.getFrame(fCourante + 1) instanceof CoupAdditionnel) {
            score = scoreOfTheNextStroke(fCourante) + scoreOfTheNextStroke(fCourante + 1);
        } else if (this.getFrame(fCourante + 1) instanceof Spare || this.getFrame(fCourante + 1) instanceof Frame) {
            score = this.getFrame(fCourante + 1).score();
        }
        return score;
    }

    /**
     * Calcule le score du prochain lancé par rapport à une Frame
     * Sous fonction pour le calcul du score total de la partie
     *
     * @param fCourante
     * @return
     */
    private int scoreOfTheNextStroke(int fCourante) {
        return this.getFrame(fCourante + 1).scoreC1();
    }

    /**
     * Construit la partie en donnant la séquence sous forme de String
     * et retourne si oui ou non la partie est valide
     *
     * @param sequence
     * @return
     */
    public boolean build(String sequence) {

        this.delete();
        System.out.println("Vérification de la séquence " + sequence);

        int numLance = 1;
        int lanceCourant = 0;
        char lance;

        while (lanceCourant < sequence.length()) {

            lance = sequence.charAt(lanceCourant);

            if (numLance == 1) {
                if (ACCEPTED_CHARS_1.contains(lance + "")) {
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
                if (ACCEPTED_CHARS_2.contains(lance + "")) {
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

            if (this.size() == MAX_FRAME) {

                if (this.getFrame(MAX_FRAME) instanceof Strike) {

                    if (lanceCourant + 2 + 1 == sequence.length()) {
                        if (ACCEPTED_CHARS_1.contains(sequence.charAt(lanceCourant + 1) + "")
                                && ACCEPTED_CHARS_2.contains(sequence.charAt(lanceCourant + 2) + "")) {
                            this.addFrame(new CoupAdditionnel(toInt(sequence.charAt(lanceCourant + 1))));
                            this.addFrame(new CoupAdditionnel(toInt(sequence.charAt(lanceCourant + 2))));
                        } else {
                            System.out.println("Erreur, caractére inattendu.");
                            return false;
                        }
                    } else {
                        System.out.println("Erreur, nombre de coups après le jeu " + MAX_FRAME + " invalides.");
                        return false;
                    }
                    lanceCourant += 2;

                } else if (this.getFrame(MAX_FRAME) instanceof Spare) {

                    if (lanceCourant + 1 + 1 == sequence.length()) {
                        if (ACCEPTED_CHARS_1.contains(sequence.charAt(lanceCourant + 1) + "")) {
                            this.addFrame(new CoupAdditionnel(toInt(sequence.charAt(lanceCourant + 1))));
                        } else {
                            System.out.println("Erreur, caractére inattendu.");
                            return false;
                        }
                    } else {
                        System.out.println("Erreur, nombre de coups après le jeu " + MAX_FRAME + " invalides.");
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
     *
     * @param c
     * @return
     */
    private static int toInt(char c) {
        int entier;

        if (c == '_') {
            entier = 0;
        } else if (c == 'X') {
            entier = MAX_FRAME;
        } else {
            entier = Integer.parseInt(c + "");
        }
        return entier;
    }

}
