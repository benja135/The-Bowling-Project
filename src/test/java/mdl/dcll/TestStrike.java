package mdl.dcll;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import mdl.dcll.types.Strike;

/**
 * Created by Matteo on 15/03/2016.
 */
public class TestStrike extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TestStrike(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(TestStrike.class);
    }

    public void testToString() {
        Strike s = new Strike();
        assertTrue("[Strike!]" == s.toString());
    }

}
