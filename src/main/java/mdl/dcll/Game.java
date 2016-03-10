package mdl.dcll;

import mdl.dcll.types.CoupAdditionnel;
import mdl.dcll.types.Frame;
import mdl.dcll.types.Spare;
import mdl.dcll.types.Strike;

import java.util.ArrayList;

/**
 * Created by benja135 on 09/03/16.
 */
public class Game {

    private ArrayList<Frame> jeux;
    private String acceptedChars1 = "_123456789X";
    private String acceptedChars2 = acceptedChars1 + "/";

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
    public void add(Frame f) {
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
    public Frame get(int i) {
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

        if (jeux.size() < 10) {
            System.out.println("Le nombre de jeux est incorrect: " + jeux.size());
            r = false;
        } else if (jeux.get(9) instanceof Strike && jeux.size() != 12) {
            System.out.println("Il manque " + (12-jeux.size()) + " coups additionnels.");
            r = false;
        } else if (jeux.get(9) instanceof Spare && jeux.size() != 11) {
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
            System.out.print(this.get(i).toString());
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
     * TODO compute score pas fini
     * Calcule le score totale de la partie
     * @return
     */
    public int computeScore() {
        int score = 0;

        for (int i = 1; i <= 10; i++) {

            if (jeux.get(i) instanceof Frame) {
                score += jeux.get(i).score();
            } else if (jeux.get(i) instanceof Strike) {
                score += jeux.get(i).score();

            } else if (jeux.get(i) instanceof Spare) {
                score += jeux.get(i+1).score_c1();
            }
        }
        return 0;
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
                        this.add(new Strike());
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
                        this.add(new Spare(toInt(sequence.charAt(lanceCourant - 1))));
                    } else {
                        this.add(new Frame(toInt(sequence.charAt(lanceCourant - 1)), toInt(lance)));
                    }
                } else {
                    System.out.println("Erreur, caractére " + lance + " inattendu.");
                    return false;
                }
            }

            if (this.size() == 10) {

                if (this.get(10) instanceof Strike) {

                    if (lanceCourant+2+1 == sequence.length()) {
                        if (acceptedChars1.contains(sequence.charAt(lanceCourant+1)+"")
                                && acceptedChars2.contains(sequence.charAt(lanceCourant+2)+"")) {
                            this.add(new CoupAdditionnel(toInt(sequence.charAt(lanceCourant+1))));
                            this.add(new CoupAdditionnel(toInt(sequence.charAt(lanceCourant+2))));
                        } else {
                            System.out.println("Erreur, caractére inattendu.");
                            return false;
                        }
                    } else {
                        System.out.println("Erreur, nombre de coups après le jeu 10 invalides.");
                        return false;
                    }
                    lanceCourant += 2;

                } else if (this.get(10) instanceof Spare) {

                    if (lanceCourant+1+1 == sequence.length()) {
                        if (acceptedChars1.contains(sequence.charAt(lanceCourant+1)+"")) {
                            this.add(new CoupAdditionnel(toInt(sequence.charAt(lanceCourant+1))));
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
     * Converti un lancé en en int (de 0 (_) à 10 (X))
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
