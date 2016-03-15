package mdl.dcll;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import mdl.dcll.types.Frame;

/**
 * Created by Matteo on 15/03/2016.
 */
public class TestFrame extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TestFrame(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(TestFrame.class);
    }

    public void testToString(){
        Frame f = new Frame(2,3);

        assertTrue("[2:3]".equals(f.toString()));

    }

    public void testScore(){
        Frame f = new Frame(2,3);

        int r = f.scoreC1();
    }

    public void testIsValid(){
        Frame f = new Frame(2,3);
        f.isValid();

        Frame f2 = new Frame(10,11);
        f2.isValid();
    }

}
