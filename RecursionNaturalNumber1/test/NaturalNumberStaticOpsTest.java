import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber2;

/**
 * Test fixture for NaturalNumberStaticOps.
 *
 * @author Elijah Bulluck
 *
 */
public final class NaturalNumberStaticOpsTest {

    /**
     * Test toStringWithCommas with input 1.
     */
    @Test
    public void toStringWithCommasTest1() {
        /*
         * Set up variables and call method under test
         */
        String s = "0";
        String s1 = NaturalNumberStaticOps
                .toStringWithCommas(new NaturalNumber2(0));
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s, s1);

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
        String s = "1,000";
        String s1 = NaturalNumberStaticOps
                .toStringWithCommas(new NaturalNumber2(1000));
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s, s1);

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
        String s = "2,143,674";
        String s1 = NaturalNumberStaticOps
                .toStringWithCommas(new NaturalNumber2());
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s, s1);

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
        String s = "1,344,554,567,666,621";
        String s1 = NaturalNumberStaticOps
                .toStringWithCommas(new NaturalNumber2(0));
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s, s1);

    }
    // using 1344554567666621 as a boundary case, 5 commas
}
