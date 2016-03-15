package mdl.dcll;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import mdl.dcll.types.CoupAdditionnel;

/**
 * Created by Matteo on 15/03/2016.
 */
public class testCoupAdditionnel extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public testCoupAdditionnel(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(testCoupAdditionnel.class);
    }

    public void testClasse(){
        CoupAdditionnel c = new CoupAdditionnel(4);
        int r =c.score();

        assertTrue(c.isValid());
        assertTrue(c.toString().equals("[Coup additionnel: 4]"));

        CoupAdditionnel c2 = new CoupAdditionnel(15);
        int a = c2.score();
        assertFalse(c2.isValid());
        assertTrue(c2.toString().equals("[Coup additionnel: 15]"));

        CoupAdditionnel c3 = new CoupAdditionnel(-2);
        int y = c3.score();
        assertFalse(c3.isValid());
        c3.toString();



    }
}
