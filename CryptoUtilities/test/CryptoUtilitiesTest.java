import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Elijah Bulluck
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

//added routine case
    @Test
    public void testReduceToGCD_400700_18223672() {
        NaturalNumber n = new NaturalNumber2(400700);
        NaturalNumber nExpected = new NaturalNumber2(4);
        NaturalNumber m = new NaturalNumber2(18223672);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    //added routine case
    @Test
    public void testIsEven_1324532() {
        NaturalNumber n = new NaturalNumber2(1324532);
        NaturalNumber nExpected = new NaturalNumber2(1324532);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    //added case

    @Test
    public void testPowerMod_2147483647_47_2() {
        NaturalNumber n = new NaturalNumber2(2147483647);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(47);
        NaturalNumber pExpected = new NaturalNumber2(47);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isWitnessToCompositeness
     */
//boundary case, smallest possible value for n and w
    @Test
    public void testisWitnessToCompositeness_5_3() {
        NaturalNumber n = new NaturalNumber2(5);
        NaturalNumber nExpected = new NaturalNumber2(5);
        NaturalNumber w = new NaturalNumber2(3);
        NaturalNumber wExpected = new NaturalNumber2(3);
        boolean iwtc = CryptoUtilities.isWitnessToCompositeness(w, n);
        boolean expected = false;
        assertEquals(expected, iwtc);
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
    }

    //routine case, should be false
    @Test
    public void testIsWitnessToCompositeness_7_3() {
        NaturalNumber n = new NaturalNumber2(7);
        NaturalNumber nExpected = new NaturalNumber2(7);
        NaturalNumber w = new NaturalNumber2(3);
        NaturalNumber wExpected = new NaturalNumber2(3);
        boolean iwtc = CryptoUtilities.isWitnessToCompositeness(w, n);
        boolean expected = false;
        assertEquals(expected, iwtc);
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
    }

    @Test
    public void testisWitnessToCompositeness_5_2() {
        NaturalNumber n = new NaturalNumber2(5);
        NaturalNumber nExpected = new NaturalNumber2(5);
        NaturalNumber w = new NaturalNumber2(2);
        NaturalNumber wExpected = new NaturalNumber2(2);
        boolean iwtc = CryptoUtilities.isWitnessToCompositeness(w, n);
        boolean expected = true;
        assertEquals(expected, iwtc);
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
    }

    /*
     * Tests of isPrime1
     */

    //smallest without chance of error
    @Test
    public void testIsPrime1_4() {
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber nExpected = new NaturalNumber2(4);
        boolean prime = CryptoUtilities.isPrime1(n);
        boolean exp = false;
        assertEquals(nExpected, n);
        assertEquals(exp, prime);
    }

    //boundary case, smallest possible number
    @Test
    public void testIsPrime1_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        boolean prime = CryptoUtilities.isPrime1(n);
        boolean exp = true;
        assertEquals(nExpected, n);
        assertEquals(exp, prime);
    }

    //routine case, should be false because there's no chance of error
    @Test
    public void testIsPrime1_10() {
        NaturalNumber n = new NaturalNumber2(10);
        NaturalNumber nExpected = new NaturalNumber2(10);
        boolean prime = CryptoUtilities.isPrime1(n);
        boolean exp = false;
        assertEquals(nExpected, n);
        assertEquals(exp, prime);
    }

    /*
     * Tests of isPrime2
     */
    //boundary case, smallest possible number
    //(n must be > 2 in composite method)
    @Test
    public void testIsPrime2_3() {
        NaturalNumber n = new NaturalNumber2(3);
        NaturalNumber nExpected = new NaturalNumber2(3);
        boolean prime = CryptoUtilities.isPrime2(n);
        boolean exp = true;
        assertEquals(nExpected, n);
        assertEquals(exp, prime);
    }

    //routine case, prime number, probability of error
    @Test
    public void testIsPrime2_11() {
        NaturalNumber n = new NaturalNumber2(11);
        NaturalNumber nExpected = new NaturalNumber2(11);
        boolean prime = CryptoUtilities.isPrime2(n);
        boolean exp = false;
        assertEquals(nExpected, n);
        assertEquals(exp, prime);
    }

    //routine case, composite number, probability of error
    @Test
    public void testIsPrime2_10() {
        NaturalNumber n = new NaturalNumber2(10);
        NaturalNumber nExpected = new NaturalNumber2(10);
        boolean prime = CryptoUtilities.isPrime2(n);
        boolean exp = false;
        assertEquals(nExpected, n);
        assertEquals(exp, prime);
    }
    /*
     * Tests of generateNextLikelyPrime
     */

    //boundary case, smallest number
    @Test
    public void testGenerateNextLikelyPrime_3() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nOriginal = new NaturalNumber2(2);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(true, n.compareTo(nOriginal) >= 0);
    }

    //routine case, odd number
    @Test
    public void testGenerateNextLikelyPrime_11() {
        NaturalNumber n = new NaturalNumber2(11);
        NaturalNumber nOriginal = new NaturalNumber2(11);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(true, n.compareTo(nOriginal) >= 0);
    }

}
