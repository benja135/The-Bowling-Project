package mdl.dcll;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import mdl.dcll.types.Constantes;
import mdl.dcll.types.Spare;

import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Tests fonctionnels de création de partie à partire d'une séquence.
     */
    public void testsValideBuild() {
        // Séquences correctes
        String t1 = "XXXXXXXXXXXX";
        String t2 = "9_9_9_9_9_9_9_9_9_9_";
        String t3 = "5/5/5/5/5/5/5/5/5/5/5";
        String t4 = "6/8/4/9/XXX8/1/6/X";

        Game game = new Game();

        assertTrue(game.build(t1));
        assertTrue(game.build(t2));
        assertTrue(game.build(t3));
        assertTrue(game.build(t4));

    }

    /**
     * Tests fonctionnels de calcule du score d'une partie à partir d'une séquence correcte.
     */
    public void testsValidesScore() {
        ArrayList<Couple> test = new ArrayList<Couple>();

        // Séquences/scores corrects
        test.add(new Couple("XXXXXXXXXXXX", 300));
        test.add(new Couple("9_9_9_9_9_9_9_9_9_9_", 90));
        test.add(new Couple("5/5/5/5/5/5/5/5/5/5/5", 150));
        test.add(new Couple("6/8/4/9/XXX8/1/6/X", 196));

        Game game = new Game();

        for (Couple t : test) {
            game.build(t.getChaine());
            assertTrue(game.computeScore() == t.getEntier());
        }
    }

    /**
     * Tests non fonctionnels de création de partie à partire d'une séquence.
     */
    public void testsNonValidesBuild() {
        // Séquences fausses
        String t1 = "XXXXXXXXXXX";             // Manque un coup additionnel après un Strike frame 10
        String t2 = "9_9_9_9_9_9_9_9_9_9";     // Manque un coup à la frame 10
        String t3 = "5/5/5/5/5/5/5/5/5/5/";    // Manque un coup additionnel après un Spare frame 10
        String t4 = "6/8/4/9//XX8/1/6/X";      // Spare innattendu
        String t5 = "bonjour";                 // Caractères innatendus

        Game game = new Game();

        assertFalse(game.build(t1));
        assertFalse(game.build(t2));
        assertFalse(game.build(t3));
        assertFalse(game.build(t4));
        assertFalse(game.build(t5));

    }

    /**
     * test la validité d'un spare
     */
    public void testValideSpare() {
        Spare s = new Spare(9);
        assertTrue(s.isValid());

        s.toString(); // TODO à modif
    }

    /**
     * test la non validité d'un spare
     */
    public void testNonValideSpare() {
        Spare s1 = new Spare(12);
        assertFalse(s1.isValid());

        Spare s2 = new Spare(-2);
        assertFalse(s2.isValid());
    }


    public void testMain() {
        String[] arg = new String[2];
        App.main(arg);
    }


}
