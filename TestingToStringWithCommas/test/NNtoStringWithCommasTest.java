import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

public class NNtoStringWithCommasTest {

    /**
     * Calls the method under test.
     *
     * @param n
     *            the number to pass to the method under test
     * @return the {@code String} returned by the method under test
     * @ensures <pre>
     * redirectToMethodUnderTest = [String returned by the method under test]
     * </pre>
     */
    private static String redirectToMethodUnderTest(NaturalNumber n) {
        return NNtoStringWithCommas6.toStringWithCommas(n);
    }

    /**
     * Test toStringWithCommas with input 1.
     */
    @Test
    public void toStringWithCommasTest1() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber zero = new NaturalNumber2(0);
        String s = "0";
        String s1 = NNtoStringWithCommas6.toStringWithCommas(zero);
        NaturalNumber newZero = new NaturalNumber2(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s, s1);
        assertEquals(zero, newZero);

    }
    // using 0 as a boundary case, smallest input

    /**
     * Test toStringWithCommas with input 1000.
     */
    @Test
    public void toStringWithCommasTest2() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(1000);
        String s = "1,000";
        String s1 = NNtoStringWithCommas6.toStringWithCommas(n);
        NaturalNumber newN = new NaturalNumber2(1000);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s, s1);
        assertEquals(n, newN);

    }
    // using 1,000 as a routine case, 1 comma

    /**
     * Test toStringWithCommas with input 2143674.
     */
    @Test
    public void toStringWithCommasTest3() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(2143674);
        String s = "2,143,674";
        String s1 = NNtoStringWithCommas6.toStringWithCommas(n);
        NaturalNumber newN = new NaturalNumber2(2143674);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s, s1);
        assertEquals(n, newN);

    }
    // using 0 as a routine case, 2 commas

    /**
     * Test toStringWithCommas with input 1344554567666621.
     */
    @Test
    public void toStringWithCommasTest4() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(21474836478);
        String s = "21,474,836,478";
        String s1 = NNtoStringWithCommas6.toStringWithCommas(n);
        NaturalNumber newN = new NaturalNumber2(21474836478);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s, s1);
        assertEquals(n, newN);

    }

}
