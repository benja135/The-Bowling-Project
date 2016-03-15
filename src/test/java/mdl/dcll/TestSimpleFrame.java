package mdl.dcll;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import mdl.dcll.types.Frame;
import mdl.dcll.types.Simple;

/**
 * Created by Matteo on 15/03/2016.
 */
public class TestSimpleFrame extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TestSimpleFrame(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(TestSimpleFrame.class);
    }

    public void testToString() {
        Frame f = new Simple(2, 3);

        assertTrue("[2:3]".equals(f.toString()));

    }

    public void testScore() {
        Frame f = new Simple(2, 3);

        int r = f.scoreC1();
    }

    public void testIsValid() {
        Frame f = new Simple(2, 3);
        f.isValid();

        Frame f2 = new Simple(10, 11);
        f2.isValid();
    }

}
